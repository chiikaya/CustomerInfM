<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>联系记录</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findREByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

                <%-- 盒子2 --%>
                <span style="margin-left: 30px">请选择查询方式：</span><select id="state3" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                <option value="REName" selected>客户姓名</option>
                <option value="REType">联系类型</option>
                <option value="REText">联系主题</option>
            </select>

            <%-- 搜索按钮--%>
            <a href="javascript:findRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addRecord" href="javascript:addRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
          </div>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="recordDataGrid">
        </table>


    </div>
</div>



<%-- 初始化编辑窗口    编号,姓名,联系时间,下次联系时间,联系类型,是谁联系,联系主题,联系备注  --%>
<!--record_id,record_name,create_time,reply_time, record_type,record_who,record_text,record_desc-->
<div id="recordEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="record_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户姓名：</td>
            <td>
                <select id="KHName"     style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">联系时间：</td>
            <td>
                <input  id="create_time" class="easyui-datetimebox"  style="width:230px;height:28px">
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">下次联系时间：</td>
            <td>
                <input  id="reply_time" class="easyui-datetimebox"  style="width:230px;height:28px">
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">联系类型：</td>
            <td><input id="record_type" class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">是谁联系：</td>
            <td>
                <select id="record_who"     style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">联系主题：</td>
            <td><input id="record_text"  class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">联系备注：</td>
            <td><input id="record_desc" class="easyui-textbox"  style="width:230px;height:60px"></td>
        </tr>

    </table>

    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveRecord()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeRecord()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>





<%--num=<%=new Date()%> :清空缓存 --%>
<script type="text/javascript" src="${path}/static/js/record.js?num=<%=new Date()%>"></script>

</body>
</html>
