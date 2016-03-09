<!DOCTYPE>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>教师信息管理</title>

<script src="js/jquery.js"></script>
<script src="js/jquery-git1.min.js"></script>
<script src="js/jquery-sortable-lists.min.js"></script>

</head>
<script type="text/javascript">
	
</script>
<body>

错误信息：<s:property value ="errorMsg"/>
	<form action = "Admin/modifyTeacherDone" method = "post" enctype="multipart/form-data">
		<input type = "hidden" name = "teacher.imgUrl" value = '<s:property value="teacher.imgUrl"/>'/>
		<input type = "hidden" name = "teacher.id" value = '<s:property value="teacher.id"/>'/>
		<img src='<s:property value="teacher.imgUrl"/>'/>
		<input type='file' name ="upload" />
		姓名:<input type="text" name="teacher.name" value="<s:property value="teacher.name"/>"/> 
		名族:<input type="text" name="teacher.nation" value="<s:property value="teacher.nation"/>"/>  
		<s:set name="ca" value="teacher.category"/>
		<s:property value ="#ca"/>
		 分类: 
		 <select name="teacher.category.id"> 
		 	<s:if test="#ca == null">
		 	<option value='-1' selected="selected">无</option>
		 	</s:if>
		 	<s:else>
		 	<option value='-1'>无</option>
		 	</s:else>
		 <s:iterator value="categories">
			 <s:if test="#ca.id == id">
		 	<option value='<s:property value="id" />'selected="selected" >
				<s:property value="name" />
			</option>
		 	</s:if>
		 	
		 	<s:else>
		 	<option value='<s:property value="id" />' >
				<s:property value="name" />
			</option>
		 	</s:else>
			
		</s:iterator> </select>
		<input type = "submit"/>
		
	</form>
</body>
</html>