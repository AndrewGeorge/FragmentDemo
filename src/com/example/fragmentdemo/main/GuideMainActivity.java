package com.example.fragmentdemo.main;

import java.util.ArrayList;

import com.example.fragmentdemo.MainActivity;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.utils.Constants;
import com.example.fragmentdemo.utils.ShareSharedpreference_U;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class GuideMainActivity extends Activity {

	private int[] pageInt = new int[] { R.drawable.guid_first_pic,
			R.drawable.guid_second_pic, R.drawable.guid_thread_pic };
	private ViewPager guide_viewpager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guid_layout);
		initView();
	}

	private void initView() {
		guide_viewpager = (ViewPager) findViewById(R.id.guide_viewpager);
		guide_viewpager.setAdapter(new BannerPageAdapter(this));
	}

	// 轮播图片的数据填充器
	public class BannerPageAdapter extends PagerAdapter {
		Context context;
		private LayoutInflater mInflater;
		private ArrayList<View> mPageViewList;

		public BannerPageAdapter(Context context) {
			mPageViewList = new ArrayList<View>();
			this.context = context;
			if (mInflater == null) {
				mInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			for (int i = 0; i < pageInt.length; i++) {
				View view = mInflater.inflate(
						R.layout.item_guid_viewpage_layout, null);
				mPageViewList.add(view);
			}
		}

		@Override
		public int getCount() {
			return pageInt != null ? pageInt.length : 0;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mPageViewList.get(position));

			ImageView item_detail_data_networkImageView = (ImageView) mPageViewList
					.get(position).findViewById(R.id.item_guid_imageview);
			TextView start_skip = (TextView) mPageViewList.get(position)
					.findViewById(R.id.item_guid_viewpage_start_skip);
			start_skip.setOnClickListener(clicklistener);
			View bottom = mPageViewList.get(position).findViewById(
					R.id.item_guid_viewpage_bottom);
			Button start_experience = (Button) mPageViewList.get(position)
					.findViewById(R.id.item_guid_viewpage_start_experience);
			start_experience.setOnClickListener(clicklistener);
			item_detail_data_networkImageView
					.setScaleType(ScaleType.CENTER_CROP);
			item_detail_data_networkImageView
					.setImageResource(pageInt[position]);
			if (position == 2) {
				bottom.setVisibility(View.VISIBLE);
			} else {
				bottom.setVisibility(View.GONE);
			}
			return mPageViewList.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mPageViewList.get(position));
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

	}

	OnClickListener clicklistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setFirstStart();
			startActivity(new Intent(GuideMainActivity.this,MainActivity.class));
			finish();
		}
	};

	private void setFirstStart() {
		ShareSharedpreference_U sharedpreference_U = ShareSharedpreference_U
				.getInstance(this, Constants.FirstStart, Context.MODE_PRIVATE);
		sharedpreference_U.putBoolean(Constants.isFirstStr, false);
	}
}
