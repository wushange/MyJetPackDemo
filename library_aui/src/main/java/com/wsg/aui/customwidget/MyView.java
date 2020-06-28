package com.wsg.aui.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * TODO: document your custom view class.
 */
@SuppressWarnings("ALL")
public class MyView extends View {

	private Path path;
	private float[] p0, p1, p2, p3, c0, c1, c2, c3, c4, c5, c6, c7;
	public MyView(Context context) {
		super(context);
		init();
	}

	public MyView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private int mPointStrokeWidth;

	private void init() {
		mPointStrokeWidth = 20;
	}



	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int w = 100;
		int h = 100;

		if(widthMode != MeasureSpec.AT_MOST) {
			w = widthSize;
		}

		if(heightMode != MeasureSpec.AT_MOST) {
			h = heightSize;
		}

		int resultSize = w > h
				? h : w;

		setMeasuredDimension(resultSize, resultSize);


		p0 = new float[]{0, h / 2};
		p1 = new float[]{w / 2, 0};
		p2 = new float[]{w, h / 2};
		p3 = new float[]{w / 2, h};


		c0 = new float[]{0, h * 3 / 4};
		c1 = new float[]{0, h / 9};
		c2 = new float[]{w / 4, 0};
		c3 = new float[]{w * 3 / 4, 0};
		c4 = new float[]{w, h / 9};
		c5 = new float[]{w, h * 3 / 4};
		c6 = new float[]{w * 3 / 4, h};
		c7 = new float[]{w / 8, h};




	}
	private void cubicTo(Path path, float[] c0, float[] c1, float[] p) {
		path.cubicTo(
				c0[0], c0[1],
				c1[0], c1[1],
				p[0], p[1]
		);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		path = new Path();
		path.moveTo(p0[0], p0[1]);
		cubicTo(path, c1, c2, p1);
		cubicTo(path, c3, c4, p2);
		cubicTo(path, c5, c6, p3);
		cubicTo(path, c7, c0, p0);

	}


}
