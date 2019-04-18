package com.cn.pub;

import java.sql.*;

public class MysqlConnect {
	public Connection con;
	public Connection getConnection(){   
 
		try{     
			Class.forName("com.mysql.jdbc.Driver");      
			con=DriverManager.getConnection(new PropertiesTest().propertiesget("mysqlip"),
											new PropertiesTest().propertiesget("mysqlusername"),
											new PropertiesTest().propertiesget("mysqlpassword"));    
			}catch(Exception e){
			e.printStackTrace();
			}     
		return con; 
	} 
 
}
