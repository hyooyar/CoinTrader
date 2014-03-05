package org.moze.background.ajax;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectionWithArgs {
	public static String doPost(String url , String content){
		String result = "";
		DataOutputStream out = null;
		BufferedReader br = null;
		try{
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setRequestMethod("POST");// 提交模式
			connection.setDoOutput(true);// 是否输入参数
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setReadTimeout(5000);
			connection.connect();
			
			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(content);
			out.flush();
	
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "utf-8"));
				String line = "";
				while ((line = br.readLine()) != null) {
					result += line;
				}
			}
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null){
					out.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String doGet(String url){
		String result = "";
		try{
			URL getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			InputStreamReader in = new InputStreamReader(connection.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String inputLine = "";
			while (((inputLine = buffer.readLine()) != null)) { 
				result += inputLine;  
            }           
            in.close();  
            connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
