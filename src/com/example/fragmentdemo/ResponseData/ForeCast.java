package com.example.fragmentdemo.ResponseData;
/****
 * ClassName:GetWeatherResponseDataVo
 * ClassDescription:天气数据响应类
 * @author George
 *time:2015年2月2日15:09:55
 ***/
public class ForeCast {
	private String fengxiang;
	private String  fengli;
	private String hight;
	private String type;
	private String low;
	private String date;
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getFengli() {
		return fengli;
	}
	public void setFengli(String fengli) {
		this.fengli = fengli;
	}
	public String getHight() {
		return hight;
	}
	public void setHight(String hight) {
		this.hight = hight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
