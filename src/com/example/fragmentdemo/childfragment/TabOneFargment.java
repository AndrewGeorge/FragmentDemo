package com.example.fragmentdemo.childfragment;

import java.util.ArrayList;
import java.util.List;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.utils.ImageLoad_Uils;
import com.example.fragmentdemo.viewflow.CircleFlowIndicator;
import com.example.fragmentdemo.viewflow.ViewFlow;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TabOneFargment extends Fragment {

	private ViewFlow viewflow;
	private CircleFlowIndicator flowindicator;
	private View fragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.tabonefragment, null);
		iniViewt(contentView);
		ArrayList<String> Banners = new ArrayList<String>();
		Banners.add("http://c.hiphotos.baidu.com/image/pic/item/78310a55b319ebc4558c5b968026cffc1f1716cc.jpg");
		Banners.add("http://c.hiphotos.baidu.com/image/pic/item/b21bb051f8198618c4fcc28b48ed2e738bd4e602.jpg");
		Banners.add("http://g.hiphotos.baidu.com/image/w%3D230/sign=ef538fe2a344ad342ebf8084e0a30c08/f11f3a292df5e0fee755a40e5e6034a85edf726b.jpg");
		updateBanner(Banners);
		return contentView;
	}

	private void iniViewt(View contentView) {
		// TODO Auto-generated method stub
		viewflow = (ViewFlow) contentView.findViewById(R.id.viewflow);
		flowindicator = (CircleFlowIndicator) contentView
				.findViewById(R.id.viewflowindic);
		fragment = contentView.findViewById(R.id.framelayout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public class ImageAdapter extends BaseAdapter {
		// private DataTransfer mDataTransfer;
		private ArrayList<String> mItemDataList;
		private LayoutInflater mInflater;

		public ImageAdapter(Context context, ArrayList<String> data) {
			// mContext = context;
			// mDataTransfer = DataTransfer.getDataTransferInstance(context);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mItemDataList = data;
		}

		@Override
		public int getCount() {
			if (mItemDataList.size() > 1) {
				return Integer.MAX_VALUE; // 返回很大的值使得getView中的position不断增大来实现循环
			}
			return mItemDataList.size();
		}

		@Override
		public String getItem(int position) {
			return mItemDataList.get(position % mItemDataList.size());
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_banner, null);
			}
			ImageView item_banner_imageview = (ImageView) convertView
					.findViewById(R.id.item_banner_imageview);
			String imageUrl = getItem(position);
			imageUrl = TextUtils.isEmpty(imageUrl) ? "" : imageUrl;
			ImageLoad_Uils.setImage(item_banner_imageview, imageUrl, true,
					ScaleType.CENTER_CROP, R.drawable.ic_launcher,
					getActivity());
			return convertView;
		}

	}

	private void updateBanner(final ArrayList<String> mBanners) {
		if (mBanners == null) {
			return;
		}
		ImageAdapter mAdapter = new ImageAdapter(getActivity(), mBanners);
		viewflow.setAdapter(mAdapter);
		viewflow.setmSideBuffer(mBanners.size()); // 实际图片张数，
													// 我的ImageAdapter实际图片张数为3
		if (mBanners.size() > 1) {
			viewflow.setFlowIndicator(flowindicator);
			viewflow.setTimeSpan(3000);
			viewflow.setViewGroup((ViewGroup) fragment);
			viewflow.setSelection(3 * 1000); // 设置初始位置
			viewflow.startAutoFlowTimer(); // 启动自动播放
		} else {
			flowindicator.setVisibility(View.GONE);
		}
	}
}
