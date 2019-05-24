package com.cn.core;

import java.io.IOException;

import com.cn.pub.ApiPost;
import com.cn.pub.GetConnect;

public class Api {
	public String oneexe(String ip,String params,String type) throws IOException{
		ApiPost apipost = new ApiPost();
		 String httpResult="";
		 String message;
		 if(type.equals("post")) {
			 apipost.resultCheck(ip,params);
			 httpResult = apipost.httpResult;
		 }else if(type.equals("get")) {
			 String str = ip + "?" + params;
			 GetConnect connect = new GetConnect();
			 httpResult = connect.getHttpRespone(str);
		}
		 if(httpResult==""){
         	message = "{\"msg\":\"failure\"}";
		 }else{
 			if(httpResult.indexOf("\"")>0){
 				httpResult = httpResult.replace("\"", "\\\"");
 			}
  
	        System.out.println(httpResult);
	        String messagestart = "{\"msg\":\"success\",\"data\":\"";
			String messageend = "\"}";
			message = messagestart + httpResult + messageend;
			System.out.println(message);
		 }
		return message;
	}
	

}
