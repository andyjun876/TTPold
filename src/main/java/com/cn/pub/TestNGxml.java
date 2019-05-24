package com.cn.pub;
import java.io.*;

import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class TestNGxml {
	//生成testngxml。
	public void xmlnew(String[] list,String[] listurl) throws FileNotFoundException, IOException{
		//创建文档
        Document document = new Document();
        //创建根元素
        Element suite = new Element("suite");
        document = new Document(suite,new DocType("suite","http://testng.org/testng-1.0.dtd"));
        //把根元素加入到document中
        //document.addContent(people); 
        
        //创建注释
        //Comment rootComment = new Comment("将数据从程序输出到XML中！");      
        //people.addContent(rootComment);
        
        suite.setAttribute("name", "测试计划");
        suite.setAttribute("parallel", "classes");
        suite.setAttribute("thread-count", "5");
        //根据传递参数配置 模块 和url 生成xml脚本
        for(int i = 0;i<list.length;i++){
        	testcase(suite,list[i],listurl[i]);
        }
        //testcase(suite,"登陆","com.dwr.test.ApiTestNG");
        listeners(suite);
        
        
        //设置xml输出格式
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");//设置编码
        format.setIndent("    ");//设置缩进
        
        
        //得到xml输出流
        XMLOutputter out = new XMLOutputter(format);
        //把数据输出到xml中
        out.output(document, new FileOutputStream("webapps/AutoTesting/WEB-INF/classes/com/dwr/test/testng.xml"));//或者FileWriter
        //out.output(document, new FileOutputStream("e:/jdom.xml"));//或者FileWriter

	}
	
	//testng listeners部分
	public void listeners(Element suite){
		Element listeners = new Element("listeners");
		suite.addContent(listeners);
        
		Element listener1 = new Element("listener");
		listeners.addContent(listener1);
        
		listener1.setAttribute("class-name", "org.uncommons.reportng.HTMLReporter");
        
        Element listener2 = new Element("listener");
        //person2_name.setText("林志颖");
        listeners.addContent(listener2);
        
        listener2.setAttribute("class-name", "org.uncommons.reportng.JUnitXMLReporter");       
	}
	
	//testng test部分
	public void testcase(Element suite,String name,String classurl){
		//创建父元素
        Element test1 = new Element("test");
        //把元素加入到根元素中
        suite.addContent(test1);
        //设置person1元素属性
        test1.setAttribute("name", name);
        test1.setAttribute("preserve-order", "true");
        test1.setAttribute("verbose", "2");
        
        Element parameter = new Element("parameter");
        //person1_address.setText("香港");
        test1.addContent(parameter);
        parameter.setAttribute("name", "parametername");
        parameter.setAttribute("value", name);

        
        Element test1_classes = new Element("classes");
        //person1_name.setText("刘德华");
        test1.addContent(test1_classes);
        
        Element test1_classes_class = new Element("class");
        //person1_address.setText("香港");
        test1_classes.addContent(test1_classes_class);
        test1_classes_class.setAttribute("name", classurl);
	}
}
