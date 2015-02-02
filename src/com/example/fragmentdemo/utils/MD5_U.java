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

import java.security.MessageDigest;


public class MD5_U {
	private MD5_U() {
	}

	/**
	 * MD5方式加密字符串
	 * 
	 * @param content
	 * @return
	 */
	public static String getMd5(String content) {
		// MessageDigest digester = null;
		// try {
		// digester = MessageDigest.getInstance("MD5");
		// } catch (NoSuchAlgorithmException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// if (digester != null) {
		// digester.update(content.getBytes());
		// byte[] digest = digester.digest();
		// return getString(digest);
		// }
		// return null;
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = content.getBytes("UTF-8");
			md.update(bytes);
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// private static String getString(byte[] b) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < b.length; i++) {
	// sb.append(b[i]);
	// }
	// return sb.toString();
	//
	// }
}
