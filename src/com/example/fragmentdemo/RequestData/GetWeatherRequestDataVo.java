package com.example.fragmentdemo.RequestData;

import com.example.fragmentdem.net.RequestVo;

public class GetWeatherRequestDataVo extends RequestVo {

	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
