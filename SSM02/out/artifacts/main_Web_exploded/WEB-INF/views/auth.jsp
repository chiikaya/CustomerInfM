<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>权限</title>

</head>
<body>

<%--<h1>欢迎访问权限管理</h1>--%>

<table id="authTreeGrid"  style="width:950px;height:250px">
</table>

<div id="authMenu" class="easyui-menu" style="width:120px;">
    <div id="addAuth" onclick="addAuth()" data-options="iconCls:'icon-add'">
        增加节点
    </div>
    <div id="editAuth" onclick="editAuth()" data-options="iconCls:'icon-edit'">
        修改节点
    </div>
    <div id="refreshAuth" onclick="refreshAuth()" data-options="iconCls:'icon-reload'">
        刷新节点
    </div>
</div>


<%-- 初始化编辑窗口--%>
<div id="authEditWindow" class="easyui-window" data-options="closed:true">
    <table >
        <tr>
            <td><input type="hidden" id="auth_id"/></td>
            <td><input type="hidden" id="parent_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">父权限：</td>
            <td><input id="parentAuth" class="easyui-textbox" data-options="disabled:true"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限层级：</td>
            <td><input id="auth_layer" class="easyui-textbox" data-options="disabled:true" style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限名称：</td>
            <td><input id="auth_name" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限描述：</td>
            <td><input id="auth_desc" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限url：</td>
            <td><input id="auth_url" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限排序：</td>
            <td><input id="auth_order"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">权限类型：</td>
            <td>
                <select id="auth_type" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">模块</option>
                    <option value="1">资源</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">是否再用：</td>
            <td>
                <select id="inuse" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">可用</option>
                    <option value="1">禁用</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveAuth()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeAuth()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>


<%--num=<%=new Date()%> :清空缓存 --%>
<script type="text/javascript" src="${path}/static/js/auth.js?num=<%=new Date()%>"></script>



</body>
</html>
