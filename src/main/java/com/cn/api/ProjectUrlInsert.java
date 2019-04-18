package com.cn.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.core.ProjectUrl;

/**
 * Servlet implementation class ProjectUrlInsert
 */
@WebServlet("/ProjectUrlInsert")
public class ProjectUrlInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectUrlInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String message;
		response.setContentType("text/html;charset=utf-8");
		System.out.println(request.getParameter("update"));
		if(request.getParameter("update").equals("1")){
			System.out.println("update");
			if(request.getParameter("company_id")!=null&&
					request.getParameter("type")!=null&&
					request.getParameter("name")!=null&&
					request.getParameter("url")!=null&&
					request.getParameter("status")!=null&&
					request.getParameter("id")!=null){
				String company_id = request.getParameter("company_id");
				String type = request.getParameter("type");
				String name = request.getParameter("name");
				String url = request.getParameter("url");
				String status = request.getParameter("status");
				String id = request.getParameter("id");
				
				ProjectUrl projecturl = new ProjectUrl();
				message = projecturl.update(Integer.valueOf(id),Integer.valueOf(company_id), Integer.valueOf(type), name, url, Integer.valueOf(status));			
			}else{
				message = "{\"msg\":\"failure\",\"data\":\"参数错误\"}";
			}
			
		}else{
			System.out.println("insert");
			if(request.getParameter("company_id")!=null&&
					request.getParameter("type")!=null&&
					request.getParameter("name")!=null&&
					request.getParameter("url")!=null&&
					request.getParameter("status")!=null){
				String company_id = request.getParameter("company_id");
				String type = request.getParameter("type");
				String name = request.getParameter("name");
				String url = request.getParameter("url");
				String status = request.getParameter("status");
				
				ProjectUrl projecturl = new ProjectUrl();
				message = projecturl.insert(Integer.valueOf(company_id), Integer.valueOf(type), name, url, Integer.valueOf(status));			
			}else{
				message = "{\"msg\":\"failure\",\"data\":\"参数错误\"}";
			}
		}
		
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
