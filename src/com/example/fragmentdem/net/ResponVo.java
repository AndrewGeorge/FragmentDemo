package com.example.fragmentdem.net;

import java.io.Serializable;

/****************************
 *ClassDescription:网络响应
 *ClassName:ResponVo
 * @version 1.0.0
 * @author dafuShao
 * @time  2014-9-29 12:08:35
 *****************************/
@SuppressWarnings("serial")
public class ResponVo implements Serializable{
	
	private int code;
	private long timestamp;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
