package com.example.fragmentdemo.main;

import java.io.File;
import java.util.Stack;

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
import android.util.Log;
import android.widget.Toast;

public class MyApplication extends Application {

	static File cacheDir;
	static boolean haveSD = false;
	private Stack<Activity> activitiesStack = new Stack<Activity>();

	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		/** judg switch API***/
		if (Config.DEVELOPER_MODE
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyDeath().build());
		}

		super.onCreate();
		/**judg user weather has SD card */
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
				context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
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
}