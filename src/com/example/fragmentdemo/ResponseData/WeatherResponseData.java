package com.example.fragmentdemo.ResponseData;

import java.util.ArrayList;
/****
 * ClassName:GetWeatherResponseDataVo
 * ClassDescription:天气数据响应类
 * @author George
 *time:2015年2月2日15:09:55
 ***/
public class WeatherResponseData {

	private String wendu;
	private String ganmao;
	private ArrayList<ForeCast> forecast;
	private String aqi;
	private String city;
	private Yesterday yesterday;
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public ArrayList<ForeCast> getForecast() {
		return forecast;
	}
	public void setForecast(ArrayList<ForeCast> forecast) {
		this.forecast = forecast;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Yesterday getYesterday() {
		return yesterday;
	}
	public void setYesterday(Yesterday yesterday) {
		this.yesterday = yesterday;
	}
	
}
