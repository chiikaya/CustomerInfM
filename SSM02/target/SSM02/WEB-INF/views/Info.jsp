<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>房屋信息</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findInfoByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

                <%-- 盒子2 --%>
                <span style="margin-left: 30px">请选择查询方式：</span><select id="state5" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                <option value="Type" selected>房屋类型</option>
                <option value="Adress">房屋地址</option>
            </select>

                <%--盒子3--%>
                <%-- 搜索按钮--%>
                <a href="javascript:findInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                    搜索
                </a>
                <%--添加按钮--%>
                <a id="addInfo" href="javascript:addInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                    添加
                </a>
            </div>

        </div>
    </div><%--上面尾--%>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="infoDataGrid">
        </table>


    </div>

</div>


<%-- 初始化编辑窗口    info_id,type_id,staff_id,info_adress,info_price --%>
<div id="infoEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="info_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">房屋类型：</td>
            <td>
                <select id="type_id2"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">管理员工：</td>
            <td>
                <select id="staff_id2"   style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">房屋地址：</td>
            <td><input id="info_adress" class="easyui-textbox"  style="width:230px;height:60px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">房屋价格：</td>
            <td><input id="info_price" class="easyui-numberbox"  style="width:230px;height:28px" ></td>
        </tr>
    </table>
    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveInfo()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeInfo()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>




<script type="text/javascript" src="${path}/static/js/Info.js?num=<%=new Date()%>"></script>


</body>
</html>
