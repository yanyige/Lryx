<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    //获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>东北师范大学留日预校</title>
    <script src="js/jquery.js"></script>
    <script src="js/jquery-git1.min.js"></script>
    <script src="js/jquery-sortable-lists.min.js"></script>
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
                    <div class="b-r">学校概况</div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学校概况</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学校介绍">学校介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=组织介绍">组织介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="leader">学校领导</a></div>
                    </div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">新闻快讯</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=新闻&pageNo=1&step=5">学校新闻</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学术&pageNo=1&step=5">交流学术</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=公告&pageNo=1&step=5">通知公告</a></div>
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
                    <div class="b-m">培训报名</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=项目介绍">项目介绍</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=招生简章">招生简章</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=网上报名">网上报名</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=报道流程">报道流程</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=答疑解惑">答疑解惑</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=生活信息">生活信息</a></div>
                    </div>
                </div>
                <div class="main-panel">
                    <div class="b-l"></div>
                    <div class="b-m">学院专区</div>
                    <div class="b-r">&gt;</div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学籍管理">学籍管理</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学生组织">学生组织</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学员活动">学员活动</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=课程安排">课程安排</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=考试安排">考试安排</a></div>
                    </div>
                    <div class="cpanel">
                        <div class="b-l"></div>
                        <div class="b-r"><a href="pagel?cname=学习资源">学习资源</a></div>
                    </div>
                </div>
            </div>
            <div class="main-content">
                <div class="main-content-nav">
                    <ul>
                        <li><a href="">首页</a></li>
                        <li>新闻快讯</li>
                        <li><s:property value ="cname"/></li>
                    </ul>
                </div>
                <div class="main-content-c">
                    <div class="c-head">
                        <span><s:property value ="cname"/></span>
                    </div>
                    <hr>
                    <div class="c-c">
                        <s:iterator value="alwpi.articles">
                            <div class="item">
                                <div class="ic"></div>
                                <div class="title"><a href = "article?id=<s:property value="id"/>"><s:property value ="title"/></a></div>
                                <div class="date"><s:property value="lastModifyTime"/></div>
                            </div>
                        </s:iterator>
    
                        <div class="item">
                            <span>共<s:property value ="alwpi.articleCn"/>条记录，每页<s:property value ="alwpi.step"/>条,当前在第<s:property value ="alwpi.pageNo"/>页</span>
                            <div class="pagination">
                                <ul>
                                    <li class="prev">上一页</li>
                                    <li class="page"><a href="pagel?cname=<s:property value ="cname"/>&pageNo=1&step=5">1</a></li>
                                    <li class="page"><a href="pagel?cname=<s:property value ="cname"/>&pageNo=2&step=5">2</a></li>
                                    <li class="page"><a href="pagel?cname=<s:property value ="cname"/>&pageNo=3&step=5">3</a></li>
                                    <li class="page"><a href="pagel?cname=<s:property value ="cname"/>&pageNo=4&step=5">4</a></li>
                                    <li class="page"><a href="pagel?cname=<s:property value ="cname"/>&pageNo=5&step=5">5</a></li>
                                    <li class="next">下一页</li>
                                </ul>
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

        var brs = document.getElementsByClassName("b-r");

        var q;

        for(var i = 0 ; i < brs.length ; i ++){
            if(brs[i].children[0]){
                var html = brs[i].children[0].innerHTML.substring(brs[i].children[0].innerHTML.length - 2);
                console.log(html);
                if(html == '<s:property value ="cname"/>'){
                    var p = brs[i].parentElement.parentElement.parentElement.children[0].children[1];
                    q = brs[i].parentElement.parentElement.children[1];
                    console.log(p);
                    console.log(q);
                    p.innerHTML = q.innerHTML;
                }
            }
        }
        var uls = document.getElementsByClassName("main-content-nav")[0].children[0];

        uls.children[1].innerHTML = q.innerHTML;
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {

        if(listpageNo<=3){
            $(".pagination").children().each(function(index){
                if(index==0||index==6){
                    if(index==0){
                        var nowpage=Number(listpageNo)-1;
                        $(this).children().attr("href","Guest/articleList?labelName="+labelName+"&pageNo="+nowpage);
                        console.log("Guest/articleList?labelName="+labelName+"&pageNo="+nowpage);
                    }else{
                        var nowpage=Number(listpageNo)+1;
                        $(this).children().attr("href","Guest/articleList?labelName="+labelName+"&pageNo="+nowpage);
                    }
                }
            });
        }else{
            $(".pagination").children().each(function(index){
                if(index==0||index==6){
                    if(index==0){
                        var nowpage=Number(listpageNo)-1;
                        $(this).children().attr("href","Guest/articleList?labelName="+labelName+"&pageNo="+nowpage);
                    }else{
                        var nowpage=Number(listpageNo)+1;
                        $(this).children().attr("href","Guest/articleList?labelName="+labelName+"&pageNo="+nowpage);
                    }
                }
                else{
                    $(this).children().text(listpageNo-3+index);
                    $(this).children().attr("href","Guest/articleList?labelName="+labelName+"&pageNo="+$(this).children().text());
                }
            });
        }
    });
</script>
</html>