package com.wsg.common.widget.ovalview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wsg.common.R;
import com.wsg.common.widget.base.BaseGradientView;
import com.wsg.common.widget.particleview.ParticleView;


/**
 * TODO: document your custom view class.
 */
public class WaveBallView extends FrameLayout {
    private boolean showParticle;
    private OvalParentView mOvalParentView;
    private ParticleView mParticleView;


    public WaveBallView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public WaveBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public WaveBallView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater.from(context).inflate(R.layout.common_wave_ball_view, this, true);
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
    }

    public void changeColor(BaseGradientView.GradientColor gradientColor) {
        for (int i = 0; i < mOvalParentView.getChildCount(); i++) {
            OvalChildView childView = (OvalChildView) mOvalParentView.getChildAt(i);
            childView.changeGradientColor(gradientColor);
        }
        mParticleView.changeGradientColor(gradientColor);

    }

}
