package com.cn.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.cn.pub.MysqlConnect;
import com.cn.pub.PropertiesTest;

public class TestCase {
	private Connection con;    
	
	public String list(String id) throws FileNotFoundException, IOException{
		String message;
		String sql;
		con=new MysqlConnect().getConnection();    
		if(id.equals("0")){
			sql=new PropertiesTest().propertiesget("testcaselist");
		}else{
			sql=new PropertiesTest().propertiesget("testcasesel")+id;
		}
		 
		
		String data = "[{";
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count=rsmd.getColumnCount();
			String[] col=new String[count];
			for(int i=0;i<count;i++){
				col[i]=rsmd.getColumnLabel(i+1);}
			
            rs.last(); 
            int datanum = rs.getRow();
            int num = 0;
            if(datanum==0){
            	message = "{\"msg\":\"failure\"}";
            }else{
	            rs.beforeFirst();  
	            while(rs.next()) {
	            		num++;
	            		for(int i=0;i<count;i++){
	            			data = data + "\"" + col[i] + "\":\"" + rs.getString(col[i]) + "\"";
	            			if(i<count-1){
	                			data = data + ",";
	                		}else {
	                			data = data + "";}
	        			}   
	            		
	            		if(num<datanum){
	            			data = data + "},{";
	            		}else {
	            			data = data + "}]";}
            		
	            }
	            
	            String messagestart = "{\"msg\":\"success\",\"data\":";
				String messageend = "}";
				message = messagestart + data + messageend;  
	        }
            con.close();
            
           
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "{\"msg\":\"failure\"}";
			e.printStackTrace();
		}
		
		return message;
	}
}
