package com.wsg.common.widget.arcview;

import android.graphics.RectF;

/**
 * Created by legendmohe on 16/5/22.
 */
public class Arc {
    public RectF mRectF;
    public RectF mOriginalRectF;
    public float mRotateAngle;

    public Arc(RectF rectF, float rotateAngle) {
        mRectF = rectF;
        mOriginalRectF = new RectF(rectF);
        mRotateAngle = rotateAngle;
    }
}
