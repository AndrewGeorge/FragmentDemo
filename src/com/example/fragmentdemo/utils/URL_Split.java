package com.example.fragmentdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.fragmentdemo.RequestData.GetWeatherRequestDataVo;

public class URL_Split {
	private static String tag="URL_Split";
	public static String split(final Context mContext,
			final GetWeatherRequestDataVo requestVo, final String orgUrl) {
		String url = null;
			if (TextUtils.isEmpty(requestVo.getCity())) {
				url=orgUrl+requestVo.getCity();
				Log_U.Log_d(tag, "split>>>>>>>>>");
			Toast.makeText(mContext, "网络访问出错啦!", 0).show();
			Log_U.Log_d(tag, "split>>>>>>>>>");
		}
		
		return TextUtils.isEmpty(url)?"":url;
	}
}
