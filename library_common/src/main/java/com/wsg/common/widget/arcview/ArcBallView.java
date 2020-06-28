package com.wsg.common.widget.arcview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wsg.common.R;
import com.wsg.common.widget.base.BaseGradientView;
import com.wsg.common.widget.particleview.ParticleView;

/**
 * 结合两个自定义视图
 *
 * @see ArcView,ArcParentView,ParticleView
 */
public class ArcBallView extends FrameLayout {
    private boolean showParticle;
    private ArcParentView mOvalParentView;
    private ParticleView mParticleView;


    public ArcBallView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ArcBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ArcBallView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater.from(context).inflate(R.layout.common_wave_arc_view, this, true);
        mParticleView = findViewById(R.id.particle_view);
        mOvalParentView = findViewById(R.id.oval_parent_view);

        mOvalParentView.bringToFront();
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.WaveBallView, defStyle, 0);

        showParticle = a.getBoolean(
                R.styleable.WaveBallView_showParticle, false);
        mParticleView.setVisibility(showParticle ? VISIBLE : GONE);
        a.recycle();
        initColor(BaseGradientView.GradientColor.BLUE);
    }

    /**
     * 统一的初始颜色
     *
     * @param gradientColor 颜色  (从帮助类中获取)
     * @see AppGlobalModel
     */
    private void initColor(BaseGradientView.GradientColor gradientColor) {
        for (int i = 0; i < mOvalParentView.getChildCount(); i++) {
            ArcView childView = (ArcView) mOvalParentView.getChildAt(i);
            childView.initGradientColor(gradientColor);
        }
        if (mParticleView.getVisibility() == VISIBLE) {
            mParticleView.initGradientColor(gradientColor);
        }

    }

    /**
     * 统一的的颜色变化
     *
     * @param gradientColor 颜色
     */
    public void changeColor(BaseGradientView.GradientColor gradientColor) {

        for (int i = 0; i < mOvalParentView.getChildCount(); i++) {
            ArcView childView = (ArcView) mOvalParentView.getChildAt(i);
            //如果当前颜色和要变换的颜色一样直接跳出循环
            if (childView.getCurrentColor() == gradientColor) {
                break;
            }
            childView.changeGradientColor(gradientColor);
        }
        //如果显示粒子在判断
        if (mParticleView.getVisibility() == VISIBLE) {
            //如果粒子颜色一样也跳过
            if (mParticleView.getCurrentColor() == gradientColor) {
                return;
            }
            mParticleView.changeGradientColor(gradientColor);
        }

    }

}
