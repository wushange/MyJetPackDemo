package com.wsg.common.widget.arcview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class ArcParentView extends FrameLayout {
    private int mOvalSize = 8;


    public ArcParentView(Context context) {
        super(context);
    }

    public ArcParentView(Context context, AttributeSet attrs) {
        super(context, attrs);

        List<View> views = new ArrayList<>();
//        for (int i = 0; i < mOvalSize; i++) {
//            int startAng;
//            int endAng;
//            if (i % 2 == 0) {
//                startAng = (int) (360 / mOvalSize * i);
//                endAng = -(360 - startAng);
//            } else {
//                startAng = -(int) (360 / mOvalSize * i);
//                endAng = 360 - startAng;
//            }
//            views.add(new ArcView(context, startAng, endAng));
//        }
        views.add(new ArcView(context, 0, 360));
        views.add(new ArcView(context, 72, -288));
//        views.add(new ArcView(context, -72, 288));
        views.add(new ArcView(context, 144, -216));
//        views.add(new ArcView(context, -144, 216));
        views.add(new ArcView(context, 216, -144));
//        views.add(new ArcView(context, -216, 144));
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
        }

    }


}
