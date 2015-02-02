package com.example.fragmentdemo.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.fragmentdem.net.GetDataFromNet;
import com.example.fragmentdem.net.ResponVo;
import com.example.fragmentdem.net.onResponListener;
import com.example.fragmentdemo.MainActivity.OnBottomTabChangeListener;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.RequestData.GetWeatherRequestDataVo;
import com.example.fragmentdemo.ResponseData.GetWeatherResponseDataVo;
import com.example.fragmentdemo.main.MyApplication;
import com.example.fragmentdemo.utils.Constants;
import com.example.fragmentdemo.utils.Log_U;
import com.example.fragmentdemo.utils.ShareSharedpreference_U;
import com.example.fragmentdemo.utils.URL_Split;
import com.example.fragmentdemo.utils.WeatherConnect;

@SuppressLint("NewApi")
public class FirstFragment extends Fragment implements onResponListener{

	private String tag="FirstFragment";
	private TextView test;
	private OnBottomTabChangeListener mOnBottomTabChangeListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.firstfragment, null);
		iniViewt(contentView);
		initdata();
		return contentView;
	}

	private void iniViewt(View contentView) {
		test = (TextView) contentView.findViewById(R.id.test);
		((MyApplication) getActivity().getApplication()).location = test;
		ShareSharedpreference_U sharedpreference_U = ShareSharedpreference_U
				.getInstance(getActivity(), getActivity()
						.getApplicationContext().toString(),
						Context.MODE_PRIVATE);
		String city = sharedpreference_U.getString("CITY", "");
		String lon = sharedpreference_U.getString("LON", "");
		String lat = sharedpreference_U.getString("LAT", "");
		test.setText(city + lon + lat);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public void setOnTabChange(
			OnBottomTabChangeListener onBottomTabChangeListener) {
		mOnBottomTabChangeListener = onBottomTabChangeListener;
	}

	private void initdata() {
		Log_U.Log_d(tag, "initdata>>>>>>>>>");
		GetDataFromNet getDataFromNet = new GetDataFromNet(getActivity(), this,
				GetWeatherResponseDataVo.class);
		getDataFromNet.startUrl("http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC");
		
//		String str=WeatherConnect.getStringResponse("http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC", 500);
//		Log_U.Log_i(tag, str);
		
	}
	@Override
	public void onResponse(ResponVo responVo) {
		if (responVo instanceof GetWeatherResponseDataVo) {
			GetWeatherResponseDataVo getWeatherResponseDataVo =(GetWeatherResponseDataVo) responVo;
			if (getWeatherResponseDataVo.getStatus().toString()=="1000") {
				updata(getWeatherResponseDataVo);
			}
		}
		
	}

	@Override
	public void onErrorResponse(VolleyError error, ResponVo responVo) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "返回的数据出错啦！", 0).show();
	}
	
	private void updata(GetWeatherResponseDataVo getWeatherResponseDataVo) {
		test.setText(getWeatherResponseDataVo.getData().getCity().toString());
	}
}
