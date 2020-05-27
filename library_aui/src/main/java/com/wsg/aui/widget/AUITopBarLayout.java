/*
 * Tencent is pleased to support the open source community by making Common_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wsg.aui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.wsg.aui.R;
import com.wsg.aui.util.AUIColorHelper;
import com.wsg.aui.util.AUIDrawableHelper;
import com.wsg.aui.util.AUIResHelper;
import com.wsg.aui.util.AUIStatusBarHelper;
import com.wsg.aui.util.AUIViewHelper;


/**
 * 这是一个对 {@link AUITopBar} 的代理类，需要它的原因是：
 * 我们用 fitSystemWindows 实现沉浸式状态栏后，需要将 {@link AUITopBar} 的背景衍生到状态栏后面，这个时候 fitSystemWindows 是通过
 * 更改 padding 实现的，而 {@link AUITopBar} 是在高度固定的前提下做各种行为的，例如按钮的垂直居中，因此我们需要在外面包裹一层并消耗 padding
 * <p>
 * 这个类一般是配合 {@link AUIWindowInsetLayout} 使用，并需要设置 fitSystemWindows 为 true
 * </p>
 *
 * @author cginechen
 * @date 2016-11-26
 */

public class AUITopBarLayout extends FrameLayout {
	private AUITopBar mTopBar;
	private Drawable mTopBarBgWithSeparatorDrawableCache;

	private int mTopBarSeparatorColor;
	private int mTopBarBgColor;
	private int mTopBarSeparatorHeight;

	public AUITopBarLayout(Context context) {
		this(context, null);
	}

