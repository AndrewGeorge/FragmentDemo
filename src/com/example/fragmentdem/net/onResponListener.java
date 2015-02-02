package com.example.fragmentdem.net;

import com.android.volley.VolleyError;

public interface onResponListener {
	
	
	public void onResponse(ResponVo responVo);
	
	public void onErrorResponse(VolleyError error,ResponVo responVo);
	

}
