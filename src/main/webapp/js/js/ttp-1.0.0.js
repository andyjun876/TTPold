function GetUrlParam(paraName) {
		　　　　var url = document.location.toString();
		　　　　var arrObj = url.split("?");

		　　　　if (arrObj.length > 1) {
		　　　　　　var arrPara = arrObj[1].split("&");
		　　　　　　var arr;

		　　　　　　for (var i = 0; i < arrPara.length; i++) {
		　　　　　　　　arr = arrPara[i].split("=");

		　　　　　　　　if (arr != null && arr[0] == paraName) {
		　　　　　　　　　　return arr[1];
		　　　　　　　　}
		　　　　　　}
		　　　　　　return "";
		　　　　}
		　　　　else {
		　　　　　　return "";
		　　　　}
		　　}
function js_list(change){
	var status;
	
	$.ajax({
           type: "GET",
           url: "http://172.16.30.12:8080/TTP/servlet/ProjectUrlList",
           data: "uid=fasdfasdfasdf",
           dateType: "json",
           success: function(data){	
        	   	status = 1;
        	   	company = 0;
        	   	if(change!='0'){
            	   	var company = document.getElementById("companysel").value;
            	   	var status  = document.getElementById("statusel").value;
        	   	}
        	   	
        	    var strhtml="";
       	   		var datajson = eval("(" + data + ")");  
       	   		if(datajson.msg!="failure"){	           	   		
           	   		var datadata = datajson.data;
           	   		for(i=0;i<datajson.data.length;i++){
           	   			if(status=='0'||datadata[i].status==status){
           	   				if(company=='0'||datadata[i].company_id==company){	
								strhtml = strhtml + "<tr id=" +datadata[i].id+ "><td>"+
								datadata[i].id+ "</td><td>" +
								datadata[i].company_name+"</td><td>"+
								datadata[i].name+"</td><td>"+
								datadata[i].dictdata_value+"</td><td>"+
								datadata[i].url+"</td><td>"+
								datadata[i].statusvalue+"</td><td>"+
								"<a href='javascript:void(0);' onclick=\"js_click('"+datadata[i].url+"')\" class='table_btn table_edit edit_btn'><i class='fa fa-internet-explorer'></i>"+
								"<span> 跳转</span></a><a href='javascript:void(0);' onclick=\"js_update('"+datadata[i].id+"')\" class='table_btn table_edit edit_btn'><i class='fa fa-refresh'></i>"+
								"<span> 更新</span></a></td></tr>";   
           	   				}
           	   			}
           	   		}
           	   		$("#thdiv").html(strhtml);
       	   		}
       	   		if(change=='0'){
           	   		js_company();
           	   		js_dict("status");
           	   	}
              }
        	});
} 
		  
function js_click(url){
			 window.open(url);  
		  }   
		  
