package com.example.fragmentdemo.childfragment;

import com.example.fragmentdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabThreeFargment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(
				R.layout.tabthreefragment, null);
		iniViewt(contentView);
		return contentView;
	}
	
	private void iniViewt(View contentView) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
}
