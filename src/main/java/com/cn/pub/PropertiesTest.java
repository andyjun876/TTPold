package com.cn.pub;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
	public String propertiesget(String str) throws FileNotFoundException, IOException {
	 	Properties properties = new Properties();  
	 	//System.out.println(new File(".").getAbsolutePath());
        properties.load(new FileInputStream("webapps\\TTP\\WEB-INF\\classes\\com\\cn\\config/dev.properties"));  
        return properties.getProperty(str);   
        
	}
}
