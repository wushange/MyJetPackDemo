package com.wsg.common.widget.ovalview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class OvalParentView extends FrameLayout {
    private int mOvalSize = 8;


    public OvalParentView(Context context) {
        super(context);
    }

    public OvalParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        List<View> views = new ArrayList<>();
        for (int i = 0; i < mOvalSize; i++) {
            int startAng;
            int endAng;
            if (i % 2 == 0) {
                startAng = (int) (-360 / mOvalSize * i + (10 - (Math.random() * 20)));
                endAng = -360 + startAng;
            } else {
                startAng = (int) (360 / mOvalSize * i + (10 - (Math.random() * 20)));
                endAng = 360 + startAng;
            }
            views.add(new OvalChildView(context, startAng, endAng));
        }
//
//
//        views.add(new OvalChildView(context, 0, 360));
//        views.add(new OvalChildView(context, 72, -288));
//        views.add(new OvalChildView(context, -92, 268));
//        views.add(new OvalChildView(context, 144, -216));
//        views.add(new OvalChildView(context, -160, 200));
//        views.add(new OvalChildView(context, 216, -144));
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
        }

    }


    public void resume() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof OvalChildView) {
                OvalChildView ovalChildView = (OvalChildView) getChildAt(i);
                ovalChildView.getOvalRotateAnimator().resume();
            }
        }
    }

    public void pause() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof OvalChildView) {
                OvalChildView ovalChildView = (OvalChildView) getChildAt(i);
                ovalChildView.getOvalRotateAnimator().pause();
            }
        }
    }
}
