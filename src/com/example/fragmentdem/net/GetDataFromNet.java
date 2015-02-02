/*
 * Copyright © 1999-2014 ShaoShi, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.fragmentdem.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.fragmentdemo.utils.Json_U;

/****************************************************
 * @ClassName:GetDataFromNet
 * @ClassDescription:ues this class's Object we can get data from interent
 * @version :1.0.0 @ author :dafuShao @ time :2014-9-29 15:22:46
 *****************************************************/
public class GetDataFromNet implements Listener<String>, ErrorListener {

	private Context context;
	private onResponListener listener;
	private RequestVo requestVo;
	private ResponVo responVo;
	private Class<?> r_class;
	private GetData getdData;
	private ResponListener responListener;

	/***
	 * 
	 * @param context
	 *            上下文环境
	 * @param onResponListener
	 *            网络访问类所实现的监听器
	 * @param r_class
	 *            接受返回数据类
	 */
	public GetDataFromNet(Context context, onResponListener listener,
			Class<?> r_class) {

		this.context = context;
		this.listener = listener;
		this.r_class = r_class;
		getdData = GetData.getDataInsterence(context);
		Log.i("GetDataFromNet", "GetDataFromNet>>>>>>>>>>>>>>>>>>>>");
	}

	public GetDataFromNet(Context context, ResponListener listener,
			RequestVo requestVo, Class<?> r_class) {
		this.context = context;
		this.responListener = listener;
		this.r_class = r_class;
		this.requestVo = requestVo;
		getdData = GetData.getDataInsterence(context);
	}

	@Override
	public void onErrorResponse(VolleyError error) {

		getdData.updateEndTime();
		ResponVo responVo = makeNoneRespone(r_class);
		Log.i("GetDataFromNet", "onErrorResponse>>>>>>>>>>>>>>>>>>>>");
		listener.onErrorResponse(error, responVo);

	}

	private ResponVo makeNoneRespone(Class<?> r_class2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onResponse(String response) {

		Log.i("GetDataFromNet", "getdData.getRealTime>>>>>>>>>>>>>>>>>>>>"
				+ getdData.getRealTime());
		// 请求时间的处理
		getdData.updateEndTime();
		ResponVo responVo = null;

		if (!TextUtils.isEmpty(response)) {
			String tempRespone = response;
			
				responVo = (ResponVo) Json_U
						.parseJsonToObject(tempRespone, r_class);
			
			if (responVo == null) {
				responVo = makeNoneResponseVo(r_class);
			}
		} else {
			if (responVo == null) {
				responVo = makeNoneTimeErrorResponseVo(r_class);
			}
			responVo = makeNoneResponseVo(r_class);
		}
		if (requestVo == null) {
			listener.onResponse(responVo);
		} else {
			responListener.onResponse(responVo);
		}

		Log.i("GetDataFromNet",
				"listener.onResponse(responVo)>>>>>>>>>>>>>>>>>>>>");
	}

	/***
	 * 调用访问
	 * 
	 * @param url
	 */
	public void startUrl(String url) {

		Log.i("GetDataFromNet", "startUrl>>>>>>>>>>>>>>>>>>>>" + url);
		getdData.obtaionDataformServer(this, this, url, null);

	}

	/**
	 * 使用post访问
	 * 
	 * @param url
	 */
	public void startPostUrl(String url, HashMap<String, String> postpram) {

		getdData.obtaionDataformServer(this, this, url, postpram);
	}

	private ResponVo makeNoneResponseVo(Class<?> r_clazz) {
		ResponVo responseVo = null;
		try {
			responseVo = (ResponVo) r_clazz.newInstance();
			// responseVo.setMessage(mContext
			// .getString(R.string.server_no_data_str));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return responseVo;
	}

	private ResponVo makeNoneTimeErrorResponseVo(Class<?> r_clazz) {
		ResponVo responseVo = null;
		try {
			responseVo = (ResponVo) r_clazz.newInstance();
			responseVo.setMessage("数据请求超时");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return responseVo;
	}
}
