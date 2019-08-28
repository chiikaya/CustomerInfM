<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>客户信息</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findClientByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

             <%-- 盒子2 --%>
                    <span style="margin-left: 30px">请选择查询方式：</span><select id="state1" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                    <option value="Name" selected>客户姓名</option>
                    <option value="Inuse">客户状态</option>
                    <option value="Source">客户来源</option>
                    <option value="Type">客户类型</option>
                    <option value="Staff">所属员工</option>
                    <option value="Company">公司</option>
                </select>

            <%--盒子3--%>
                <%-- 搜索按钮--%>
                <a href="javascript:findClient()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                    搜索
                </a>
                <%--添加按钮--%>
                <a id="addClient" href="javascript:addClient()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                    添加
                </a>
            </div>

        </div>
    </div><%--上面尾--%>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="clientDataGrid">
        </table>

    </div>

</div>



<%-- 初始化编辑窗口
   编号，姓名，客户状态，客户来源，所属员工，客户类型，
   性别，手机，QQ，邮箱，职位，公司  --%>
<div id="clientEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="client_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户姓名：</td>
            <td><input id="clientname" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户状态：</td>
            <td>
                <select id="status_inuse1"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户来源：</td>
            <td>
                <select id="from_name1"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">所属员工：</td>
            <td>
                <select id="staff_name1"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户类型：</td>
            <td>
                <select id="mold_name1"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">性别：</td>
            <td>
                <select id="sex" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">电话：</td>
            <td><input id="tel1" class="easyui-numberbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">QQ：</td>
            <td><input id="QQ" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">电子邮箱：</td>
            <td><input id="email1" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">职位：</td>
            <td><input id="post" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">公司：</td>
            <td><input id="company" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveClient()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeClient()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>





<script type="text/javascript" src="${path}/static/js/client.js?num=<%=new Date()%>"></script>

</body>
</html>
