package com.example.fragmentdem.net;

import com.android.volley.VolleyError;

public interface ResponListener {

	public void onResponse(ResponVo responVo);
	
	public void onErrorResponse(VolleyError error,RequestVo requestVo,ResponVo responVo);
	
}
