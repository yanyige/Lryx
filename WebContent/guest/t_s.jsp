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
    <link href="./css/sec.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <div class="inner-wrapper">
        <div class="header">
            <div class="headerContent"><img src="./images/headIcon.png" alt="header"></div>
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
            <img src="./images/培训报名.png">
            <div class="inner-panel">
                <div class="panel-head">
                    <div class="b-l"></div>
                    <div class="b-r">学校概况</div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学校概况</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="survey.html" class="on">学校介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="organization.html">组织介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="leader.html">学校领导</a></div>
                    </div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学术动态</div>
                    <div class="b-r"><span>&gt;</span></div>
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
                <script type="text/javascript">
                    var id = '<s:property value="id"/>';
                    var name = '<s:property value="teacher.name"/>';
                    var cp = document.getElementsByClassName("cpanel");
                    var ocp = cp.children;
                    for(var i = 0 ; i < cp.length ; i ++){

                    	if(cp[i].children[1].children[0].innerHTML == '<s:property value="teacher.categoty"/>'){
                    		
                    		cp[i].children[1].children[0].class = "on";
                    	}
                    }
                </script>
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
                    <li>师资力量</li>
                    <li><s:property value="teacher.category.name" /></li>
                    <li><s:property value="teacher.name"/></li>
                </ul>
            </div>
            <div class="main-content-c">
                <div class="c-head">
                    <span>师资力量</span>
                </div>
                <hr>
                <div class="c-pro">
                    <div class="proWrap">
                        <div class="proTitle">
                           <s:property value="teacher.name"/>
                        </div>
                        <div class="mainPro">
                            <div class="proTag">
                                <div class="proTable">
                                    <div class="tableRow clearfix">
                                        <div class="tableBlock">
                                            <div class="blockL">姓名</div>
                                            <div class="blockR"><s:property value="teacher.name"/></div>
                                        </div>
                                        <div class="tableBlock">
                                            <div class="blockL">性别</div>
                                            <div class="blockR"><s:property value="teacher.man"/></div>
                                        </div>
                                        <script>
                                        	var tb = document.getElementsByClassName("tableBlock");
                                        	if(tb[1].children[1].innerHTML){
                                        		tb[1].children[1].innerHTML = '男';
                                        	}else{
                                        		tb[1].children[1].innerHTML = '女';
                                        	}
                                        </script>
                                    </div>
                                    <div class="tableRow clearfix">
                                        <div class="tableBlock">
                                            <div class="blockL">名族</div>
                                            <div class="blockR"><s:property value="teacher.nation"/></div>
                                        </div>
                                        <div class="tableBlock">
                                            <div class="blockL">学历</div>
                                            <div class="blockR"><s:property value="teacher.educationbg"/></div>
                                        </div>
                                    </div>
                                    <div class="tableRow clearfix">
                                        <div class="tableBlock">
                                            <div class="blockL">职务</div>
                                            <div class="blockR"><s:property value="teacher.job.name"/></div>
                                        </div>
                                        <div class="tableBlock">
                                            <div class="blockL">职称</div>
                                            <div class="blockR"><s:property value="teacher.title.name"/></div>
                                        </div>
                                    </div>
                                    <div class="tableRow clearfix">
                                        <div class="tableBlock">
                                            <div class="blockL">出生日期</div>
                                            <div class="blockR"><s:property value="teacher.birth"/></div>
                                        </div>
                                        <div class="tableBlock">
                                            <div class="blockL">办公室</div>
                                            <div class="blockR"><s:property value="teacher.educationbg"/></div>
                                        </div>
                                    </div>
                                    <div class="tableRow clearfix">
                                        <div class="tableBlock">
                                            <div class="blockL">联系电话</div>
                                            <div class="blockR"><s:property value="teacher.phone"/></div>
                                        </div>
                                        <div class="tableBlock">
                                            <div class="blockL">联系邮箱</div>
                                            <div class="blockR"><s:property value="teacher.email"/></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="proPhoto">
									<img src='<s:property value="teacher.imgUrl"/>'>
                                </div>
                            </div>
                            <div class="proMain">

                            </div>
                        </div>
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
    window.onload = function(){
        var mainPanels = document.getElementsByClassName("main-panel");

        for(var i in mainPanels){
            mainPanels[i].onclick = function(){
                var re = /focus/;
                if(!re.test(this.className)) {
                    this.className = this.className + " focus";
                    for (var j in this.children) {
                        //                    this.children[j].style.display = "block";
                        if (this.children[j].className == "cpanel") {
                            this.children[j].style.display = "block";
                        }
                    }
                }else{
                    this.className = "main-panel";
                    for (var j in this.children) {
                        //                    this.children[j].style.display = "block";
                        if (this.children[j].className == "cpanel") {
                            this.children[j].style.display = "none";
                        }
                    }
                }
            }
        }
        var wrapper = document.getElementsByClassName("wrapper");
        var innerWrapper = document.getElementsByClassName("inner-wrapper");
        console.log(wrapper[0].offsetHeight);
        console.log(innerWrapper[0].offsetHeight);
        wrapper[0].style.height =innerWrapper[0].offsetHeight + 56 + "px";
    }
</script>
</html>