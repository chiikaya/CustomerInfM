<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>员工信息</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findDescByName4" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

                <%-- 盒子2 --%>
                <span style="margin-left: 30px">请选择查询方式：</span><select id="state4" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                <option value="Name1" selected>员工姓名</option>
                <option value="Dept_name1">部门</option>
                <option value="Role_name1">角色</option>
                <option value="Degree1">学历</option>
            </select>

                <%--盒子3--%>
                <%-- 搜索按钮--%>
                <a href="javascript:findDesc4()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                    搜索
                </a>
                <%--添加按钮--%>
                <a id="addDesc" href="javascript:addDesc()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                    添加
                </a>
            </div>

        </div>
    </div><%--上面尾--%>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="descDataGrid">
        </table>

    </div>

</div>


  <%--初始化编辑窗口--%>
<div id="descEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="staff_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">员工姓名：</td>
            <td><input id="staff_name" class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">年龄：</td>
            <td><input id="staff_age" class="easyui-numberbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">性别：</td>
            <td>
                <select id="staff_sex" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">民族：</td>
            <td><input id="staff_nation" class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">部门：</td>
            <td>
                <select id="staff_dept"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">角色：</td>
            <td>
                <select id="staff_role"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">学历：</td>
            <td><input id="staff_degree" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">婚姻：</td>
            <td>
            <select id="staff_marital" class="easyui-combobox" data-options="editable:false"  style="width:230px;">
                <option value="0">未婚</option>
                <option value="1">已婚</option>
            </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">家庭住址：</td>
            <td><input id="staff_address" class="easyui-textbox"  style="width:230px;height:60px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">手机：</td>
            <td><input id="staff_phone" class="easyui-numberbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">电话：</td>
            <td><input id="staff_tel" class="easyui-numberbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">Email：</td>
            <td><input id="staff_email" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveDesc()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeDesc()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>





<script type="text/javascript" src="${path}/static/js/desc.js?num=<%=new Date()%>"></script>
</body>
</html>
