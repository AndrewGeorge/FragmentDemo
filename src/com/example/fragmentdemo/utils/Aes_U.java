/*
 * Copyright © 1999-2014 Shaoshi, Inc. All Rights Reserved
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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

@TargetApi(Build.VERSION_CODES.FROYO)
public class Aes_U {

	private Aes_U() {
	}

	/**
	 * AES解密
	 * 
	 * @param cryptedText
	 * @param seed
	 * @return String
	 */
	public static String decrypt(String cryptedText, String seed) {
		String tempText = cryptedText;
		if (TextUtils.isEmpty(tempText)) {
			return null;
		}
		byte[] clearText = null;
		try {
			byte[] keyData = seed.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, ks,
					new IvParameterSpec(new byte[c.getBlockSize()]));
			clearText = c.doFinal(Base64.decode(tempText, Base64.DEFAULT));
			String str = new String(clearText, "UTF-8");
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempText;
	}

	/**
	 * AES加密
	 * 
	 * @param clearText
	 * @param seed
	 * @return String
	 */
	public static String encrypt(String clearText, String seed) {
		if (TextUtils.isEmpty(clearText)) {
			return null;
		}

		byte[] encryptedText = null;
		try {
			byte[] keyData = seed.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, ks,
					new IvParameterSpec(new byte[c.getBlockSize()]));
			encryptedText = c.doFinal(clearText.getBytes("UTF-8"));
			return Base64.encodeToString(encryptedText, Base64.DEFAULT);
		} catch (Exception e) {
			return null;
		}
	}
}
