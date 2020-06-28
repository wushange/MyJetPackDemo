package com.wsg.common.widget.ovalview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.wsg.common.widget.base.BaseGradientView;


public class OvalChildView extends BaseGradientView {

    private float[] p0, p1, p2, p3, c0, c1, c2, c3, c4, c5, c6, c7;
    private int mStartAngle = 0;
    private int mEndAngle = 360;
    private Path mOvalPath;
    private Paint mOvalPaint;
    private int mPadding = 50;
    private int contentWidth;
    private int contentHeight;
    private ObjectAnimator mOvalRotateAnimator;
    private int mOvalRotateDuration = 5000;

    public OvalChildView(Context context) {
        super(context);
        init();
    }

    public OvalChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OvalChildView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OvalChildView(Context context, int mStartAngle, int mEndAngle) {
        super(context);
        this.mStartAngle = mStartAngle;
        this.mEndAngle = mEndAngle;
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mOvalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOvalPaint.setAlpha(100);
        mOvalPath = new Path();
        mOvalPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mOvalRotateAnimator = ObjectAnimator.ofFloat(this, "rotation", mStartAngle, mEndAngle);
        mOvalRotateAnimator.setInterpolator(new LinearInterpolator());
        mOvalRotateAnimator.setDuration(mOvalRotateDuration);
        mOvalRotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        mOvalRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        contentWidth = getWidth() - paddingLeft - paddingRight;
        contentHeight = getHeight() - paddingTop - paddingBottom;
        p0 = new float[]{0 + mPadding, contentHeight / 2};
        p1 = new float[]{contentWidth / 2, 0 + mPadding};
        p2 = new float[]{contentWidth - mPadding, contentHeight / 2};
        p3 = new float[]{contentWidth / 2, contentHeight - mPadding};

        c1 = new float[]{0 + mPadding, contentHeight / 9 + mPadding};
        c2 = new float[]{contentWidth / 4 + mPadding, 0 + mPadding};

        c3 = new float[]{contentWidth * 3 / 4 + mPadding, 0 + mPadding};
        c4 = new float[]{contentWidth - mPadding, contentHeight / 9 + mPadding};

        c5 = new float[]{contentWidth - mPadding, contentHeight * 3 / 4 + mPadding};
        c6 = new float[]{contentWidth * 3 / 4 - mPadding, contentHeight - mPadding};

        c7 = new float[]{contentWidth / 8 + mPadding, contentHeight - mPadding};
        c0 = new float[]{0 + mPadding, contentHeight * 3 / 4 - mPadding};

        mOvalPath.moveTo(p0[0], p0[1]);
        cubicTo(mOvalPath, c1, c2, p1);
        cubicTo(mOvalPath, c3, c4, p2);
        cubicTo(mOvalPath, c5, c6, p3);
        cubicTo(mOvalPath, c7, c0, p0);
        canvas.save();

        LinearGradient backGradient;
        backGradient = new LinearGradient(0, 0, getWidth(), 0, getGradientColor(), null, Shader.TileMode.CLAMP);
        mOvalPaint.setShader(backGradient);
        canvas.drawPath(mOvalPath, mOvalPaint);
        canvas.restore();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mOvalRotateAnimator.isStarted()) {
            mOvalRotateAnimator.cancel();
        }
        mOvalRotateAnimator.start();
    }

    public ObjectAnimator getOvalRotateAnimator() {
        return mOvalRotateAnimator;
    }


    public void cubicTo(Path path, float[] c0, float[] c1, float[] p) {
        path.cubicTo(
                c0[0], c0[1],
                c1[0], c1[1],
                p[0], p[1]
        );
    }
}
