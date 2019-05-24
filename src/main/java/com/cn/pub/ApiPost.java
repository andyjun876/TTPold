package com.cn.pub;

import java.io.*;
import java.util.Map;



public class ApiPost {
	public String httpResult = null;
	public String resvalue = null;
	public String url = null;
	public String param = null;
	public Map<String,String> resmap = null;
	 
    public void resultCheck(String url,String param) throws IOException{

    	SendPost sendpost = new SendPost();
    	httpResult = sendpost.sendPost(url, param);
        //httpResult=connectstr.getHttpRespone(url);    
    }
    
    public String getvalue(String connectstr,String values){
    	resvalue = Common.getJsonValue(connectstr, values);
    	return resvalue;
    }
   
}
