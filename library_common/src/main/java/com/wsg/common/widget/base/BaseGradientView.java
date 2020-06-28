package com.wsg.common.widget.base;


import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public abstract class BaseGradientView extends View {

    private int[] gradientColorFrom = new int[2];
    private int[] gradientColorTo = new int[2];

    public enum GradientColor {
        /**
         * 初始颜色：红，蓝，黄
         */
        RED, BLUE, YELLOW
    }

    public void initGradientColor(GradientColor status) {
        switch (status) {
            case RED:
                gradientColorFrom[0] = red[0];
                gradientColorFrom[1] = red[1];
                break;
            case YELLOW:
                gradientColorFrom[0] = yellow[0];
                gradientColorFrom[1] = yellow[1];
                break;
            default:
                gradientColorFrom[0] = blue[0];
                gradientColorFrom[1] = blue[1];
                break;
        }
    }

    private final int[] blue = {Color.parseColor("#29a8f1"), Color.parseColor("#285fe9")};
    private final int[] red = {Color.parseColor("#f19029"), Color.parseColor("#e92875")};
    private final int[] yellow = {Color.parseColor("#f1d729"), Color.parseColor("#e98c28")};
    private ValueAnimator endAnimator;
    private ValueAnimator startAnimator;
    private int colorAnimalDuiation = 500;

    public BaseGradientView(Context context) {
        super(context);
    }

    public BaseGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void changeGradientColor(GradientColor toColor) {
        switch (toColor) {
            case RED:
                gradientColorTo[0] = red[0];
                gradientColorTo[1] = red[1];
                break;
            case YELLOW:
                gradientColorTo[0] = yellow[0];
                gradientColorTo[1] = yellow[1];
                break;
            default:
                gradientColorTo[0] = blue[0];
                gradientColorTo[1] = blue[1];
                break;
        }
        if (startAnimator != null && endAnimator != null && startAnimator.isRunning() && endAnimator.isRunning()) {
            startAnimator.cancel();
            endAnimator.cancel();
        }
        startAnimator = ValueAnimator.ofInt(gradientColorFrom[0], gradientColorTo[0]);
        startAnimator.setDuration(colorAnimalDuiation);
        startAnimator.setEvaluator(new ArgbEvaluator());
        startAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gradientColorFrom[0] = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        endAnimator = ValueAnimator.ofInt(gradientColorFrom[1], gradientColorTo[1]);
        endAnimator.setDuration(colorAnimalDuiation);
        endAnimator.setEvaluator(new ArgbEvaluator());
        endAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gradientColorFrom[1] = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        startAnimator.start();
        endAnimator.start();
    }

    public int[] getGradientColor() {
        return gradientColorFrom;
    }

    public GradientColor getCurrentColor() {
        GradientColor gradientColor = GradientColor.BLUE;
        int currentColor = getGradientColor()[0];
        final int blueColor = blue[0];
        int redColor = red[0];
        int yellowColor = yellow[0];
        if (currentColor == blueColor) {
            gradientColor = GradientColor.BLUE;
        } else if (currentColor == yellowColor) {
            gradientColor = GradientColor.YELLOW;
        } else if (currentColor == redColor) {
            gradientColor = GradientColor.RED;
        }
        return gradientColor;
    }

}
