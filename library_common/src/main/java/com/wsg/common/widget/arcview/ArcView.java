package com.wsg.common.widget.arcview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.wsg.common.widget.base.BaseGradientView;


public class ArcView extends BaseGradientView {
    private Paint mOvalPaint;
    private int mNumOfOval = 1;
    private ValueAnimator mOvalRotateAnimator;
    private int mCenterX, mCenterY;
    private int mPadding = 80;
    private int mOvalRadius = 400;
    private int mOvalRegionWidth = mOvalRadius * 2 - 60;
    private int mOvalRegionHeight = mOvalRadius * 2 + 30;
    private int mOvalRotateDuration = 5000;
    private int mStartAngle = 0;
    private int mEndAngle = 360;
    private Arc[] mArcs;


    public ArcView(Context context) {
        super(context);
        setup();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }


    public ArcView(Context context, int mStartAngle, int mEndAngle) {
        super(context);
        this.mStartAngle = mStartAngle;
        this.mEndAngle = mEndAngle;
        setup();
    }


    private void setup() {
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mOvalPaint = new Paint();
        mOvalPaint.setAlpha(150);
        mOvalPaint.setAntiAlias(true);
        mOvalPaint.setStyle(Paint.Style.FILL);
        mOvalRotateAnimator = ObjectAnimator.ofFloat(this, "rotation", mStartAngle, mEndAngle);
        mOvalRotateAnimator.setInterpolator(new LinearInterpolator());
        mOvalRotateAnimator.setDuration(mOvalRotateDuration);
        mOvalRotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        mOvalRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2;
        mCenterY = h / 2;
        mOvalRadius = mCenterY - mPadding;
        if (mOvalRadius > mCenterX - mPadding) {
            mOvalRadius = mCenterX - mPadding;
        }
        mOvalRegionWidth = mOvalRadius * 2 - 60;
        mOvalRegionHeight = mOvalRadius * 2 + 30;
        setupDrawables(w, h);
        if (mOvalRotateAnimator.isStarted()) {
            mOvalRotateAnimator.cancel();
        }
        mOvalRotateAnimator.start();
    }

    private void setupDrawables(int w, int h) {
        mArcs = new Arc[2];
        for (int i = 0; i < 2; i++) {
            RectF rectF = new RectF(
                    mCenterX - mOvalRegionWidth / 2,
                    mCenterY - mOvalRegionHeight / 2,
                    mCenterX + mOvalRegionWidth / 2,
                    mCenterY + mOvalRegionHeight / 2);
            mArcs[i] = new Arc(rectF, 0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = mNumOfOval - 1; i >= 0; i--) {
            Arc arc = mArcs[i];
            LinearGradient backGradient;
            backGradient = new LinearGradient(0, 0, getWidth(), 0, getGradientColor(), null, Shader.TileMode.CLAMP);
            mOvalPaint.setShader(backGradient);
            canvas.drawOval(arc.mRectF, mOvalPaint);
        }
    }


}
