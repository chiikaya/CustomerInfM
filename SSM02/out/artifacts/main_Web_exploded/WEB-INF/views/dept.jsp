<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>部门</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin-left: 100px;margin-top: 15px;margin-bottom: 10px">
            <span>部门名称：</span><input id="findByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">
            <span style="margin-left: 50px">部门描述：</span><input id="findByDesc" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">&nbsp;&nbsp;&nbsp;
            <%-- 搜索按钮--%>
            <a href="javascript:findDept()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addDept" href="javascript:addDept()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="deptDataGrid">
        </table>


    </div>

</div>


<%-- 初始化编辑窗口   部门ID，部门名称，部门位置(location) ，是否可用  Dept  Dept --%>
<div id="deptEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="dept_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">部门名称：</td>
            <td><input id="dept_name" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">部门描述：</td>
            <td><input id="dept_desc" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">部门位置：</td>
            <td><input id="dept_location"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">是否再用：</td>
            <td>
                <select id="inuse3" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">可用</option>
                    <option value="1">禁用</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveDept()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeDept()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>







<script type="text/javascript" src="${path}/static/js/dept.js?num=<%=new Date()%>"></script>

</body>
</html>
