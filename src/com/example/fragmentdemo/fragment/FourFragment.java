package com.example.fragmentdemo.fragment;

import com.example.fragmentdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FourFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.fourfragment, null);
		iniViewt(contentView);
		return contentView;
	}

	private void iniViewt(View contentView) {
		// TODO Auto-generated method stub

	}

}
