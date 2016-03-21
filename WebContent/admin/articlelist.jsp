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

			<div>
				文章的类型
				<s:iterator value="acategorise">
					<li data-module='c'>
						<div>
							<a href="Admin/articleList?cname=<s:property value="name" />&pageNo=1&step=5">
								<s:property value="id" />----<s:property value="name" />
							</a>
						</div>
					</li>
				</s:iterator>
				总文章数：<s:property value ="alwpi.articleCn"/>
				当前所在页面：<s:property value ="alwpi.pageNo"/>
				每页显示文章数：<s:property value ="alwpi.step"/>
				<s:iterator value="alwpi.articles">
					<div>
						--类别：<s:property value="category.name"/>
						--<a href = "Admin/modifyArticle?id=<s:property value="id"/>">标题:<s:property value ="title"/></a>
						--是否置顶：<s:property value ="onTop"/>
						--是否突出显示：<s:property value ="highlight"/>
						<form method="post" action="Admin/deleteArticle?id=<s:property value="id" />">
							<input type="submit" value="删除" />
						</form>
					</div>
				</s:iterator>
			</div>
		</div>
	</div>
</div>


	
</body>
</html>