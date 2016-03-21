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
	$(function() {
		var options = {
			placeholderCss : {
				'background-color' : '#ff8'
			},
			hintCss : {
				'background-color' : '#bbf'
			},
			onChange : function(cEl) {
				console.log('onChange');
			},
			complete : function(cEl) {
				console.log('complete');
			},
			isAllowed : function(cEl, hint, target) {
				if (target.data('module') === 'c' && cEl.data('module') == 'c') {
					return false;
				} else {
					hint.css('background-color', '#99ff99');
					return true;
				}
			},
			opener : {
				active : true,
				as : 'html', // if as is not set plugin uses background image
				close : '<i class="fa fa-minus c3"></i>', // or 'fa-minus c3',  // or './imgs/Remove2.png',
				open : '<i class="fa fa-plus"></i>', // or 'fa-plus',  // or'./imgs/Add2.png',
				openerCss : {
					'display' : 'inline-block',
					//'width': '18px', 'height': '18px',
					'float' : 'left',
					'margin-left' : '-35px',
					'margin-right' : '5px',
					//'background-position': 'center center', 'background-repeat': 'no-repeat',
					'font-size' : '1.1em'
				}
			},
			ignoreClass : 'clickable',

		}
		$('#sTree1').sortableLists(options)
		$('#sTree2').sortableLists(options)
		$('#sTree3').sortableLists(options)
	});
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

			新增职称
			<br>


			<div>
				<form action="Admin/addTeacherTitle" method="post">
					<s:actionerror />
					<input type="text" name="name" id="name" /> <input type="submit" />
				</form>
			</div>
			<ul>
				<s:iterator value="titles">
					<li data-module='c'>
						<div>
							<form action="Admin/delTeacherTitle?id=<s:property value="id" />"
								method="post">
								<ul>
								<li><s:property value="id" /></li>
								<li><s:property value="name" /></li>
								<li><input type="submit" value="删除" /></li></ul>
							</form>
						</div>
					</li>
				</s:iterator>
			</ul>

			新增职务
			</br>
			<div>
				<form action="Admin/addTeacherJob" method="post">
					<input type="text" name="name" id="name" /> <input type="submit" />
				</form>
			</div>
			<ul id="sTree2">
				<s:iterator value="jobs">
					<li data-module='c'>
						<div>
							<form action="Admin/delTeacherJob?id=<s:property value="id" />"
								method="post">
								<s:property value="id" />
								<s:property value="name" />
								<s:property value="no" />
								<input type="submit" value="删除" />

							</form>
						</div>
					</li>
				</s:iterator>
			</ul>
			
			新增类别
				</br>
				<div>
					<form action="Admin/addTeacherCategory" method="post">

						<input type="text" name="name" id="name" /> <input type="submit" />
					</form>
				</div>
				<ul id="sTree3">
				<s:iterator value="categories">

					<li data-module='c'>
						<div>
							<form
								action="Admin/delTeacherCategory?id=<s:property value="id" />"
								method="post">
								<s:property value="id" />
								<s:property value="name" />
								<s:property value="no" />
								<input type="submit" value="删除" />
							</form>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>
	
</body>
</html>