package com.cn.api;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cn.core.Api;



/**
 * Servlet implementation class ApiExe
 */
@WebServlet("/ApiExe")
public class ApiExe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiExe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        reader.close();
        //String data2 = JSON.toJSONString(json);
        json = json.replace("\\\"", "\"");
        json = json.replace("\\\\", "\\");
        json = json.substring(1, json.length()-1);
        JSONObject data = JSONObject.parseObject(json);
        String ip = data.getString("ip");
		 String param= data.getString("params");
		 String type= data.getString("type");
        
		
		 String message;
		Api api=new Api();
		message = api.oneexe(ip, param, type);
		response.getWriter().write(message);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
