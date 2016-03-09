<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>东北师范大学留日预校</title>
    <link href="css/sec.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <div class="inner-wrapper">
        <div class="header">
            <div class="headerContent"><img src="images/headIcon.png" alt="header"></div>
        </div>
        <div class="content">
            <div class="right-panel">
                <div class="right-inner">
                    <div class="panel-head">
                        <div class="b-l"></div>
                        <div class="b-r">相关链接</div>
                    </div>
                    <div class="main-panel">
                        <div class="b-l"></div>
                        <div class="b-m">教&emsp;务&emsp;处</div>
                        <div class="b-r">&gt;</div>
                    </div>
                    <div class="main-panel">
                        <div class="b-l"></div>
                        <div class="b-m">学&nbsp;员&nbsp;公&nbsp;告</div>
                        <div class="b-r">&gt;</div>
                    </div>
                    <div class="main-panel">
                        <div class="b-l"></div>
                        <div class="b-m">日本各大学网址</div>
                        <div class="b-r">&gt;</div>
                    </div>
                </div>
            </div>
            <img src="images/培训报名.png">
            <div class="inner-panel">
                <div class="panel-head">
                    <div class="b-l"></div>
                    <div class="b-r">师资力量</div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学校概况</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="survey.jsp">学校介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="organization.jsp">组织介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="leader.jsp" class="on">学校领导</a></div>
                    </div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学术动态</div>
                    <div class="b-r"><span>&gt;</span></div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r">学术交流</div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r">学术活动</div>
                    </div>
                </div>
                <div class="main-panel">
                <div class="b-l"></div>
                <div class="b-m">师资力量</div>
                <div class="b-r">&gt;</div>
                <div class="cpanel">
                    <div class="b-l"></div>
                    <div class="b-r"><a href="teacherList?id=-1">全体教师</a></div>
                </div>
                <s:iterator value="categories">
                    <div class="cpanel">
                    <div class="b-l"></div>
                    <div class="b-r"><a href="teacherList?id=<s:property value="id" />"><s:property value="name" /></a></div>
                    </div>
                </s:iterator>
            </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">远程教学</div>
                    <div class="b-r">&gt;</div>
                </div>
            </div>
            <div class="main-content">
                <div class="main-content-nav">
                    <ul>
                        <li>首页</li>
                        <li>学校师资</li>
                    </ul>
                </div>
                <div class="main-content-c">
                    <div class="c-head">
                    	<s:iterator value="categories">
							<a class="headA" href = "teacherList?id=<s:property value="id" />">[<s:property value="name" />]</a>
						</s:iterator>
                    </div>
                    <hr>
                    <div class="c-article">
                        <div class="leader-category">
                            <s:iterator value="cwtl">
            
                                    <div class="category">
                                        <span><s:property value="category.name"/></span>
                                    </div><hr>
                                    <div class="leader-list" >
                                    	<div class="list-wrapper">
	                                    <s:iterator value="teachers">
	                                    
	                                        
	                                            <div class="lblock">
	                                            <img src="images/leader.jpg" alt="" width="105" height="74">
	                                            <p><a href = "teacher?id=<s:property value="id" />">
	                                                <s:property value="name" /></a></p>
	                                            </div>
	                                        
	                                    </s:iterator>
                                    	</div>
                                    </div>
                            </s:iterator>

                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="footer">
            <ul>
                <li>概况</li>
                <li>机构</li>
                <li>师资</li>
                <li>教学</li>
                <li>留学</li>
                <li>专题</li>
                <li>制度</li>
            </ul>
        </div>

    </div>
</div>
</body>
<script>

</script>
<script src="js/flowLayout1.js" type="text/javascript"></script>
</html>