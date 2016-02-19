package jp.gr.java_conf.ya.livewallclock; // Copyright (c) 2012-2016 YA <ya.androidapp@gmail.com> All rights reserved.

import java.util.Calendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class LiveWallService extends WallpaperService {

	private final Handler mHandler = new Handler();

	@Override
	public Engine onCreateEngine() {
		// 描画用の自作Engineクラスを返す
		return new LiveEngine();
	}

	// 描画を行うEngineクラス
	public class LiveEngine extends Engine {

		/** イメージ **/
		private Bitmap image1;
		private Bitmap image2;
		private Bitmap image3;
		private Bitmap image4;
		private Bitmap image5;
		private Bitmap image6;
		/** x座標 **/
		private int x1 = 0;
		private int x2 = 50;
		private int x3 = 100;
		private int x4 = 150;
		private int x5 = 200;
		private int x6 = 250;
		/** y座標 **/
		private int y1 = 0;
		private int y2 = 0;
		private int y3 = 0;
		private int y4 = 0;
		private int y5 = 0;
		private int y6 = 0;
		/** x速度 **/
		private int vx1 = 5;
		private int vx2 = -10;
		private int vx3 = 15;
		private int vx4 = -20;
		private int vx5 = 25;
		private int vx6 = -30;
		/** y速度 **/
		private int vy1 = 20;
		private int vy2 = 30;
		private int vy3 = 60;
		private int vy4 = 80;
		private int vy5 = 120;
		private int vy6 = 160;
		/** 幅 **/
		private int width;
		/** 高さ **/
		private int height;
		/** 描画画像切り替えフラグ **/
		private boolean flag = true;

		/** 描画用のRunnableオブジェクト **/
		private final Runnable drawRunnable = new Runnable() {
			public void run() {
				drawFrame();
			}
		};
		/** 表示状態フラグ **/
		private boolean visible;

		/** コンストラクタ **/
		public LiveEngine() {
			image1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
			image2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
			image3 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
			image4 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
			image5 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
			image6 = BitmapFactory.decodeResource(getResources(),
					R.drawable.image0);
		}

		/** Engine生成時に呼び出される **/
		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
		}

		/** Engine破棄時に呼び出される **/
		@Override
		public void onDestroy() {
			super.onDestroy();
			mHandler.removeCallbacks(drawRunnable);
		}

		/** 表示状態変更時に呼び出される **/
		@Override
		public void onVisibilityChanged(boolean visible) {
			this.visible = visible;
			if (visible) {
				drawFrame();
			} else {
				mHandler.removeCallbacks(drawRunnable);
			}
		}

		/** サーフェイス生成時に呼び出される **/
		@Override
		public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
			super.onSurfaceCreated(surfaceHolder);
		}

		/** サーフェイス変更時に呼び出される **/
		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);
			this.width = width;
			this.height = height;
			drawFrame();
		}

		/** サーフェイス破棄時に呼び出される **/
		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
			visible = false;
			mHandler.removeCallbacks(drawRunnable);
		}

		/** オフセット変更時に呼び出される **/
		@Override
		public void onOffsetsChanged(float xOffset, float yOffset, float xStep,
				float yStep, int xPixels, int yPixels) {
			drawFrame();
		}

		/** キャンバスで描画を行う **/
		private void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			Canvas c = null;
			try {
				// キャンバスをロック
				c = holder.lockCanvas();

				if (c != null) {
					// 描画
					BitmapDrawable wallpaper = (BitmapDrawable) getWallpaper();
					Bitmap bitmap = wallpaper.getBitmap();
					c.drawBitmap(bitmap, 0, 0, null);

					if (flag) {
						Calendar cal = Calendar.getInstance();
						int color1 = cal.get(Calendar.SECOND) % 10;
						int color2 = cal.get(Calendar.SECOND) / 10;
						int color3 = cal.get(Calendar.MINUTE) % 10;
						int color4 = cal.get(Calendar.MINUTE) / 10;
						int color5 = cal.get(Calendar.HOUR_OF_DAY) % 10;
						int color6 = cal.get(Calendar.HOUR_OF_DAY) / 10;

						switch (color1) {
						case 0:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image2);
							break;
						case 2:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image3);
							break;
						case 3:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image4);
							break;
						case 4:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						case 5:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image6);
							break;
						case 6:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image7);
							break;
						case 7:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image8);
							break;
						case 8:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image9);
							break;
						default:
							image1 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						switch (color2) {
						case 0:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image3);
							break;
						case 2:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						case 3:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image7);
							break;
						case 4:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image8);
							break;
						default:
							image2 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						switch (color3) {
						case 0:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image2);
							break;
						case 2:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image3);
							break;
						case 3:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image4);
							break;
						case 4:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						case 5:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image6);
							break;
						case 6:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image7);
							break;
						case 7:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image8);
							break;
						case 8:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image9);
							break;
						default:
							image3 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						switch (color4) {
						case 0:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image3);
							break;
						case 2:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						case 3:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image7);
							break;
						case 4:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image8);
							break;
						default:
							image4 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						switch (color5) {
						case 0:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image2);
							break;
						case 2:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image3);
							break;
						case 3:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image4);
							break;
						case 4:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						case 5:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image6);
							break;
						case 6:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image7);
							break;
						case 7:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image8);
							break;
						case 8:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image9);
							break;
						default:
							image5 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						switch (color6) {
						case 0:
							image6 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image1);
							break;
						case 1:
							image6 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image5);
							break;
						default:
							image6 = BitmapFactory.decodeResource(
									getResources(), R.drawable.image10);
							break;
						}

						c.drawBitmap(image1, x1, y1, null);
						c.drawBitmap(image2, x2, y2, null);
						c.drawBitmap(image3, x3, y3, null);
						c.drawBitmap(image4, x4, y4, null);
						c.drawBitmap(image5, x5, y5, null);
						c.drawBitmap(image6, x6, y6, null);
					}

					if (x1 < 0 || width - image1.getWidth() < x1) {
						vx1 = -vx1;
					}
					if (y1 < 0 || height - image1.getHeight() < y1) {
						vy1 = -vy1;
					}
					if (x2 < 0 || width - image2.getWidth() < x2) {
						vx2 = -vx2;
					}
					if (y2 < 0 || height - image2.getHeight() < y2) {
						vy2 = -vy2;
					}
					if (x3 < 0 || width - image3.getWidth() < x3) {
						vx3 = -vx3;
					}
					if (y3 < 0 || height - image3.getHeight() < y3) {
						vy3 = -vy3;
					}
					if (x4 < 0 || width - image4.getWidth() < x4) {
						vx4 = -vx4;
					}
					if (y4 < 0 || height - image4.getHeight() < y4) {
						vy4 = -vy4;
					}
					if (x5 < 0 || width - image5.getWidth() < x5) {
						vx5 = -vx5;
					}
					if (y5 < 0 || height - image5.getHeight() < y5) {
						vy5 = -vy5;
					}
					if (x6 < 0 || width - image6.getWidth() < x6) {
						vx6 = -vx6;
					}
					if (y6 < 0 || height - image6.getHeight() < y6) {
						vy6 = -vy6;
					}

					x1 += vx1;
					y1 += vy1;
					x2 += vx2;
					y2 += vy2;
					x3 += vx3;
					y3 += vy3;
					x4 += vx4;
					y4 += vy4;
					x5 += vx5;
					y5 += vy5;
					x6 += vx6;
					y6 += vy6;
				}
			} finally {
				// Canvas アンロック
				if (c != null) {
					holder.unlockCanvasAndPost(c);
				}
			}
			// 次の描画をセット
			mHandler.removeCallbacks(drawRunnable);
			if (visible) {
				mHandler.postDelayed(drawRunnable, 60);
			}
		}

		// /** タッチイベント **/
		// @Override
		// public void onTouchEvent(MotionEvent event) {
		// // 画面をタッチすると画像を切り替える
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_UP:
		// if (flag) {
		// flag = false;
		// } else {
		// flag = true;
		// }
		// }
		// }
	}
}
