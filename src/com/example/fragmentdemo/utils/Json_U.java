package com.example.fragmentdemo.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;

public class Json_U {

	public static <T> T parseJsonToObject(String respon, Class<T> r_class) {

		try {
			Log.i("Json_U", "parseJsonToObject>>>>>>>>>>>>>>>>>>" + respon);
			return JSON.parseObject(respon, r_class);

		} catch (Exception e) {
			// TODO: handle exception
			String arrayData = "\"data\":[]";
			String objectData = "\"data\":{}";
			e.printStackTrace();
			if (respon.contains(arrayData)) {
				String tempJsonStr = respon.replace(arrayData, objectData);
				try {
					return JSON.parseObject(tempJsonStr, r_class);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
		

	}

	
	public static String objToJsonStr(Object obj) {

		Log.i("Json_U", "objToJsonStr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String temp = null;
		try {
			temp = JSON.toJSONString(obj);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Log.i("Json_U", "objToJsonStr>>>>>>>>>>>" + temp);

		return temp;
	}

}
