package com.wsg.common.widget.particleview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;

import com.wsg.common.widget.base.BaseGradientView;

import java.util.Random;

public class ParticleView extends BaseGradientView {

    private int mCenterX, mCenterY;
    private Paint mDotPaint;
    private Random mRandom = new Random();
    private Dot[] mDots;
    private ValueAnimator mDotMoveAnimator;
    private @ColorInt
    int mPaintColor;
    private int mDotMovingDuration = 1500;
    private int mNumOfDot = 10;
    private int mOvalRadius = 50;
    private int mDotMaxVelocity = 4;
    private int mDotMinVelocity = 2;
    private int mDotMaxRadius = 40;
    private int mDotMinRadius = 2;
    private int mDotMinAlpha = 100;
    private int mDotMaxAplha = 255;
    private int mDotBornMargin = 30;

    public ParticleView(Context context) {
        super(context);
        init(null, 0);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ParticleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mPaintColor = Color.BLACK;
        mDotPaint = new Paint();
        mDotPaint.setColor(mPaintColor);
        mDotPaint.setAntiAlias(true);
        mDotPaint.setStyle(Paint.Style.FILL);

        setLayerType(LAYER_TYPE_HARDWARE, null);
        mDotMoveAnimator = new ValueAnimator();
        mDotMoveAnimator.setInterpolator(new LinearInterpolator());
        mDotMoveAnimator.setDuration(mDotMovingDuration);
        mDotMoveAnimator.setIntValues(0, mDotMovingDuration);
        mDotMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                for (int i = 0; i < mNumOfDot; i++) {
                    Dot dot = mDots[i];
                    dot.distance -= dot.velocity;
                    dot.alpha = (int) (dot.baseAlpha * ((double) (dot.distance - mOvalRadius) / (dot.baseDistance - mOvalRadius)));
                    if (dot.distance < mOvalRadius) {
                        randomDot(dot);
                    }
                }
                invalidate();
            }
        });
        mDotMoveAnimator.setRepeatMode(ValueAnimator.RESTART);
        mDotMoveAnimator.setRepeatCount(ValueAnimator.INFINITE);
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
        if (mDotMoveAnimator.isStarted()) {
            mDotMoveAnimator.cancel();
        }
        mDotMoveAnimator.start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mNumOfDot; i++) {
            Dot dot = mDots[i];
            LinearGradient linearGradient = new LinearGradient(getWidth(), 400, 0, 0, getGradientColor(), null, Shader.TileMode.CLAMP);
            mDotPaint.setAlpha(dot.alpha);
            mDotPaint.setShader(linearGradient);
            canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.radius, mDotPaint);
        }
    }

    public ValueAnimator getDotMoveAnimator() {
        return mDotMoveAnimator;
    }

    private void randomDot(Dot dot) {
        int w = getWidth();
        int h = getHeight();

        int minDistance = mOvalRadius + mDotBornMargin;
        int alpha = mRandom.nextInt(mDotMaxAplha - mDotMinAlpha) + mDotMinAlpha;
        double angle = Math.toRadians(mRandom.nextInt(180));
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

    static class Dot {
        public int alpha;
        public int baseAlpha;
        public int baseX;
        public int baseY;
        public int radius;
        public int velocity;
        public double angle;
        public int distance;
        public int baseDistance;

        public Dot(int baseY, int baseX) {
            this.baseY = baseY;
            this.baseX = baseX;
        }

        public int getCenterX() {
            return baseX + (int) (distance * Math.cos(angle));
        }

        public int getCenterY() {
            return baseY + (int) (distance * Math.sin(angle));
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getVelocity() {
            return velocity;
        }

        public void setVelocity(int velocity) {
            this.velocity = velocity;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getBaseY() {
            return baseY;
        }

        public void setBaseY(int baseY) {
            this.baseY = baseY;
        }

        public int getBaseX() {
            return baseX;
        }

        public void setBaseX(int baseX) {
            this.baseX = baseX;
        }

        public int getBaseAlpha() {
            return baseAlpha;
        }

        public void setBaseAlpha(int baseAlpha) {
            this.baseAlpha = baseAlpha;
        }

        public int getBaseDistance() {
            return baseDistance;
        }

        public void setBaseDistance(int baseDistance) {
            this.baseDistance = baseDistance;
        }
    }

}
