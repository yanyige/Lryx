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

	<form action = "Admin/saveTeacher" method = "post" enctype="multipart/form-data">
		<input type='file' name ="upload"/>
		姓名:<input type="text" name="teacher.name" /> 
		名族:<input type="text" name="teacher.nation" />  
		 性别: 
		 <select name="teacher.isMan">
		 	<option value='true'>男</option>
		 	
		 	<option value='false'>女</option>
		 </select>
		<br> <br>
		学历:<input type="text" name="teacher.educationbg" /> 
		出生日期:<input type="text" name="teacher.birth" /> 
		 <br> <br> 
		联系电话:<input type="text" name="teacher.phone" />
		联系邮箱:<input type="text" name="teacher.email" /> 
		<br> <br>
		 职称:
		<select name="teacher.title.id"> 
		
		 	<option value='-1'>无</option>
		<s:iterator value="titles">
			<option value='<s:property value="id" />'>
				<s:property value="name" />
			</option>
		</s:iterator> </select> 
		职务:
		<select name="teacher.job.id"> 
		
		 	<option value='-1'>无</option>
		<s:iterator value="jobs">
			<option value='<s:property value="id" />'>
				<s:property value="name" />
			</option>
		</s:iterator> </select>
		 分类: 
		 <select name="teacher.category.id"> 
		 
		 	<option value='-1'>无</option>
		 <s:iterator value="categories">
			<option value='<s:property value="id" />'>
				<s:property value="name" />
			</option>
		</s:iterator> </select>
		 是否名师: 
		 <select name="teacher.isFamous">
		 	<option value='true'>是</option>
		 	
		 	<option value='false'>否</option>
		 </select>
		 <input type = "submit"/>
		 <br>
		 <br>
		 个人经历：
		 <br>
		 <textarea name= "teacher.introduction" rows="50" cols="120"></textarea>
	</form>
</body>
</html>