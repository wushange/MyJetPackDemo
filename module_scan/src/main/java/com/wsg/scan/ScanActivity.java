package com.wsg.scan;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wsg.library_fragmentation.base.SupportActivity;
import com.wsg.scan.R;
import com.blankj.utilcode.util.BarUtils;

public class ScanActivity extends SupportActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BarUtils.transparentStatusBar(this);
		setContentView(R.layout.activity_main);
		ScanHomeFragment homeFragment = findFragment(ScanHomeFragment.class);

		if (homeFragment == null) {
			homeFragment = new ScanHomeFragment();
		}
		loadRootFragment(R.id.ffff, homeFragment);
	}
}
