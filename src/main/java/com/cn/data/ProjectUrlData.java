package com.cn.data;

public class ProjectUrlData {
	public String id;
	public String company_id;
	public String company;
	public String type_name;
	public String type;
	public String name;
	public String url;
	public String status;
	
	public ProjectUrlData(String id,String company_id,String company,String type_name,String type,String name,String url,String status){
		this.id = id;
		this.company_id = company_id;
		this.company = company;
		this.type_name = type_name;
		this.type = type;
		this.name = name;
		this.url = url;
		this.status = status;
	}

	public void setstatus(String status){
		this.status = status;
	}
	
	public String getstatus(){
		return this.status;
	}
	
	public void seturl(String url){
		this.url = url;
	}
	
	public String geturl(){
		return this.url;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public String getname(){
		return this.name;
	}
	
	public void settype(String type){
		this.type = type;
	}
	
	public String gettype(){
		return this.type;
	}
	
	public void settypename(String typename){
		this.type_name = typename;
	}
	
	public String gettypename(){
		return this.type_name;
	}
	
	public void setcompany(String company){
		this.company = company;
	}
	
	public String getcompany(){
		return this.company;
	}
	
	public void setcompany_id(String company_id){
		this.company_id = company_id;
	}
	
	public String getcompany_id(){
		return this.company_id;
	}
	

	public void setid(String id){
		this.id = id;
	}
	
	public String getid(){
		return this.id;
	}
}
