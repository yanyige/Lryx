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
<link rel="stylesheet" type="text/css" href="css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

</head>
<script type="text/javascript">
	
</script>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="col-md-2">
			<ul>
				<li>
					<a href = "Admin/addTeacher">添加教师信息</a>
				</li>
				<li>
					<a href = "Admin/manageTeacherTitle">编辑教师职务职称等信息</a>
				</li>
				<li>
					<a href = "Admin/modifyTeacherNo">教师列表</a>
				</li>
				<li>
					<a href = "Admin/addArticle">添加文章</a>
				</li>
				<li>
					<a href = "Admin/articleList?cname=新闻&pageNo=1&step=5">修改文章</a>
				</li>
				<li>
					<a href = "Admin/pageList">修改二级页面</a>
				</li>
			</ul>
		</div>
		<div class="col-md-10">

		错误信息：<s:property value ="errorMsg"/><br><br>
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
		</div>
	</div>
</div>


</body>
</html>