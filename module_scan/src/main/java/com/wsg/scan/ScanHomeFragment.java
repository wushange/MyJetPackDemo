package com.wsg.scan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.wsg.library_fragmentation.swipeback.SwipeBackFragment;
import com.wsg.scan.R;
import com.wsg.scan.ui.ScanStartFragment;


public class ScanHomeFragment extends SwipeBackFragment {

	private Button mBtnStart;


	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_scan_home, container, false);
		mBtnStart = root.findViewById(R.id.btn_start);
		mBtnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				start(new ScanStartFragment());
			}
		});
		if(BuildConfig.BuildModule){
			setSwipeBackEnable(false);
		}
		return attachToSwipeBack(root);
	}
}
