package com.example.fragmentdemo.fragment;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.main.MyApplication;
import com.example.fragmentdemo.utils.ShareSharedpreference_U;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThreadFragment extends Fragment {

	private MapView mapView;
	private BaiduMap baiduMap;
	private LocationClient client;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflater = getActivity().getLayoutInflater();
		View contentView = inflater.inflate(R.layout.threefragment, null);
		iniViewt(contentView);
		return contentView;
	}
	private void iniViewt(View contentView) {
		mapView = (MapView) contentView.findViewById(R.id.bmapView);
		baiduMap = mapView.getMap();
		mapView.showZoomControls(false);
		ShareSharedpreference_U sharedpreference_U = ShareSharedpreference_U
				.getInstance(getActivity().getApplicationContext(),
						getActivity().getApplicationContext().toString(),
						Context.MODE_PRIVATE);
		baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
				LocationMode.NORMAL, true, null));
		client = ((MyApplication) getActivity()
				.getApplicationContext()).getLocationClientInstence();
		baiduMap.setMaxAndMinZoomLevel(17, 17);
		baiduMap.setMyLocationEnabled(true);
		client.registerLocationListener(new ModifMapLocationListener());
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		client.setLocOption(option);
		client.start();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	public class ModifMapLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData);
			
			LatLng ll = new LatLng(location.getLatitude(),
					location.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			baiduMap.animateMapStatus(u);
		}
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		mapView.onPause();
		super.onPause();
	}
	@Override
	public void onDestroy() {
		
		client.stop();
		// 关闭定位图层
		baiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
		super.onDestroy();
	}
	@Override
	public void onResume() {
		mapView.onResume();
		super.onResume();
	}
	

}
