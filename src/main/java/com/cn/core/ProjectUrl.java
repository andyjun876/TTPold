package com.cn.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.cn.pub.MysqlConnect;
import com.cn.pub.PropertiesTest;


public class ProjectUrl {
	
	private Connection con;    
	private PreparedStatement ps;  


	public String list(String id) throws FileNotFoundException, IOException{
		
		String message;
		String sql;
		con=new MysqlConnect().getConnection();    
		if(id.equals("0")){
			sql=new PropertiesTest().propertiesget("projecturllist");
		}else{
			sql=new PropertiesTest().propertiesget("projecturlsel")+id;
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
	
	public String insert(int company_id,int type,String name,String url,int status) throws FileNotFoundException, IOException {
		String message = "";
		con=new MysqlConnect().getConnection();    
		//String sql="INSERT INTO project_url(company_id,type,name,url,status)  VALUES (1,1,'system1.1_生产','https://www.163.com/',1);"; 
		String sql=new PropertiesTest().propertiesget("projecturlinsert"); 
		try {
			ps=con.prepareStatement(sql);
			ps.setLong(1, company_id); 
			ps.setLong(2, type); 
			ps.setString(3, name); 
			ps.setString(4, url); 
			ps.setLong(5, status); 
			
			ps.executeUpdate(); 
			message = "{\"msg\":\"success\"}";
			return message;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "{\"msg\":\"failure\"}";
			return message;
		}  
	}
	
	public String getcompany() throws FileNotFoundException, IOException{
		String message;
		String sql;
		con=new MysqlConnect().getConnection();  
		
		sql=new PropertiesTest().propertiesget("getcompany");
		
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
	
	public String GetDictionary(String dictname) throws FileNotFoundException, IOException{
		String message;
		String sql;
		con=new MysqlConnect().getConnection();    
		if(dictname.equals("0")){
			sql=new PropertiesTest().propertiesget("getdictionary");
		}else{
			sql=new PropertiesTest().propertiesget("getdictionary")+" where dict_name='"+dictname+"';";
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
	
	public String update(int id,int company_id,int type,String name,String url,int status) throws FileNotFoundException, IOException{
		String message = "";
		con=new MysqlConnect().getConnection();    
		//String sql="INSERT INTO project_url(company_id,type,name,url,status)  VALUES (1,1,'system1.1_生产','https://www.163.com/',1);"; 
		String sql=new PropertiesTest().propertiesget("projecturlupdate"); 
		try {
			ps=con.prepareStatement(sql);
			ps.setLong(1, company_id); 
			ps.setLong(2, type); 
			ps.setString(3, name); 
			ps.setString(4, url); 
			ps.setLong(5, status); 
			ps.setLong(6, id); 
			
			ps.executeUpdate(); 
			message = "{\"msg\":\"success\"}";
			return message;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "{\"msg\":\"failure\"}";
			return message;
		}  
	}
}
