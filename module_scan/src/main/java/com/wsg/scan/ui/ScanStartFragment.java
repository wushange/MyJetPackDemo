package com.wsg.scan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.wsg.library_fragmentation.swipeback.SwipeBackFragment;
import com.wsg.scan.R;


public class ScanStartFragment extends SwipeBackFragment {

	private android.widget.Button btnstart;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_scan_home, container, false);
		this.btnstart = (Button) root.findViewById(R.id.btn_start);
		return attachToSwipeBack(root);
	}
}
