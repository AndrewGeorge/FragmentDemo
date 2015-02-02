package com.example.fragmentdemo.main;

import java.io.File;
import java.util.Stack;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.example.fragmentdemo.utils.Log_U;
import com.example.fragmentdemo.utils.ShareSharedpreference_U;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyApplication extends Application {
	static File cacheDir;
	static boolean haveSD = false;
	private String tag = "MyApplication";
	public LocationClient mLocationClient;
	public TextView location;
	private Stack<Activity> activitiesStack = new Stack<Activity>();

	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		SDKInitializer.initialize(this);
		initLocationData();
		/** judg switch API ***/
		if (Config.DEVELOPER_MODE
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyDeath().build());
		}
		/** judg user weather has SD card */
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			haveSD = true;
		} else {
			Toast.makeText(MyApplication.this, "your phone don't hase sd card",
					Toast.LENGTH_LONG).show();
		}
		cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),
				"imageloader/Cache");
		initImageLoader(getApplicationContext());
		super.onCreate();
	}

	public void addAppInstance(Activity activity) {
		if (!activitiesStack.empty()) {
			Activity mActivity = activitiesStack.lastElement();
			String lastSimpleName = mActivity.getPackageName()
					+ mActivity.getClass().getSimpleName();
			String simpleName = activity.getPackageName()
					+ activity.getClass().getSimpleName();
			if (lastSimpleName.endsWith(simpleName)) {
				delAppinstance(activity);
				return;
			}
			activitiesStack.add(activity);
		}
	}

	public void delAppinstance(Activity activity, boolean isFinish) {

		Log.i("MyApplication", activity.getClass().getName());
		activitiesStack.remove(activity);
		if (isFinish) {
			activity.fileList();
		}
	}

	public void delAppinstance(Activity activity) {
		Log.i("MyApplication", activity.getClass().getName());
		activitiesStack.remove(activity);
		activity.fileList();
	}

	public static void initImageLoader(Context context) {

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCache(new UnlimitedDiscCache(cacheDir))
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}

	private void exit() {
		for (int i = 0; i < activitiesStack.size(); i++) {
			Activity activity = activitiesStack.get(i);
			if (activity != null) {
				activity.finish();
			}
		}
		activitiesStack.clear();
	}

	/**
	 * 定位初始化
	 */
	private void initLocationData() {
		// 定位
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationClient.registerLocationListener((BDLocationListener) new Locatiolistener());
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}
	public LocationClient getLocationClientInstence(){
		if (mLocationClient==null) {
			mLocationClient=new LocationClient(this);
		}
		return mLocationClient;
	}

	class Locatiolistener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation arg0) {
			Log_U.Log_d(tag, "onResponse");
			String city;
			double lon;
			double lat;
			if (arg0 == null) {
				Toast.makeText(getApplicationContext(), "定位失败，请手动定位!", 0);
				return;
			}
			if (arg0 != null) {
				city = arg0.getCity();
				lon = arg0.getLongitude();
				lat = arg0.getLatitude();
				ShareSharedpreference_U sharedpreference_U = ShareSharedpreference_U
						.getInstance(getApplicationContext(),
								getApplicationContext().toString(),
								Context.MODE_PRIVATE);
				if (!TextUtils.isEmpty(city)) {
					sharedpreference_U.putString("CITY", city);
				}
				sharedpreference_U.putString("LON", String.valueOf(lon));
				sharedpreference_U.putString("LAT", String.valueOf(lat));
				if (location != null) {
					location.setText(city + lon + lat);
				}
				mLocationClient.stop();
			}
		}
	}
}