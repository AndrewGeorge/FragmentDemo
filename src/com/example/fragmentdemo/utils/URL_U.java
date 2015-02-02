/*
 * Copyright © 1999-2014 byecity, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.fragmentdemo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import android.content.Context;
import com.example.fragmentdem.net.GetData;
import com.example.fragmentdem.net.RequestData;
import com.example.fragmentdem.net.RequestVo;


public class URL_U {

	private URL_U() {
	}

//	/**
//	 * 组装请求URL，通过代理
//	 * 
//	 * @param mContext
//	 *            应用程序上下文
//	 * @param requestVo
//	 *            请求对象
//	 * @param orgUrl
//	 *            原始请求URL
//	 * @param aesKey
//	 *            AES加密密钥
//	 * @param did
//	 *            服务器所需did
//	 * @return
//	 * @return String
//	 */
//	public static String assemURL(final Context mContext,
//			final RequestVo requestVo, final String orgUrl) {
//		// if (BuildConfig.DEBUG) {
//		Random random = new Random();
//		RequestData mRequestData = new RequestData();
//		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		mRequestData.timestamp = String.valueOf(GetData.getDataInsterence(mContext).getRealTime());
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String data = Json_U.objToJsonStr(requestVo);
//		mRequestData.data = data;
//		String jsonVo = Json_U.objToJsonStr(mRequestData);
//		//Log_U.SystemOut("jsonVo------>" + jsonVo);
//		RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		String aesKey = regServer_U.getAesKey();
//		String did = regServer_U.getDid();
//		String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		String dataStr = null;
//		if (aesBase64Str != null) {
//			try {
//				dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return orgUrl + "&data=" + dataStr + "&sign="
//				+ MD5_U.getMd5(aesBase64Str + Constants.SALT_STR) + "&did="
//				+ did + Constants.PASTPORT_SYS_STR;
//		// }
//		// else {
//		// Random random = new Random();
//		// requestVo.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		// requestVo.timestamp = String.valueOf(DataTransfer
//		// .getDataTransferInstance(mContext).getRealTime());
//		// RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		// String aesKey = regServer_U.getAesKey();
//		// String did = regServer_U.getDid();
//		// String jsonVo = Json_U.objToJsonStr(requestVo);
//		// Log_U.SystemOut("jsonVo-------->" + jsonVo);
//		// String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		// String dataStr = null;
//		// if (aesBase64Str != null) {
//		// try {
//		// dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		// }
//		// return orgUrl + "?data=" + dataStr + "&sign="
//		// + MD5_U.getMd5(aesBase64Str + Constants.SALT_STR) + "&did="
//		// + did;
//		// }
//
//	}
//
//	/**
//	 * 支付get请求（通过代理）
//	 * 
//	 * @param requestVo
//	 * @param orgUrl
//	 * @return String
//	 */
//	public static String assemURLPay(final Context mContext,
//			final RequestVo requestVo, final String orgUrl) {
//		// if (BuildConfig.DEBUG) {
//		Random random = new Random();
//		RequestData mRequestData = new RequestData();
//		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		mRequestData.timestamp = String.valueOf(DataTransfer
//				.getDataTransferInstance(mContext).getRealTime());
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String data = Json_U.objToJsonStr(requestVo);
//		mRequestData.data = data;
//		String jsonVo = Json_U.objToJsonStr(mRequestData);
//		Log_U.SystemOut("jsonVo------>" + jsonVo);
//		RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		String aesKey = regServer_U.getAesKey();
//		String did = regServer_U.getDid();
//		String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		String dataStr = null;
//		if (aesBase64Str != null) {
//			try {
//				dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return orgUrl + "&data=" + dataStr + "&sign="
//				+ MD5_U.getMd5(aesBase64Str + Constants.SALT_STR) + "&did="
//				+ did + Constants.PASTPORT_SYS_PayCenter;
//		// }
//		// else {
//		// Random random = new Random();
//		// requestVo.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		// requestVo.timestamp = String.valueOf(DataTransfer
//		// .getDataTransferInstance(mContext).getRealTime());
//		// RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		// String aesKey = regServer_U.getAesKey();
//		// String did = regServer_U.getDid();
//		// String jsonVo = Json_U.objToJsonStr(requestVo);
//		// Log_U.SystemOut("jsonVo-------->" + jsonVo);
//		// String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		// String dataStr = null;
//		// if (aesBase64Str != null) {
//		// try {
//		// dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		// }
//		// return orgUrl + "?data=" + dataStr + "&sign="
//		// + MD5_U.getMd5(aesBase64Str + Constants.SALT_STR) + "&did="
//		// + did;
//		// }
//
//	}
//
//	/**
//	 * 支付post请求（通过代理）
//	 * 
//	 * @param requestVo
//	 * @param orgUrl
//	 * @return String
//	 */
//	public static HashMap<String, String> assemURLPayPostData(
//			final Context mContext, final RequestVo requestVo) {
//		HashMap<String, String> datas = new HashMap<String, String>();
//		Random random = new Random();
//		RequestData mRequestData = new RequestData();
//		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		mRequestData.timestamp = String.valueOf(DataTransfer
//				.getDataTransferInstance(mContext).getRealTime());
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String data = Json_U.objToJsonStr(requestVo);
//		mRequestData.data = data;
//		String jsonVo = Json_U.objToJsonStr(mRequestData);
//		Log_U.SystemOut("jsonVo------>" + jsonVo);
//		RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		String aesKey = regServer_U.getAesKey();
//		String did = regServer_U.getDid();
//		String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		String dataStr = null;
//		if (aesBase64Str != null) {
//			try {
//				dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if (aesBase64Str != null) {
//			datas.put("data", dataStr);
//			datas.put("did", did);
//			datas.put("sys", Constants.PASTPORT_PayCenter);
//			datas.put("sign", MD5_U.getMd5(aesBase64Str + Constants.SALT_STR));
//		}
//		String postData = Json_U.objToJsonStr(datas);
//		Log_U.Log_v("URL_U", "assemURLUploadFilePostData -->> postData="
//				+ postData);
//		return datas;
//	}
//
//	/**
//	 * 检查更新get请求
//	 * 
//	 * @param requestVo
//	 * @param orgUrl
//	 * @return String
//	 */
//	public static String assemURLCheckUpdate(RequestVo requestVo, String orgUrl) {
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String jsonVo = Json_U.objToJsonStr(requestVo);
//		Log_U.SystemOut("jsonVo------>" + jsonVo);
//		String encoderStr = null;
//		try {
//			encoderStr = URLEncoder.encode(jsonVo, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return orgUrl + "?data=" + encoderStr;
//	}
//
//	// /**
//	// * 拼接普通的URL请求（用户中心未加密使用）
//	// *
//	// * @param requestVo
//	// * @param orgUrl
//	// * @return String
//	// */
//	// public static String assemURLPlusStringByUserCenter(RequestVo requestVo,
//	// String orgUrl) {
//	// requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//	// String jsonVo = Json_U.objToJsonStr(requestVo);
//	// Log_U.SystemOut("jsonVo------>" + jsonVo);
//	// String encoderStr = null;
//	// try {
//	// encoderStr = URLEncoder.encode(jsonVo, "UTF-8");
//	// } catch (UnsupportedEncodingException e) {
//	// // TODO Auto-generated catch block
//	// e.printStackTrace();
//	// }
//	// return orgUrl + "?params=" + encoderStr;
//	// }
//	/**
//	 * 意见反馈请求url
//	 * 
//	 * @param requestVo
//	 * @param orgUrl
//	 * @return
//	 */
//	public static String assemURLPlusFeedBack(RequestVo requestVo, String orgUrl) {
//		// requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		// String jsonVo = Json_U.objToJsonStr(requestVo);
//		StringBuffer sb = new StringBuffer();
//		try {
//
//			sb.append("source=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getSource(), "UTF-8"));
//			sb.append("&model=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getModel(), "UTF-8"));
//			sb.append("&version=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getVersion(), "UTF-8"));
//			sb.append("&type=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getType(), "UTF-8"));
//			sb.append("&content=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getContent(), "UTF-8"));
//			sb.append("&name=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getName(), "UTF-8"));
//			sb.append("&contact=");
//			sb.append(URLEncoder.encode(
//					((FeedBackRequestVo) requestVo).getContact(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Log_U.SystemOut("sb------>" + sb.toString());
//		// String encoderStr = null;
//		// try {
//		// encoderStr = URLEncoder.encode(jsonVo, "UTF-8");
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		return orgUrl + "?" + sb.toString();
//	}
//
//	/**
//	 * get请求，通过代理
//	 * 
//	 * @param requestVo
//	 * @param orgUrl
//	 * @return String
//	 */
//	public static String assemURLPlusString(final Context mContext,
//			final RequestVo requestVo, final String orgUrl) {
//		Random random = new Random();
//		RequestData mRequestData = new RequestData();
//		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		mRequestData.timestamp = String.valueOf(DataTransfer
//				.getDataTransferInstance(mContext).getRealTime());
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String data = Json_U.objToJsonStr(requestVo);
//		mRequestData.data = data;
//		String jsonVo = Json_U.objToJsonStr(mRequestData);
//		Log_U.SystemOut("jsonVo------>" + jsonVo);
//		RegServer_U regServer_U = RegServer_U.getInstance(mContext);
//		String aesKey = regServer_U.getAesKey();
//		String did = regServer_U.getDid();
//		String aesBase64Str = Aes_U.encrypt(jsonVo, aesKey);
//		String dataStr = null;
//		if (aesBase64Str != null) {
//			try {
//				dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return orgUrl + "&data=" + dataStr + "&sign="
//				+ MD5_U.getMd5(aesBase64Str + Constants.SALT_STR) + "&did="
//				+ did + Constants.SYS_STR;
//	}

	/**
	 * get请求，无代理
	 * 
	 * @param requestVo
	 * @param orgUrl
	 * @return String
	 */
	public static String assemURLPlusStringAppKey(final Context mContext,
			final RequestVo requestVo, final String orgUrl) {
		Random random = new Random();
		RequestData mRequestData = new RequestData();
		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
		mRequestData.timestamp = String.valueOf(GetData.getDataInsterence(mContext).getRealTime());
		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
		String data = Json_U.objToJsonStr(requestVo);
		mRequestData.Request = data;
		mRequestData.AppKey = "14090101";
		mRequestData.AppSecrete = "85545185F034AD5CACCEAED9AA61771F67E371C1BC080AB2";
		String jsonVo = Json_U.objToJsonStr(mRequestData);
		Log_U.SystemOut("jsonVo------>" + jsonVo);
		
			String aesKey = "".substring(8, 8 + 16);
			String aesBase64Str = Aes_U.encrypt(jsonVo,
					aesKey);
			String dataStr = null;
			if (aesBase64Str != null) {
				try {
					aesBase64Str = aesBase64Str.replaceAll("\n", "");
					dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String sign = MD5_U.getMd5(aesBase64Str+"");
			return orgUrl + "&requestString=" + dataStr + "&sign=" + sign;
	}
//	/**
//	 * 无代理post
//	 * 
//	 * @param mContext
//	 * @param requestVo
//	 * @return
//	 */
//	public static HashMap<String, String> assemURLPlusStringAppKeyPostData(
//			final Context mContext, final RequestVo requestVo) {
//
//		HashMap<String, String> datas = new HashMap<String, String>();
//		Random random = new Random();
//		RequestData mRequestData = new RequestData();
//		mRequestData.nonce = String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		mRequestData.timestamp = String.valueOf(DataTransfer
//				.getDataTransferInstance(mContext).getRealTime());
//		requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		String data = Json_U.objToJsonStr(requestVo);
//		mRequestData.Request = data;
//		mRequestData.AppKey = "14090101";
//		mRequestData.AppSecrete = "85545185F034AD5CACCEAED9AA61771F67E371C1BC080AB2";
//		String jsonVo = Json_U.objToJsonStr(mRequestData);
//		Log_U.Log_v("URL_U", "assemURLPlusStringAppKeyPostData -->> jsonVo="
//				+ jsonVo);
//		/*** 处理相关加密 1.5.0版**开始 **/
//		if (Constants.isAesEncodeFor150) {
//			String aesBase64Str = Aes_U.encrypt(jsonVo,
//					Constants.AES_KEY.substring(8, 8 + 16));
//			String dataStr = null;
//			if (aesBase64Str != null) {
//				try {
//					aesBase64Str = aesBase64Str.replace("\n", "");
////					dataStr = URLEncoder.encode(aesBase64Str, "UTF-8");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			String sign = MD5_U.getMd5(aesBase64Str + Constants.SALT_STR);
//			datas.put("requestString", aesBase64Str);
//			datas.put("sign", sign);
//			return datas;
//		}
//		/*** 处理相关加密 1.5.0版**结束 **/
//		if (jsonVo != null) {
//			datas.put("requestString", jsonVo);
//		}
//
//		return datas;
//	}
//
//	/**
//	 * 支付post请求（无代理）
//	 * 
//	 * @param mContext
//	 * @param requestVo
//	 * @return
//	 */
//	public static HashMap<String, String> assemURLPayPostDataNoAgent(
//			final Context mContext, final RequestVo requestVo) {
//
//		HashMap<String, String> datas = new HashMap<String, String>();
//		// Random random = new Random();
//		// RequestData mRequestData = new RequestData();
//		// mRequestData.nonce =
//		// String.valueOf(random.nextInt(Integer.MAX_VALUE));
//		// mRequestData.timestamp = String.valueOf(DataTransfer
//		// .getDataTransferInstance(mContext).getRealTime());
//		// requestVo.trace_id = TraceID_U.getInstance().getTraceId();
//		// String data = Json_U.objToJsonStr(requestVo);
//		// mRequestData.Request = data;
//		// mRequestData.AppKey = "14090101";
//		// mRequestData.AppSecrete =
//		// "85545185F034AD5CACCEAED9AA61771F67E371C1BC080AB2";
//		// String jsonVo = Json_U.objToJsonStr(mRequestData);
//		String jsonVo = Json_U.objToJsonStr(requestVo);
//		Log_U.Log_v("URL_U", "assemURLPayPostData -->> jsonVo=" + jsonVo);
//
//		if (jsonVo != null) {
//			datas.put("params", jsonVo);
//		}
//
//		return datas;
//	}
//
//	/**
//	 * 文件上传post
//	 * 
//	 * @param mContext
//	 * @param requestVo
//	 * @return
//	 */
//	public static HashMap<String, String> assemURLUploadFilePostData(
//			final Context mContext, final RequestVo requestVo) {
//		HashMap<String, String> datas = new HashMap<String, String>();
//		String data = Json_U.objToJsonStr(requestVo);
//		Log_U.Log_v("URL_U", "assemURLUploadFilePostData -->> data=" + data);
//		// Log_U.printFile(!BuildConfig.DEBUG, "/sdcard/baicheng/log",
//		// "baicheng_log", 2, "URL_U",
//		// "assemURLUploadFilePostData -->> data=" + data);
//
//		if (data != null) {
//			datas.put("json_params", data);
//		}
//		return datas;
//	}

}
