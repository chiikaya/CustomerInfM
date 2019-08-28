<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>角色</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin-left: 100px;margin-top: 15px;margin-bottom: 10px">
            <span>角色名称：</span><input id="findRoleByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">
            <span style="margin-left: 50px">角色描述：</span><input id="byDesc" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">&nbsp;&nbsp;&nbsp;
            <%-- 搜索按钮--%>
            <a href="javascript:findRole()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addRole" href="javascript:addRole()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="roleDataGrid">
        </table>

        <%--角色授权 --%>
        <div id="empowerAuthWindow" data-options="closed:true" >
            <ul id="empowerAuthTree" style="margin-top: 10px"></ul>
            <%--保存按钮--%>
            <a href="javascript:savaAuthOfRole()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width: 80px;margin-left: 35px;margin-top: 30px">
                保存
            </a>
            <%--关闭按钮--%>
            <a href="javascript:closeempowerAuthWindow()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" style="width: 80px;margin-left:30px;margin-top: 30px">
                关闭
            </a>

        </div>

    </div>

</div>


<%--角色授权：
    1.准备一个超连接，点击时弹出一个窗口，窗口内准备一个带checkbox的tree
    窗口内显示所有的权限，当前角色已经具备的那些权限需要被勾选。

    2.拿到所有被勾选的权限的id，插入数据库。
      a. 两部分对比。
      b. delete from sys_role_auth where role_id =?
         新传过来的全部插入
         --%>





<%-- 初始化编辑窗口    角色ID，角色名称，描述，排序，是否再用  --%>
<div id="roleEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="role_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">角色名称：</td>
            <td><input id="role_name" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">角色描述：</td>
            <td><input id="role_desc" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">角色排序：</td>
            <td><input id="role_order"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">是否再用：</td>
            <td>
                <select id="inuse2" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">可用</option>
                    <option value="1">禁用</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveRole()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeRole()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>



<%--num=<%=new Date()%> :清空缓存 --%>
<script type="text/javascript" src="${path}/static/js/role.js?num=<%=new Date()%>"></script>

</body>
</html>