function js_update(id){
			  
		  	var param = "&companyid=" + document.getElementById("companysel").value +
		  				"&statusid=" +  document.getElementById("statusel").value;
			  if(id==0){
				 url = "newprojecturl.html";
			  }else{
 			  url = "newprojecturl.html" + "?id=" + id + param;

			  }
	   		window.location.href = url;

		  }
	function js_company(){
		$.ajax({
           type: "GET",
           url: "http://172.16.30.12:8080/TTP/servlet/GetCompanyList",
           data: "uid=fasdfasdfasdf",
           dateType: "json",
           success: function(data){	
        	    var strhtml;
       	   		var datajson = eval("(" + data + ")");
       	   		var datadata = datajson.data;
       	   		strhtml = "<select name='companysel' id='companysel' class='f_left search_input' onchange='js_list(1)'> ";
       	   		if(GetUrlParam('back')!=""){
           	   		if(GetUrlParam('companyid')==0){
           	   			strhtml = strhtml + "<option value='0' selected='selected'>全部</option>"
           	   		}else{
	           	   		strhtml = strhtml + "<option value='0' >全部</option>"
	           	   	}
           	   		for(i=0;i<datajson.data.length;i++){
	           	   		if(datadata[i].id==GetUrlParam('companyid')){
           	   				strhtml = strhtml + "<option value='" + datadata[i].id + "' selected='selected'>" + datadata[i].company_name + "</option>"
           	   			}else {
           	   				strhtml = strhtml + "<option value='" + datadata[i].id + "'>" + datadata[i].company_name + "</option>"			           	   			
           	   			}
           	   		}
       	   		}else{
           	   		strhtml = strhtml + "<option value='0' selected='selected'>全部</option>"
           	   		for(i=0;i<datajson.data.length;i++){
           	   			strhtml = strhtml + "<option value='" + datadata[i].id + "'>" + datadata[i].company_name + "</option>"		           	   						
           	   		}
       	   		}
       	   		strhtml = strhtml + "</select> ";
       	   		//alert(document.getElementById("selectAge").value);
       	   		$("#thcompanysel").html(strhtml);
       	   				           	   		
              }
        	});
	}
	
	function js_dict(dictname){
		var param = "dictname=" + dictname
		$.ajax({
           type: "GET",
           url: "http://172.16.30.12:8080/TTP/servlet/GetDictionary",
           data: param,
           dateType: "json",
           success: function(data){	
        	   	
        	    var strhtml;
       	   		var datajson = eval("(" + data + ")");
       	   		strhtml = "<select name='statusel' id='statusel' class='f_left search_input' onchange='js_list(1)'> ";
       	   		if(GetUrlParam('back')!=""){
       	   			if(GetUrlParam('statusid')==0){
       	   			strhtml = strhtml + "<option value='0' selected='selected'>全部</option>"
       	   			}else{
           	   		strhtml = strhtml + "<option value='0' >全部</option>"}
           	   		if(datajson.msg!="failure"){
	           	   		var datadata = datajson.data;
	           	   		for(i=0;i<datajson.data.length;i++){
	           	   			if(datadata[i].dict_value==GetUrlParam('statusid')){
			           	   		strhtml = strhtml + "<option value='" + datadata[i].dict_value + "' selected='selected'>" + datadata[i].dictdata_value + "</option>"		           	   							
	           	   			}else {
			           	   		strhtml = strhtml + "<option value='" + datadata[i].dict_value + "'>" + datadata[i].dictdata_value + "</option>"		           	   							
	           	   			}
	           	   		}
	           	   		//alert(document.getElementById("selectAge").value);
           	   		}else{
           	   			strhtml = strhtml + "<option value='0'>未配置</option>"	
           	   		}
       	   		}else{
           	   		strhtml = strhtml + "<option value='0' >全部</option>"
           	   		if(datajson.msg!="failure"){
	           	   		var datadata = datajson.data;
	           	   		for(i=0;i<datajson.data.length;i++){
	           	   			if(datadata[i].dict_value==1){
			           	   		strhtml = strhtml + "<option value='" + datadata[i].dict_value + "' selected='selected'>" + datadata[i].dictdata_value + "</option>"		           	   							
	           	   			}else {
			           	   		strhtml = strhtml + "<option value='" + datadata[i].dict_value + "'>" + datadata[i].dictdata_value + "</option>"		           	   							
	           	   			}
	           	   		}
	           	   		//alert(document.getElementById("selectAge").value);
           	   		}else{
           	   			strhtml = strhtml + "<option value='0'>未配置</option>"	
           	   		}
       	   		}
       	   	strhtml = strhtml + "</select> ";
       	   		$("#thstatusel").html(strhtml);
              }
        	});
		
		if(GetUrlParam('back')!=""){
			js_list('1');
		}
	}
	function update(paramnum){
		if(GetUrlParam('id')!=""){
			var id = "id=" + GetUrlParam('id');
			
			$.ajax({
	               type: "GET",
	               url: "http://172.16.30.12:8080/TTP/servlet/ProjectUrlList",
	               data: id,
	               dateType: "json",
	               success: function(data){	
	            	    var strhtml;
	           	   		var datajson = eval("(" + data + ")");
	           	   		if(datajson.msg=="failure"){
	           	   			alert("无"+ companryid + "的数据！");
	           	   			window.location.href = "projecturl.html";
	           	   		}else{
		           	   		var datadata = datajson.data;
		           	   		if (datajson.data.length==1){
		           	   			document.getElementById("id").value = datadata[0].id;
		           	   			document.getElementById("company").value = datadata[0].company_id;
		           	   			document.getElementById("name").value = datadata[0].name;
		           	   			document.getElementById("type").value = datadata[0].type;
		           	   			document.getElementById("url").value = datadata[0].url;
		           	   			document.getElementById("status").value = datadata[0].status;
		           	   			
		           	   		}
		           	   		
		                  }
	           	   		}
	            	});
		}
		
	}
	
	
	function dictionary(){
		id="id="+document.getElementById("id").value;
		var url = document.location.toString();
		var paramnum = 0;
		if(url.indexOf('?')!=-1){
			paramnum = 1;
		}
		$.ajax({
               type: "GET",
               url: "http://172.16.30.12:8080/TTP/servlet/GetDictionary",
               data: id,
               dateType: "json",
               success: function(data){	
            	   	
            	    var strhtml;
           	   		var datajson = eval("(" + data + ")");
           	   		var datadata = datajson.data;
           	   		var num = 0;
           	   		strhtml = "<select name='type' id='type' class='f_left search_input'> ";
           	   		strhtmlstatus = "<select name='status' id='status' class='f_left search_input'> ";
           	   		for(i=0;i<datajson.data.length;i++){
           	   			
           	   			if(datadata[i].dict_name=='type'){
           	   				num = num + 1;
	           	   			strhtml = strhtml + "<option value='" + datadata[i].dict_value + "'>" + datadata[i].dictdata_value + "</option>"		           	   						
           	   			}else if(datadata[i].dict_name=='status'){
           	   				num = num + 1;
           	   				strhtmlstatus = strhtmlstatus + "<option value='" + datadata[i].dict_value + "'>" + datadata[i].dictdata_value + "</option>"		           	   						
           	   			}
           	   			
           	   			
           	   		}
	           	   	if (num==0){
           	   			strhtml = strhtml + "<option value='0'>未配置</option>"	
           	   			strhtmlstatus = strhtmlstatus + "<option value='0'>未配置</option>"	
       	   			}
           	   		strhtml = strhtml + "</select> ";
           	   		strhtmlstatus = strhtmlstatus + "</select> ";
           	   		//alert(document.getElementById("selectAge").value);
           	   		$("#thdict").html(strhtml);
           	   		$("#thstatus").html(strhtmlstatus);
                  }
            	});
				if(document.getElementById("id").value==""&&paramnum==1){
					update(paramnum);
				}
			}

	function insert(){
		
		id = document.getElementById("id").value;
		companyid = document.getElementById("company").value;
		name = document.getElementById("name").value;
		type = document.getElementById("type").value;
		url = document.getElementById("url").value;
		status = document.getElementById("status").value;
		
		var str = "update=0&company_id=" + companyid + "&" +
		"type=" + type + "&" +
		"name=" + name + "&" +
		"url=" + url + "&" +
		"status=" + status;
		
		if (id==""){				
			if(companyid==""&&name==""&&type==""&&url==""&&status==""){
				alert("不能为空");
			}else{
				
				$.ajax({
		               type: "GET",
		               url: "http://172.16.30.12:8080/TTP/servlet/ProjectUrlInsert",
		               data: str,
		               dateType: "json",
		               success: function(data){	
		            	    var strhtml;
		           	   		var datajson = eval("(" + data + ")");
		           	   		
			           	   	if(datajson.msg=="failure"){
		           	   			alert("参数错误！");
		           	   		}else{
		           	   			alert("添加成功");
		           	   			window.location.href = "projecturl.html";
		           	   		}

		                  }
		            	});
			}
		}else{
			if(companyid==""&&name==""&&type==""&&url==""&&status==""){
				alert("不能为空");
			}else{
				str = "update=1&id=" + id + "&"+ str;
				$.ajax({
		               type: "GET",
		               url: "http://172.16.30.12:8080/TTP/servlet/ProjectUrlInsert",
		               data: str,
		               dateType: "json",
		               success: function(data){	
		            	    var strhtml;
		           	   		var datajson = eval("(" + data + ")");
		           	   		
			           	   	if(datajson.msg=="failure"){
		           	   			alert("参数错误！");
		           	   		}else{
		           	   			alert("更新成功");
		           	   			window.location.href = "projecturl.html";
		           	   		}
		           	   						           	   		
		                  }
		            	});
			}
		}
	}
	
	function back(){
		if(GetUrlParam('companyid')!=""&&GetUrlParam('statusid')!=""){
			window.location.href = "projecturl.html?back=1&companyid=" + GetUrlParam('companyid') +"&statusid="+ GetUrlParam('statusid');
		}else{
			window.location.href = "projecturl.html";
		}
	}
	
	function newprojecturl(){
		$.ajax({
            type: "GET",
            url: "http://172.16.30.12:8080/TTP/servlet/GetCompanyList",
            data: "uid=fasdfasdfasdf",
            dateType: "json",
            success: function(data){	
         	    var strhtml;
        	   		var datajson = eval("(" + data + ")");
        	   		var datadata = datajson.data;
        	   		strhtml = "<select name='company' id='company' class='f_left search_input' onchange='dictionary()'> ";
        	   		for(i=0;i<datajson.data.length;i++){
        	   			strhtml = strhtml + "<option value='" + datadata[i].id + "'>" + datadata[i].company_name + "</option>"		           	   						
        	   		}
        	   		
        	   		strhtml = strhtml + "</select> ";
        	   		//alert(document.getElementById("selectAge").value);
        	   		$("#thdiv").html(strhtml);
        	   		
        	   		dictionary();
        	   		
               }
         	});

	}
	
	
	