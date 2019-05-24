package com.cn.pub;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetConnect {

	private String urlstr="";
	DataOutputStream outputStream = null;
	public String httpResult = null;
	public String resvalue = null;
	public String url = null;
	public String param = null;
	 
    public void resultCheck(String url,String param) throws IOException{
    	String uslstr = url + "?" + param;
    	httpResult = getHttpRespone(uslstr);
        //httpResult=connectstr.getHttpRespone(url);    
    }
    
    public String getvalue(String connectstr,String values){
    	resvalue = Common.getJsonValue(connectstr, values);
    	return resvalue;
    }
    
    public String geturl() {
        return urlstr;
    }

    public String getHttpRespone(String urlstr) throws IOException {

        String line = "";

        String httpResults = "";

        //urlstr=("http://172.16.21.38:8082/score/queryScoreById?id=201708031145501071de161398140b0e");

        try {

			URL url = new URL(urlstr);
			 // 连接类的父类，抽象类
			HttpURLConnection urlConnection =(HttpURLConnection)url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json, text/plain, */*");
			urlConnection.setRequestProperty("Connection", "keep-alive");
			// 建立实际的连接
			
			urlConnection.connect();
			InputStream inStream = urlConnection.getInputStream();
			
			//outputStream = new DataOutputStream(urlConnection.getOutputStream());
			
			//outputStream.flush();
			
			//outputStream.close();
			//InputStream inStream = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));

            while ((line = reader.readLine()) != null) {

                httpResults = httpResults + line.toString();

            }

            reader.close();

            // 断开连接

            urlConnection.disconnect();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return httpResults;

    }

}