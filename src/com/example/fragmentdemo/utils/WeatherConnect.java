package com.example.fragmentdemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherConnect {

	private static String tag="WeatherConnect";
	public static InputStream httpRequest(String urlString, int timeout) {
		Log_U.Log_i("url", urlString);
		if (timeout == 0) {
			timeout = 10000;
		}
		URL url = null;
		URLConnection connection = null;
		InputStream inputStream = null;
		try {
			url = new URL(urlString);
			connection = url.openConnection();
			connection.setConnectTimeout(timeout);
			inputStream = connection.getInputStream();
			connection.connect();
			Log_U.Log_i(tag, "connect()");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;

	}
	
	public static String convertStreamToString(InputStream is) {      
        /*  
          * To convert the InputStream to String we use the BufferedReader.readLine()  
          * method. We iterate until the BufferedReader return null which means  
          * there's no more data to read. Each line will appended to a StringBuilder  
          * and returned as String.  
          */     
		Log_U.Log_i(tag, is+"");
         BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
         StringBuilder sb = new StringBuilder();      
     
         String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {      
                 sb.append(line);      
             }      
         } catch (IOException e) {      
             e.printStackTrace();      
         } finally {      
            try {      
                 is.close();      
             } catch (IOException e) {      
                 e.printStackTrace();      
             }      
         }      
        Log_U.Log_i(tag, sb.toString());
        return sb.toString();      
     }
	public static String getStringResponse(String urlString, int timeout) {
		Log_U.Log_i(tag, urlString);
		return convertStreamToString(httpRequest(urlString, timeout));
	}
}
