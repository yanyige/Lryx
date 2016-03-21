<!DOCTYPE>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>发表新闻</title>
<base href="<%=basePath%>">


<script src="js/jquery.js"></script>
<script src="js/jquery-git1.min.js"></script>
<script src="js/jquery-sortable-lists.min.js"></script>
<script type="text/javascript" src="ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="js/html5shiv.js"></script>
	   <script src="js/respond.min.js"></script>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>




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

			<form action = "Admin/saveArticle" method = "post" enctype="multipart/form-data">
				<input type ='submit'/>
				标题:<input type="text" name="article.title" value='<s:property value = "article.title"/>' /> 
				 是否显示在主页墙上: 
				 <select name="article.onIndex">
				 	<option value='true'>是</option>
				 	
				 	<option value='false'>否</option>
				 </select>
				<input type='file' name ="upload"/>
				 是否置顶: 
				 <select name="article.onTop">
				 	<option value='true'>是</option>
				 	
				 	<option value='false'>否</option>
				 </select>
				 是否标红: 
				 <select name="article.highlight">
				 	<option value='true'>是</option>
				 	
				 	<option value='false'>否</option>
				 </select>
				 文章类别：<select name="article.category.id">
				 <s:iterator value="acategorise">
					<option value='<s:property value="id" />'>
						<s:property value="name" />
					</option>
				</s:iterator> </select> 
				<br> <br>
				
				<textarea name="article.content" id="content" style="margin:10px 0px"></textarea>
				<script type="text/javascript">
					UEDITOR_CONFIG.UEDITOR_HOME_URL = 'ueditor1_4_3-utf8-jsp/';
					var ue = UE. getEditor('content');
					ue.ready(function(){
						ue.setContent('<s:property value='article.content' escape = 'false'/>');
					});
				</script>
					

			</form>
		</div>
	</div>
</div>


	
		
</body>

<script type="text/javascript">
	function setFocus() {
	    UE.getEditor('content').focus();
	}
</script>   

</html>