package com.example.fragmentdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareSharedpreference_U {

	private SharedPreferences mSharedPreferences;
	
	private static class MInstance {
		private final static ShareSharedpreference_U SHAREDPREFERENCE_U = new ShareSharedpreference_U();
	}
	public static ShareSharedpreference_U getInstance(Context context,String filename,int mode) {
		MInstance.SHAREDPREFERENCE_U.mSharedPreferences = context
				.getSharedPreferences(filename, mode);
		return MInstance.SHAREDPREFERENCE_U;
	}
	public static ShareSharedpreference_U newInstance(Context context,
			String sharedFileName, int mode) {
		ShareSharedpreference_U mSharedpreference_U = new ShareSharedpreference_U();

		mSharedpreference_U.mSharedPreferences = context.getSharedPreferences(
				sharedFileName, mode);
		return mSharedpreference_U;
	}
	
	public boolean clear(){
		Editor editor=mSharedPreferences.edit();
		editor.clear();
		return editor.commit();
	}
	public boolean putBoolean(String key,Boolean value){
		Editor editor=mSharedPreferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}
	public boolean gettBoolean(String key,Boolean defvalue){
		return mSharedPreferences.getBoolean(key, defvalue);
	}
	public boolean putString(String key,String value){
		Editor editor=mSharedPreferences.edit();
		editor.putString(key, value);
		return editor.commit();
	}
	
	public String getString(String key,String defvalue){
		return mSharedPreferences.getString(key, defvalue);
	}
	
 }
