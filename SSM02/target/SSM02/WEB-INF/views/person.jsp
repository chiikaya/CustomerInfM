<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>联系人</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findPersonByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

                <%-- 盒子2 --%>
                <span style="margin-left: 30px">请选择查询方式：</span><select id="state4" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                <option value="PEName" selected>联系人姓名</option>
                <option value="PEKH">属于的客户</option>
            </select>
            <%-- 搜索按钮--%>
            <a href="javascript:findPerson()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                搜索
            </a>
            <%--添加按钮--%>
            <a id="addPerson" href="javascript:addPerson()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                添加
            </a>
            </div>
        </div>
    </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="personDataGrid">
        </table>


    </div>
</div>



<%-- 初始化编辑窗口    编号，属于的客户，联系人姓名，性别，年龄，职位，电话，与客户关系   --%>
<!--person_id,person_customer,person_name,person_sex,person_age,person_post,person_tel,customer_nexus-->
<div id="personEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="person_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">关联客户：</td>
            <td>
                <select id="person_customer"     style="width:230px;height:28px" >
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">联系人姓名：</td>
            <td><input id="person_name" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">性别：</td>
            <td>
                <select id="person_sex" class="easyui-combobox" data-options="editable:false"   style="width:230px;">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">年龄：</td>
            <td><input id="person_age"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">职位：</td>
            <td><input id="person_post"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">电话：</td>
            <td><input id="person_tel"  class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">与客户关系：</td>
            <td><input id="customer_nexus" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>

    </table>

    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:savePerson()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closePerson()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>





<%--num=<%=new Date()%> :清空缓存 --%>
<script type="text/javascript" src="${path}/static/js/person.js?num=<%=new Date()%>"></script>

</body>
</html>
