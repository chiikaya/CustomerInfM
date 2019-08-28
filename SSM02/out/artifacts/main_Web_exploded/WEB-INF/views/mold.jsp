<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>客户类型</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin-left: 300px;margin-top: 15px;margin-bottom: 10px">
            <span>请输入客户类型：</span><input id="findMoldByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">
            <%-- 搜索按钮--%>
            <a href="javascript:findMold()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 150px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addMold" href="javascript:addMold()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="moldDataGrid">
        </table>


    </div>

</div>


<%-- 初始化编辑窗口    类型编号 ，客户类型 mold_id  mold_name --%>
<div id="moldEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="mold_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户类型：</td>
            <td><input id="mold_name" class="easyui-textbox"  style="width:200px;height:28px" data-options="required:true"></td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveMold()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeMold()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>


<script type="text/javascript" src="${path}/static/js/mold.js?num=<%=new Date()%>"></script>


</body>
</html>
