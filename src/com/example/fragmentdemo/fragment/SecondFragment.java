package com.example.fragmentdemo.fragment;

import org.apache.http.protocol.ResponseDate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.fragmentdem.net.GetData;
import com.example.fragmentdem.net.GetDataFromNet;
import com.example.fragmentdem.net.onResponListener;
import com.example.fragmentdem.net.ResponVo;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.adapter.TabFragmentPagerAdapter;
import com.example.fragmentdemo.childfragment.TabOneFargment;
import com.example.fragmentdemo.childfragment.TabThreeFargment;
import com.example.fragmentdemo.childfragment.TabTwoFargment;
import com.example.fragmentdemo.views.PagerSlidingTabStrip;

public class SecondFragment extends Fragment implements onResponListener {

	private ViewPager overseas_Service_ViewPager;
	private PagerSlidingTabStrip indicator;

	private int iCurrentIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.secondfragment, null);
		iniViewt(contentView);
		return contentView;
	}

	private void GetData() {

		GetDataFromNet getDataFromNet = new GetDataFromNet(getActivity(), this,
				ResponseDate.class);

	}

	private void iniViewt(View contentView) {
		Class[] fragments = new Class[] { TabOneFargment.class,
				TabTwoFargment.class, TabThreeFargment.class };

		String[] text = { "tabone", "tabtwo", "tabthree" };
		int[] icons = { R.drawable.overseas_service_onddaytour_tab_selector,
				R.drawable.overseas_service_entrancetick_selector,
				R.drawable.overseas_service_tab_trafic_selecter };

		indicator = (PagerSlidingTabStrip) contentView
				.findViewById(R.id.oversea_service_indicator);
		setTabsValue();
		TabFragmentPagerAdapter madapter = new TabFragmentPagerAdapter(
				getChildFragmentManager(), getActivity(), fragments, text,
				icons, 1);

		overseas_Service_ViewPager = (ViewPager) contentView
				.findViewById(R.id.oversea_service_pager);
		overseas_Service_ViewPager.setAdapter(madapter);
		overseas_Service_ViewPager.setOffscreenPageLimit(2);
		indicator.setViewPager(overseas_Service_ViewPager);
		indicator.setCurrentItem(iCurrentIndex);

	}

	// 首页切换第二页的标签
	public void setCurrentTab(int iTab) {
		iCurrentIndex = iTab;
		// 切换第二fragment中的ViewPage
		// if (indicator != null) {
		// indicator.setCurrentItem(iCurrentIndex);
		// }

	}

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值。
	 */
	@SuppressLint("ResourceAsColor")
	private void setTabsValue() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		// 设置Tab是自动填充满屏幕的
		indicator.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		indicator.setDividerColor(getResources().getColor(
				R.color.divide_line_color));
		indicator.setDividerPadding((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 8, dm));
		// 设置Tab底部线的高度
		indicator.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 0, dm));
		// 设置Tab Indicator的高度
		indicator.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, dm));
		// 设置Tab标题文字的大小
		indicator.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 13, dm));
		// indicator.setTextColorResource(R.color.indicator_tab_main_text_color);
		indicator.setTextColor(R.color.indicator_tab_main_text_color);
		// 设置Tab Indicator的颜色
		indicator.setIndicatorColor(getResources().getColor(
				R.color.light_purple_color));
		// // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		// indicator.setSelectedTextColor(getResources().getColor(
		// R.color.light_purple_color));
		// 取消点击Tab时的背景色
		// indicator.setTabBackground(0);
		indicator.setLinePadding((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 10, dm));
		indicator.setIconHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 20, dm));
	}

	@Override
	public void onResponse(ResponVo responVo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorResponse(VolleyError error, ResponVo responVo) {
		// TODO Auto-generated method stub

	}
}
