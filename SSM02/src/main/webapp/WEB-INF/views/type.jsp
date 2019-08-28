<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>房屋类型</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin-left: 300px;margin-top: 15px;margin-bottom: 10px">
            <span>请输入房屋类型名称：</span><input id="findTypeByname" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">
            <%-- 搜索按钮--%>
            <a href="javascript:findType()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 150px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addType" href="javascript:addType()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="typeDataGrid">
        </table>


    </div>

</div>


<%-- 初始化编辑窗口    type_id,type_name --%>
<div id="typeEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="type_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">房屋类型：</td>
            <td><input id="type_name" class="easyui-textbox"  style="width:200px;height:28px" data-options="required:true"></td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveType()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeType()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>




<script type="text/javascript" src="${path}/static/js/type.js?num=<%=new Date()%>"></script>


</body>
</html>
