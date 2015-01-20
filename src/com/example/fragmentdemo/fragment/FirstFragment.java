package com.example.fragmentdemo.fragment;

import com.example.fragmentdemo.MainActivity.OnBottomTabChangeListener;
import com.example.fragmentdemo.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class FirstFragment extends Fragment {

	private OnBottomTabChangeListener mOnBottomTabChangeListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(
				R.layout.firstfragment, null);
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
	
	public void setOnTabChange(
			OnBottomTabChangeListener onBottomTabChangeListener) {
		mOnBottomTabChangeListener = onBottomTabChangeListener;
	}

}
