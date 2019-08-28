<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>

<html>
<head>
    <title>XXX 管理系统</title>
    <script type="text/javascript">
        function showTime()
        {
            var myDate=new Date();		//定义日期与时间变量  年月日时分秒 星期几
            var y = myDate.getFullYear();
            var m = myDate.getMonth() + 1;
            var d = myDate.getDate();
            if(m<10)
                m="0"+m;
            if(d<10)
                d="0"+d;

            var hour=myDate.getHours();
            var minutes=myDate.getMinutes();
            var seconds=myDate.getSeconds();
            if(hour<10)
                hour="0"+hour;
            if(minutes<10)
                minutes="0"+minutes;
            if(seconds<10)
                seconds="0"+seconds;
            var week = myDate.getDay(); //0-6
            switch (week){
                case 0:week="星期天";break;
                case 1:week="星期一";break;
                case 2:week="星期二";break;
                case 3:week="星期三";break;
                case 4:week="星期四";break;
                case 5:week="星期五";break;
                case 6:week="星期六";break;
            }
            document.getElementById("time").innerHTML="当前时间为："+y+"-"+m+"-"+d+" "+hour+":"+minutes+":"+seconds+" "+week;		//给id为time的标签赋值
            setTimeout("showTime()",1000);	//设置定时函数，1秒执行一次showTime函数
        }
        window.onload=showTime;			//页面加载时调用showTime函数
    </script>

</head>
<body>

<%--大盒子    data-options="fit:true" ：自适应整个屏幕 --%>
<div class="easyui-layout" data-options="fit:true" >

    <%--北面--%>
    <div data-options="region:'north'" style="height:80px;background-color: #ffb3b3">

        <div>
           <h1 style="margin: 20px;padding: 0px;text-align: center">欢迎${sessionScope.username}访问客户管理系统</h1>
        </div>

            <div style="float: right;margin-top: -20px;margin-right: 50px">
            <%--欢迎${sessionScope.username}登陆&nbsp;&nbsp;--%>
            <span id="time" style="margin-right: 25px"></span>
            <a href="javascript:window.location.replace('${path}/sys/logout')">退出</a>
        </div>

    </div>

    <%--西面--%>
    <div data-options="region:'west'<%--,split:true--%>" title="功能菜单" style="width:200px;">

        <%--手风琴--%>
        <div class="easyui-accordion" data-options="fit:true">

            <c:if test="${!empty modules}">
                <c:forEach items="${modules}" var="module">
                    <%--需要把Java类型集合转换成json格式 --%>

                    <c:set var="children" value="${module.children}" scope="session"/>
                    <%
                        List<Map<String,Object>>list= (List<Map<String, Object>>) session.getAttribute("children");
                        String jsonString= JSON.toJSONString(list);
                        session.setAttribute("children",jsonString);
                    %>

                    <div title="${module.text}" data-options="iconCls:'icon-ok'" style="padding: 10px">
                            <%--树控件 --%>
                        <ul id="${module.id}"></ul>
                        <script type="text/javascript">
                            $('#${module.id}').tree({
                                data:${sessionScope.children},//获取子菜单
                                //在点击菜单项的时候调用的函数
                                onClick:function (node) {
                                    //表明指定的面板是否存在
                                    var flag= $('#tabs').tabs('exists',node.text);
                                    if(!flag){
                                        // 添加新的选项卡面板
                                        $('#tabs').tabs('add',{
                                            title: node.text,
                                            selected: true,
                                            closable:true,  //关闭按钮
                                            href:path+"/"+node.auth_url
                                        });
                                    }else{
                                        var tab=  $('#tabs').tabs('select',node.text); // 获取选择的面板
                                        // 更新选择的面板的新标题和内容
                                        $('#tabs').tabs('update', {
                                            tab: tab,
                                            options: {
                                                href:path+"/"+node.auth_url  // 新内容的URL
                                            }
                                        });

                                    }


                                }//
                            });
                        </script>
                    </div>
                </c:forEach>
            </c:if>

        </div>


    </div>

    <%--中间--%>
    <div data-options="region:'center'<%--,title:'内容区域',iconCls:'icon-edit'--%>">

        <div id="tabs" class="easyui-tabs" data-options="fit:true">
            <div title="首页" style="padding:10px">
                <%-- ${sessionScope}--%>

                <%--  首页背景图片  --%>
                    <div style="margin-left: 300px;margin-top: 20px">
                        <img src="<%=path%>/static/images/2.png" style="width: 400px;height: 400px" >
                    </div>




            </div>

        </div>


    </div>


    <%--大盒子尾--%>
</div>

<%--防止浏览器后退--%>
<script type="text/javascript">
    history.go(1);
</script>

</body>
</html>
