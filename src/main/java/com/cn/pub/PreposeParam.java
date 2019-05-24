package com.cn.pub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import net.sf.json.JSONObject;

public class PreposeParam {
	public String prepose(String param,String prepose,String preposeparam) throws Exception, IOException{
		String strparam = param;
		String[] preparam = preposeparam.split(",");
		ApiPost apipost1 = new ApiPost();
		Connection con; 
		String[] col = {"id","type","ip","parameter", "expectedresult", "expectedsql","status","path","prepose","prepose_param"};
		String httpResult = null;
		 
		con =  new MysqlConnect().getConnection();
		String sql = new PropertiesTest().propertiesget("preposesql") ;
		sql = sql + prepose + ";";
		Statement statement = con.createStatement();
		
        // 结果集
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String url = rs.getString(col[2])+rs.getString(col[7]);
        String param1 = rs.getString(col[3]);
        con.close();
		// 调用接口
		apipost1.resultCheck(url,param1);
		// 返回结果
		httpResult = apipost1.httpResult;
		
		if(prepose.equals("0")){
			System.out.println("wuqianzhi");
		}else{
			String strres = httpResult;

			
			if(apipost1.getvalue(strres, "code").equals("10000")){
				String str1 = apipost1.getvalue(strres, "data");
				JSONObject getJsonObj1 = JSONObject.fromObject(str1);
				strparam = strparam.replace("?", getJsonObj1.getString(preparam[1]));
			}
		}
		return strparam;
	}
}
