package com.example.fragmentdemo.views;

public interface IconPagerAdapter {
	/**
	 * Get icon representing the page at {@code index} in the adapter.
	 */
	int getIconResId(int index);

	// From PagerAdapter
	int getCount();

	/**
	 * layout ID
	 * 
	 * @return
	 */
	int getLayoutResId();

	/**
	 * textView ID
	 * 
	 * @return
	 */
	int getTextResId();

	/**
	 * 图标位置
	 * 
	 * @return
	 */
	int getIconLocation();
}
