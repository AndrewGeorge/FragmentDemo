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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import com.example.fragmentdemo.BuildConfig;

import android.util.Log;



/******************************************
 * 类描述： Log输入工具类 类名称：Log_U
 * 
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2014-2-21 上午10:45
 ******************************************/
public class Log_U {

	private Log_U() {
	}

	public static void SystemOut(final String logStr) {
		if (BuildConfig.DEBUG) {
			System.out.println(logStr);
		}
	}

	public static void Log_v(final String vStrTag, final String vLogStr) {
		if (BuildConfig.DEBUG) {
			Log.v(vStrTag, vLogStr);
		}
	}

	public static void Log_e(final String eStrTag, final String eLogStr) {
		if (BuildConfig.DEBUG) {
			Log.e(eStrTag, eLogStr);
		}
	}

	public static void Log_d(final String dStrTag, final String dLogStr) {
		if (BuildConfig.DEBUG) {
			Log.d(dStrTag, dLogStr);
		}
	}

	public static void Log_i(final String iStrTag, final String iLogStr) {
		if (BuildConfig.DEBUG) {
			Log.i(iStrTag, iLogStr);
		}
	}

	public static void Log_w(final String wStrTag, final String wLogStr) {
		if (BuildConfig.DEBUG) {
			Log.w(wStrTag, wLogStr);
		}
	}

	public static final int CRASH = 1000;

	public static int printFile(boolean isLogFile, String logpath,
			String file_name, int priority, String tag, String msg) {
		int ret = 0;
		int logfile_switch = 10;
		if (isLogFile) {
			logfile_switch = 0;
		}
		if (priority >= logfile_switch) {
			ret += printMsgToFile(logfile_switch, logpath, file_name, priority,
					tag, msg);
		}
		return ret;
	}

	private static int printMsgToFile(int logfile_switch, String logpath,
			String file_name, int priority, String tag, String msg) {
		if (msg == null) {
			msg = "[null]";
		}
		byte[] buffer = msg.getBytes();
		return printToFile(logfile_switch, logpath, file_name, priority, tag,
				"log", buffer, 0, buffer.length);
	}

	private static int printToFile(int logfile_switch, String logpath,
			String file_name, int priority, String tag, String type,
			byte[] buffer, int offset, int count) {
		int ret = 0;
		if (priority == CRASH) {
			type = "crash";
		}
		if (priority < logfile_switch || !checkLogPath(logpath)) {
			if (priority == CRASH) {
				logpath = "/sdcard/";
				type = "crash";
				if (!checkLogPath(logpath)) {
					return ret;
				}
			} else {
				return ret;
			}
		}

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);

		String timeString = String.format("%d-%d-%d %d:%d:%d.%d", year, month,
				day, hour, minute, second, millisecond);
		String headString = String.format("\r\n%s\t(%d)\ttag:%s\tmessage:",
				timeString, priority, tag);
		byte[] headBuffer = headString.getBytes();

		String fileName = String.format(
				"%s/%s.%s",
				logpath,
				file_name
						+ String.format("_%d_%d_%d_%d_%d_%d", year, month, day,
								hour, minute, second), type);
		FileOutputStream fo = null;
		try {
			File file = new File(fileName);
			// if (file.length() >= clearLen) {
			// clearFile(file, fileName);
			// }

			fo = new FileOutputStream(file, true);
			fo.write(headBuffer);
			fo.write(buffer, offset, count);
			ret = headBuffer.length + count;
		} catch (Throwable tr) {
			ret = 0;
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (Throwable tr) {
				}
			}
		}
		return ret;
	}

	private static boolean checkLogPath(String newPath) {
		if (newPath == null) {
			return false;
		}
		boolean ret = false;
		try {
			File logPath = new File(newPath);
			if (!logPath.exists()) {
				logPath.mkdirs();
			}
			if (logPath.isDirectory()) {
				ret = true;
			} else {
				ret = false;
			}
		} catch (Throwable e) {
			ret = false;
		}
		return ret;
	}

	private static void clearFile(File file, String fileName) {

		BufferedReader reader = null;
		try {
			FileOutputStream testfile = new FileOutputStream(fileName);
			testfile.write(new String("").getBytes());

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			if ((tempString = reader.readLine()) == null) {
				// Log.v(TAG, "文件 " + fileName + " 的内容已清空！");

			} else {
				// Log.v(TAG, "文件 " + fileName + " 的内容清空失败！");

				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					// Log.v(TAG, "line " + line + ": " + tempString);
					line++;
				}

			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
