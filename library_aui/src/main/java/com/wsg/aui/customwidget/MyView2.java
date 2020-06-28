package com.wsg.aui.customwidget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.ColorInt;

import com.wsg.aui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class MyView2 extends FrameLayout {
	private String TAG = "MyView2";
	private int circleNumber = 10;
	private int rotateDuiation = 5000;
	private @ColorInt
	int mPaintColor;
	private Paint mDotPaint;
	List<ObjectAnimator> rotaAnimators = new ArrayList<>();
	List<Float> rataedAng = new ArrayList<>();
	private Random mRandom = new Random();
	private AnimatorSet mAnimatorSet;

	private Oval[] mOvals;
	private Dot[] mDots;
	private int mCenterX, mCenterY;

	private int mNumOfDot = 30;
	private int mOvalRadius = 300;
	private int mDotMaxVelocity = 4;
	private int mDotMinVelocity = 2;
	private int mDotMaxRadius = 8;
	private int mDotMinRadius = 2;
	private int mDotMinAlpha = 100;
	private int mDotMaxAplha = 255;
	private int mDotBornMargin = 30;
	Bitmap bitmapPaint;
	private Rect mSrcRect, mDestRect;

	public MyView2(Context context) {
		super(context);
	}

	private FrameLayout mCircleLayout;

	public MyView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sample_my_view2, this, true);
		mCircleLayout = findViewById(R.id.circle_layout);
		initDraw(context);
		mPaintColor = Color.RED;
		mDotPaint = new Paint();
		mDotPaint.setColor(mPaintColor);
		mDotPaint.setAntiAlias(true);
		mDotPaint.setStyle(Paint.Style.FILL);

//		mDotMoveAnimator = new ValueAnimator();
//		mDotMoveAnimator.setInterpolator(new LinearInterpolator());
//		mDotMoveAnimator.setDuration(mDotMovingDuration);
//		mDotMoveAnimator.setIntValues(0, mDotMovingDuration);
//		mDotMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				for (int i = 0; i < mNumOfDot; i++) {
//					Dot dot = mDots[i];
//					dot.distance -= dot.velocity;
//					dot.alpha = (int) (dot.baseAlpha * ((double) (dot.distance - mOvalRadius) / (dot.baseDistance - mOvalRadius)));
//					if (dot.distance < mOvalRadius) {
//						randomDot(dot);
//					}
//				}
//				invalidate();
//			}
//		});
//		mDotMoveAnimator.setRepeatMode(ValueAnimator.RESTART);
//		mDotMoveAnimator.setRepeatCount(ValueAnimator.INFINITE);


	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCenterX = w / 2;
		mCenterY = h / 2;
		mDots = new Dot[mNumOfDot];
		for (int i = 0; i < mNumOfDot; i++) {
			mDots[i] = new Dot(mCenterX, mCenterY);
			randomDot(mDots[i]);
		}
	}

	private void initDraw(Context context) {
		mCircleLayout.removeAllViews();
		for (int i = 0; i < circleNumber; i++) {
			int startAng = (int) (360 / circleNumber * i + (10 - (Math.random() * 20)));
			ImageView imageView = new ImageView(context);
			LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.gravity = Gravity.CENTER;
			imageView.setLayoutParams(layoutParams);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.bbbb));
			mCircleLayout.addView(imageView);
			ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", startAng, i % 2 == 0 ? 360 + startAng : -360 + startAng);
//			ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", startAng,   360 + startAng );
			objectAnimator.setInterpolator(new LinearInterpolator());
			objectAnimator.setDuration(rotateDuiation);
			objectAnimator.setRepeatMode(ValueAnimator.RESTART);
			objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
			objectAnimator.start();
			rotaAnimators.add(objectAnimator);

		}


	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mNumOfDot; i++) {
			Dot dot = mDots[i];
			mDotPaint.setAlpha(255);
			canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.radius, mDotPaint);
		}
	}

	private void randomDot(Dot dot) {
		int w = getWidth();
		int h = getHeight();

		int minDistance = mOvalRadius + mDotBornMargin;
		int alpha = mRandom.nextInt(mDotMaxAplha - mDotMinAlpha) + mDotMinAlpha;
		double angle = Math.toRadians(mRandom.nextInt(360));
		int distance = mRandom.nextInt((int) Math.sqrt(w / 2.0 * w / 2.0 + h / 2.0 * h / 2.0) - minDistance) + minDistance;
		int velocity = mRandom.nextInt(mDotMaxVelocity - mDotMinVelocity) + mDotMinVelocity;
		int radius = mRandom.nextInt(mDotMaxRadius - mDotMinRadius) + mDotMinRadius;

		dot.setAlpha(alpha);
		dot.setBaseAlpha(alpha);
		dot.setAngle(angle);
		dot.setDistance(distance);
		dot.setBaseDistance(distance);
		dot.setVelocity(velocity);
		dot.setRadius(radius);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//获取手指在屏幕上的坐标
		float x = event.getX();
		float y = event.getY();
		//获取手指的操作--》按下、移动、松开
		int action = event.getAction();
		switch (action) {
			case MotionEvent.ACTION_DOWN://按下
				Log.e(TAG, "ACTION_DOWN");
				for (int i = 0; i < rotaAnimators.size(); i++) {
					ObjectAnimator objectAnimator = rotaAnimators.get(i);
					objectAnimator.pause();
					rataedAng.add((Float) objectAnimator.getAnimatedValue());
					Log.e(TAG, "getAnimatedValue" + objectAnimator.getAnimatedValue());
				}
				break;
			case MotionEvent.ACTION_MOVE://移动
				Log.e(TAG, "ACTION_MOVE");
				for (int i = 0; i < mCircleLayout.getChildCount(); i++) {
					ImageView imageView = (ImageView) mCircleLayout.getChildAt(i);
					if (imageView != null) {
//						float startAng = rataedAng.get(i);
//						ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", startAng, i % 2 == 0 ? 360 + startAng : -360 - startAng);
//						objectAnimator.setInterpolator(new LinearInterpolator());
//						objectAnimator.setDuration(500);
//						objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//						objectAnimator.setRepeatCount(ValueAnimator.REVERSE);
//						objectAnimator.start();
					}
				}
				break;
			case MotionEvent.ACTION_UP://松开
				Log.e(TAG, "ACTION_UP");
//				initDraw(getContext());
				for (int i = 0; i < rotaAnimators.size(); i++) {
					ObjectAnimator objectAnimator = rotaAnimators.get(i);
					objectAnimator.resume();
				}
				break;
			default:
		}
		return true;
	}
}
