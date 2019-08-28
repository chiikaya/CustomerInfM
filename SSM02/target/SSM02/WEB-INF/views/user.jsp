<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>

    <title>用户</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height:70px">
        <div style="margin-left: 100px;margin-top: 15px;margin-bottom: 10px">
            <span style="margin-left: 150px">用户名称：</span><input id="findUserbyName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">
           <%-- 搜索按钮--%>
            <a href="javascript:findUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 50px">搜索</a>
            <%--添加新用户按钮--%>
            <a id="addUser" href="javascript:addUser()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 40px">添加</a>
        </div>
    </div>

    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="userDataGrid">
        </table>

            <%--用户授权 --%>
            <div id="empowerRoleWindow" data-options="closed:true" >
                <ul id="empowerRoleTree" style="margin-top: 10px"></ul>
                <%--保存按钮--%>
                <a href="javascript:savaRoleOfUser2()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width: 80px;margin-left: 35px;margin-top: 30px">
                    保存
                </a>
                <%--关闭按钮--%>
                <a href="javascript:closeempowerRoleWindow()" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" style="width: 80px;margin-left:30px;margin-top: 30px">
                    关闭
                </a>
            </div>

            <!-- 更改部门-->
            <div id="changeDeptWindow" data-options="closed:true">
                <ul id="changeDeptTree" style="margin-top: 10px"></ul>
                <!-- 这个是更改部门那个关闭-->
                <a href="javascript:saveDptOfUser()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px;margin-top: 20px">保存</a>
                <a href="javascript:closeDeptUserWindow()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px;margin-top: 20px">关闭</a>
            </div>

    </div>
</div>




<%-- 初始化用户编辑窗口    用户ID，用户名，密码，手机号，邮箱，是否再用  --%>
<div id="userEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="user_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">用户名称：</td>
            <td><input id="username" class="easyui-textbox" data-options="iconCls:'icon-man',iconWidth:38" style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">用户密码：</td>
            <td><input id="password" class="easyui-textbox" type="password" data-options="iconCls:'icon-lock',iconWidth:38" style="width:230px;height:28px" ></td>
        </tr>
        <tr>
        <td style="width: 100px" align="right">确认密码：</td>
        <td><input id="pwd2" class="easyui-passwordbox"  validType="confirmPass['#pass']" iconWidth="38"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">用户电话：</td>
            <td><input id="tel"  class="easyui-numberbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">电子邮箱：</td>
            <td><input id="email"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">所属部门：</td>
            <td>
                <select id="user_dept"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">是否再用：</td>
            <td>
                <select id="inuse1" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">可用</option>
                    <option value="1">禁用</option>
                </select>
            </td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveUser()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeUser()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>






<script type="text/javascript" src="${path}/static/js/user.js?num=<%=new Date()%>"></script>
</body>
</html>
