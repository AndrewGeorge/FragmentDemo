package com.example.fragmentdemo;

import com.example.fragmentdemo.fragment.FirstFragment;
import com.example.fragmentdemo.fragment.FourFragment;
import com.example.fragmentdemo.fragment.SecondFragment;
import com.example.fragmentdemo.fragment.ThreadFragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private TabHost tabhost;
	private TabWidget tabwidget;
	private String[] tabtitle = { "首页", "大厅", "我的", "更多" };
	private int[] icon = { R.drawable.indicator_tab_icon_home_selector,
			R.drawable.indicator_tab_icon_shop_selector,
			R.drawable.indicator_tab_icon_my_selector,
			R.drawable.indicator_tab_icon_more_selector };
	private String[] tags = { "HOME", "SHOP", "MY", "MORE" };
	private FragmentManager mFm = getSupportFragmentManager();
	private FirstFragment firstFragment;
	private SecondFragment secondFragment;
	private	ThreadFragment threadFragment;
	private FourFragment fourFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initview() {
		tabhost = (TabHost) findViewById(android.R.id.tabhost);
		tabwidget = (TabWidget) findViewById(android.R.id.tabs);
		tabhost.setup();
		tabhost.setOnTabChangedListener(tabchangelistener);
		addtab(tabtitle, icon, tags);
		tabhost.setCurrentTab(0);

	}

	private void initData() {

	}
	// 添加标签
	private void addtab(String[] tabtit, int[] icon, String[] tags) {

		for (int i = 0; i < tabtit.length; i++) {
			LinearLayout linearLayout = (LinearLayout) LayoutInflater
					.from(this).inflate(R.layout.tab_iconlayout, tabwidget,
							false);
			ImageView icon_image = (ImageView) linearLayout
					.findViewById(R.id.tab_icon_imageView);
			TextView icon_text = (TextView)linearLayout.findViewById(R.id.tab_text_textview);
			icon_text.setText(tabtit[i]);
			icon_image.setImageResource(icon[i]);
			TabHost.TabSpec spec = tabhost.newTabSpec(tags[i]);
			spec.setIndicator(linearLayout);
			spec.setContent(new DummyTabContent(getBaseContext()));
			tabhost.addTab(spec);
		}
	}
	public class DummyTabContent implements TabContentFactory {
		private Context mContext;

		public DummyTabContent(Context context) {
			mContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			View v = new View(mContext);
			return v;
		}

	}
	TabHost.OnTabChangeListener tabchangelistener = new TabHost.OnTabChangeListener() {

		@Override
		public void onTabChanged(String tabId) {

			attachTab(tabId);
		}
	};
	private void attachTab(String tabId) {
		FragmentTransaction mFt = mFm.beginTransaction();

		if (firstFragment != null) {
			mFt.hide(firstFragment);
		}
		if (secondFragment != null) {
			mFt.hide(secondFragment);
		}
		if (threadFragment != null) {
			mFt.hide(threadFragment);
		}
		if (fourFragment != null) {
			mFt.hide(fourFragment);
		}

		if (tabId.equalsIgnoreCase("HOME")) {
			attachTabVisa(mFt, "HOME");
		} else if (tabId.equalsIgnoreCase("SHOP")) {
			attachTabService(mFt, "SHOP");
		} else if (tabId.equalsIgnoreCase("MY")) {
			attachTabMyInfo(mFt, "MY");
		} else if (tabId.equalsIgnoreCase("MORE")) {
			attachTabMore(mFt, "MORE");
		}
		// 执行Fragment事务（添加。移除，替换fragment）
		mFt.commit();
	}
	
	private void attachTabVisa(FragmentTransaction mFt, String tabId) {

		if (firstFragment == null) {
			firstFragment = new FirstFragment();
			mFt.add(R.id.realtabcontent, firstFragment, "HOME");
			firstFragment.setOnTabChange(new OnBottomTabChangeListener() {

				@Override
				public void setOnTabChange(int iTab) {
					tabhost.setCurrentTab(1);
					secondFragment.setCurrentTab(iTab);

				}
			});
		} else {
			mFt.show(firstFragment);

		}
	}

	private void attachTabService(FragmentTransaction mFt, String tabId) {

		if (secondFragment == null) {
			secondFragment = new SecondFragment();
			mFt.add(R.id.realtabcontent, secondFragment, "SHOP");
		} else {
			mFt.show(secondFragment);
		}
	}

	private void attachTabMyInfo(FragmentTransaction mFt, String tabId) {

		if (threadFragment == null) {
			threadFragment = new ThreadFragment();
			mFt.add(R.id.realtabcontent, threadFragment, "MY");
		} else {
			mFt.show(threadFragment);
		}
	}

	private void attachTabMore(FragmentTransaction mFt, String tabId) {

		if (fourFragment == null) {
			fourFragment = new FourFragment();
			mFt.add(R.id.realtabcontent, fourFragment, "MORE");
		} else {
			mFt.show(fourFragment);
		}
	}
	
	public interface OnBottomTabChangeListener {

		public void setOnTabChange(int iTab);
	}

}