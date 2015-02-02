package com.example.fragmentdemo.ResponseData;

import com.example.fragmentdem.net.ResponVo;
/****
 * ClassName:GetWeatherResponseDataVo
 * ClassDescription:天气数据响应类
 * @author George
 *time:2015年2月2日15:09:55
 ***/
public class GetWeatherResponseDataVo extends ResponVo{
	
	private static final long serialVersionUID = 1L;
	private String desc;
	private String  status;
	private WeatherResponseData data;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public WeatherResponseData getData() {
		return data;
	}
	public void setData(WeatherResponseData data) {
		this.data = data;
	}	

}
