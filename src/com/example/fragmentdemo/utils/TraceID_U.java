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

import java.util.UUID;

import android.text.TextUtils;


public class TraceID_U {

	private String md5UuidStr;

	private TraceID_U() {
	}

	private static class MInstance {
		private final static TraceID_U TRACEID = new TraceID_U();
	}

	public static TraceID_U getInstance() {
		return MInstance.TRACEID;
	}

	/**
	 * 获取TRACEID
	 * 
	 * @return String （MD5 加密后的UUID字符串）
	 */
	public String getTraceId() {
		if (TextUtils.isEmpty(md5UuidStr)) {
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			md5UuidStr = MD5_U.getMd5(uuidStr);
			Log_U.SystemOut("md5UuidStr--------->" + md5UuidStr);
			return md5UuidStr;
		}
		return md5UuidStr;
	}
}
