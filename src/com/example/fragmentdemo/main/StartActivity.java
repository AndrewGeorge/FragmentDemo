package com.example.fragmentdemo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fragmentdemo.MainActivity;
import com.example.fragmentdemo.R;
import com.example.fragmentdemo.utils.Constants;
import com.example.fragmentdemo.utils.ShareSharedpreference_U;

public class StartActivity extends Activity {

	private boolean isfirst;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_start);
		isfirst=getisfirst();
		if (isfirst) {
			gotoGuideView();
			finish();
		}else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		super.onCreate(savedInstanceState);
	}

	private void gotoGuideView() {
		Intent intent = new Intent(this, GuideMainActivity.class);
		startActivity(intent);
	}

	
	private Boolean getisfirst() {
		ShareSharedpreference_U sharedpreference_U = ShareSharedpreference_U
				.getInstance(this, Constants.FirstStart, Context.MODE_PRIVATE);
		return sharedpreference_U.gettBoolean(Constants.isFirstStr, true);
	}
}