	public AUITopBarLayout(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.AUITopBarStyle);
	}

	public AUITopBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.AUITopBar, R.attr.AUITopBarStyle, 0);
		mTopBarSeparatorColor = array.getColor(R.styleable.AUITopBar_aui_topbar_separator_color,
				ContextCompat.getColor(context, R.color.aui_config_color_separator));
		mTopBarSeparatorHeight = array.getDimensionPixelSize(R.styleable.AUITopBar_aui_topbar_separator_height, 1);
		mTopBarBgColor = array.getColor(R.styleable.AUITopBar_aui_topbar_bg_color, Color.WHITE);
		boolean hasSeparator = array.getBoolean(R.styleable.AUITopBar_aui_topbar_need_separator, true);

		// 构造一个透明的背景且无分隔线的TopBar，背景与分隔线有AUITopBarLayout控制
		mTopBar = new AUITopBar(context, true);
		mTopBar.getCommonFieldFormTypedArray(context, array);
		LayoutParams lp = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				AUIResHelper.getAttrDimen(context, R.attr.aui_topbar_height));
		addView(mTopBar, lp);

		array.recycle();
		setBackgroundDividerEnabled(hasSeparator);
		setBarLightOrDark((Activity) context, mTopBarBgColor);
	}


	public void setCenterView(View view) {
		mTopBar.setCenterView(view);
	}

	public TextView setTitle(int resId) {
		return mTopBar.setTitle(resId);
	}

	public TextView setTitle(String title) {
		return mTopBar.setTitle(title);
	}

	public TextView setEmojiTitle(String title) {
		return mTopBar.setEmojiTitle(title);
	}

	public void showTitlteView(boolean toShow) {
		mTopBar.showTitleView(toShow);
	}

	public void setSubTitle(int resId) {
		mTopBar.setSubTitle(resId);
	}

	public void setSubTitle(String subTitle) {
		mTopBar.setSubTitle(subTitle);
	}

	public void setTitleGravity(int gravity) {
		mTopBar.setTitleGravity(gravity);
	}

	public void addLeftView(View view, int viewId) {
		mTopBar.addLeftView(view, viewId);
	}

	public void addLeftView(View view, int viewId, RelativeLayout.LayoutParams layoutParams) {
		mTopBar.addLeftView(view, viewId, layoutParams);
	}

	public void addRightView(View view, int viewId) {
		mTopBar.addRightView(view, viewId);
	}

	public void addRightView(View view, int viewId, RelativeLayout.LayoutParams layoutParams) {
		mTopBar.addRightView(view, viewId, layoutParams);
	}

	public AUIAlphaImageButton addRightImageButton(int drawableResId, int viewId) {
		return mTopBar.addRightImageButton(drawableResId, viewId);
	}

	public AUIAlphaImageButton addLeftImageButton(int drawableResId, int viewId) {
		return mTopBar.addLeftImageButton(drawableResId, viewId);
	}

	public Button addLeftTextButton(int stringResId, int viewId) {
		return mTopBar.addLeftTextButton(stringResId, viewId);
	}

	public Button addLeftTextButton(String buttonText, int viewId) {
		return mTopBar.addLeftTextButton(buttonText, viewId);
	}

	public Button addRightTextButton(int stringResId, int viewId) {
		return mTopBar.addRightTextButton(stringResId, viewId);
	}

	public Button addRightTextButton(String buttonText, int viewId) {
		return mTopBar.addRightTextButton(buttonText, viewId);
	}

	public AUIAlphaImageButton addLeftBackImageButton() {
		return mTopBar.addLeftBackImageButton();
	}

	public void removeAllLeftViews() {
		mTopBar.removeAllLeftViews();
	}

	public void removeAllRightViews() {
		mTopBar.removeAllRightViews();
	}

	public void removeCenterViewAndTitleView() {
		mTopBar.removeCenterViewAndTitleView();
	}

	/**
	 * 设置 TopBar 背景的透明度
	 *
	 * @param alpha 取值范围：[0, 255]，255表示不透明
	 */
	public void setBackgroundAlpha(int alpha) {
		this.getBackground().setAlpha(alpha);
	}

	@Override
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(color);
		setBarLightOrDark((Activity) getContext(), color);
	}


	/**
	 * 根据当前 offset、透明度变化的初始 offset 和目标 offset，计算并设置 Topbar 的透明度
	 *
	 * @param currentOffset     当前 offset
	 * @param alphaBeginOffset  透明度开始变化的offset，即当 currentOffset == alphaBeginOffset 时，透明度为0
	 * @param alphaTargetOffset 透明度变化的目标offset，即当 currentOffset == alphaTargetOffset 时，透明度为1
	 */
	public int computeAndSetBackgroundAlpha(int currentOffset, int alphaBeginOffset, int alphaTargetOffset) {
		double alpha = (float) (currentOffset - alphaBeginOffset) / (alphaTargetOffset - alphaBeginOffset);
		alpha = Math.max(0, Math.min(alpha, 1)); // from 0 to 1
		int alphaInt = (int) (alpha * 255);
		this.setBackgroundAlpha(alphaInt);
		return alphaInt;
	}

	/**
	 * 设置是否要 Topbar 底部的分割线
	 *
	 * @param enabled true 为显示底部分割线，false 则不显示
	 */
	public void setBackgroundDividerEnabled(boolean enabled) {
		if (enabled) {
			if (mTopBarBgWithSeparatorDrawableCache == null) {
				mTopBarBgWithSeparatorDrawableCache = AUIDrawableHelper.
						createItemSeparatorBg(mTopBarSeparatorColor, mTopBarBgColor, mTopBarSeparatorHeight, false);
			}
			AUIViewHelper.setBackgroundKeepingPadding(this, mTopBarBgWithSeparatorDrawableCache);
		} else {
			AUIViewHelper.setBackgroundColorKeepPadding(this, mTopBarBgColor);
		}
	}

	/**
	 * 根据标题栏颜色设置状态栏是否深色模式
	 *
	 * @param context Context
	 */
	private void setBarLightOrDark(Activity context, int color) {
		boolean isLightModel = AUIColorHelper.isLightColor(color);
		try {
			if (isLightModel) {
				AUIStatusBarHelper.setStatusBarLightMode(context);
			} else {
				AUIStatusBarHelper.setStatusBarDarkMode(context);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
