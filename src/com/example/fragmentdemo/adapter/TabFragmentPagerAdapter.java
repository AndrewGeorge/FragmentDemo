package com.example.fragmentdemo.adapter;

import com.example.fragmentdemo.views.IconPagerAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter
		implements IconPagerAdapter {
	private Context mContext;
	private Class[] fragments;
	private String[] text;
	private int[] icon;
	private int iLayoutResId;
	private int iTextResId;
	private int iIconLocation;

	// private Fragment[] fragmentArr;

	/**
	 * 没有icon的tab
	 * 
	 * @param fm
	 * @param context
	 * @param fragments
	 *            fragment数组
	 * @param text
	 *            tab文案数组
	 * @param layoutResId
	 *            layout ID
	 * @param textResId
	 *            testview ID
	 */
	public TabFragmentPagerAdapter(FragmentManager fm, Context context,
			Class[] fragments, String[] text, int layoutResId, int textResId) {
		super(fm);
		mContext = context;
		this.fragments = fragments;
		this.text = text;
		this.iLayoutResId = layoutResId;
		this.iTextResId = textResId;
	}

	/**
	 * 有icon的tab
	 * 
	 * @param fm
	 * @param context
	 * @param fragments
	 *            fragment数组
	 * @param text
	 *            tab文案数组
	 * @param icon
	 *            tab 图标数组
	 * @param layoutResId
	 *            layout ID
	 * @param textResId
	 *            testview ID
	 * @param iconLocation
	 *            icon位置：1-left，2-top，3-right，4-bottom
	 */
	public TabFragmentPagerAdapter(FragmentManager fm, Context context,
			Class[] fragments, String[] text, int[] icon, int layoutResId,
			int textResId, int iconLocation) {
		super(fm);
		mContext = context;
		this.fragments = fragments;
		this.text = text;
		this.icon = icon;
		this.iLayoutResId = layoutResId;
		this.iTextResId = textResId;
		this.iIconLocation = iconLocation;
	}
	/**
	 * 代码实现tab布局
	 * 
	 * @param fm
	 * @param context
	 * @param fragments
	 * @param text
	 * @param icon
	 * @param iconLocation
	 */
	public TabFragmentPagerAdapter(FragmentManager fm, Context context,
			Class[] fragments, String[] text, int[] icon, int iconLocation) {
		super(fm);
		mContext = context;
		this.fragments = fragments;
		this.text = text;
		this.icon = icon;
		this.iIconLocation = iconLocation;
	}
	@Override
	public Fragment getItem(int position) {
		// return TestFragment.newInstance(text[position % text.length]);
		Fragment mFragment = null;
		if (fragments != null) {
			mFragment = Fragment.instantiate(mContext,
					fragments[position].getName(), null);
		}
		return mFragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {

		return text[position % text.length].toUpperCase();
	}

	@Override
	public int getCount() {
		return text.length;
	}

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		if (icon != null) {
			return icon[index];
		}
		return 0;// 没有图标
	}

	@Override
	public int getLayoutResId() {
		// TODO Auto-generated method stub
		return iLayoutResId;
	}

	@Override
	public int getTextResId() {
		// TODO Auto-generated method stub
		return iTextResId;
	}

	@Override
	public int getIconLocation() {
		// TODO Auto-generated method stub
		return iIconLocation;
	}

}
