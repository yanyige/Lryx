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
			错误信息：<s:property value ="errorMsg"/>

			<form class = "form-inline" action = "Admin/saveTeacher" method = "post" enctype="multipart/form-data">
				<br>
				<label for="file">上传照片</label>
				<input type='file' id="file" class="file" name ="upload"/>
				<br>
				<div class="form-group">
				    <label for="InputName">姓名</label>
				    <input type="text" class="form-control" id="InputName" placeholder="Your Name" name="teacher.name"/>
				 </div>

				 <div class="form-group">
				    <label for="InputNation">名族</label>
				    <input type="text" class="form-control" id="InputNation" placeholder="Your Nation" name= "teacher.nation"/>
				 </div>

				<div class="form-group">
				    <label for="isMan">性别</label>
				    <select class="form-control">
						<option value='true'>男</option>
						<option value='false'>女</option>
					</select>
				 </div>
				<br>
				<br>

				<div class="form-group">
				    <label for="educationbg">学历</label>
				    <input type="text" class="form-control" id="educationbg" placeholder="Your educationbg" name="teacher.educationbg"/>
				 </div>

				 <div class="form-group">
				    <label for="birth">出生日期</label>
				    <input type="text" class="form-control" id="birth" placeholder="Your birthday" name="teacher.birth"/>
				 </div>

				 <br> 
				 <br>
				
				<div class="form-group">
				    <label for="phone">联系电话</label>
				    <input type="text" class="form-control" id="phone" placeholder="Your phone" name="teacher.phone"/>
				 </div>

				 <div class="form-group">
				    <label for="email">联系邮箱</label>
				    <input type="text" class="form-control" id="email" placeholder="Your email" name="teacher.email"/>
				 </div>

				<br>
				<br>
				

				<div class="form-group">
				    <label for="title">职称</label>
					<select class="form-control" id = "title" name="teacher.title.id"> 
				
				 	<option value='-1'>无</option>
					<s:iterator value="titles">
						<option value='<s:property value="id" />'>
						<s:property value="name" />
						</option>
					</s:iterator> 
					</select> 
				</div>

				<div class="form-group">
				    <label for="job">职务</label>
					<select class="form-control" name="teacher.job.id" id="job"> 
				
				 	<option value='-1'>无</option>
					<s:iterator value="jobs">
						<option value='<s:property value="id" />'>
							<s:property value="name" />
						</option>
					</s:iterator> 
					</select>
				</div>

				<div class="form-group">
				    <label for="category">分类</label>
					<select class="form-control" id = "category" name="teacher.category.id"> 
				 
				 	<option value='-1'>无</option>
					 <s:iterator value="categories">
						<option value='<s:property value="id" />'>
							<s:property value="name" />
						</option>
					</s:iterator> 
					</select>
				</div>

				<div class="form-group">
				    <label for="isFamous">是否名师</label>
					<select class = "form-control" id = "isFamous" name="teacher.isFamous">
				 	<option value='true'>是</option>
				 	
				 	<option value='false'>否</option>
					</select>
				</div>

				 
				 <input type = "submit"/>
				 <br>
				 <br>
				 个人经历：
				 <br>
				 <textarea class="form-control" name= "teacher.introduction" rows="50" cols="120"></textarea>
			</form>
		</div>
	</div>
</div>





</body>
</html>