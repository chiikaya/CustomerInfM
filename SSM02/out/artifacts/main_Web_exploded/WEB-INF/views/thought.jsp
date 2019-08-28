<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>客户关怀</title>
</head>
<body>


<div class="easyui-layout" data-options="fit:true">

    <%--上面--%>
    <div data-options="region:'north'" style="height:70px">
        <div style="margin: 0px;padding: 0px">

            <%--盒子1--%>
            <div style="float: left;margin-left: 200px;margin-top: 18px">
                <span>请输入输入查询内容：</span>
                <input id="findThoughtByName" class="easyui-textbox"   style="width:150px;height:28px;margin-left: 30px">

                <%-- 盒子2 --%>
                <span style="margin-left: 30px">请选择查询方式：</span><select id="state2" class="easyui-combobox" style="width:150px;height:28px;margin-left: 30px">
                <option value="THName" selected>客户</option>
                <option value="THText">关怀主题</option>
                <option value="THWay">关怀方式</option>
            </select>

        <%--盒子3--%>
                <a href="javascript:findThought()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px;margin-left: 30px">
                  搜索
               </a>
              <%--添加按钮--%>
              <a id="addThought" href="javascript:addThought()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;margin-left: 30px">
                   添加
               </a>
             </div>
         </div>
     </div>
    <%--中间--%>
    <div data-options="region:'center'">
        <%--设置分页--%>
        <table id="thoughtDataGrid">
        </table>


    </div>
</div>



<%-- 初始化编辑窗口    编号，主题，方式，关怀时间，下次关怀时间，备注  --%>
<%--thought_id,thought_name,thought_text,thought_way,create_time,reply_time,thought_desc --%>
<div id="thoughtEditWindow" class="easyui-window" data-options="closed:true">
    <table  style="margin-top: 10px">
        <tr>
            <td><input type="hidden" id="thought_id"/></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">客户：</td>
            <td>
               <select id="KHByName"     style="width:230px;height:28px" >
               </select>
            </td>
        </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">关怀主题：</td>
            <td><input id="thought_text" class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">关怀方式：</td>
            <td><input id="thought_way"  class="easyui-textbox"  style="width:230px;height:28px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">关怀时间：</td>
            <td>
                <input  id="create_time2" class="easyui-datetimebox"  style="width:230px;height:28px">
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">下次关怀时间：</td>
            <td>
                <input  id="reply_time2" class="easyui-datetimebox"  style="width:230px;height:28px">
            </td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">备注：</td>
            <td><input id="thought_desc"  class="easyui-textbox"  style="width:230px;height:60px" ></td>
        </tr>
        <tr>
            <td style="width: 100px" align="right">关怀人：</td>
            <td><input id="person" class="easyui-textbox"  style="width:230px;height:28px"></td>
        </tr>

    </table>

    <div style="margin-top: 40px;margin-left: 110px">
        <a href="javascript:saveThought()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-save'">
            保&nbsp;存
        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:closeThought()" style="width: 80px;height: 30px" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">
            关&nbsp;闭
        </a>
    </div>
</div>





<%--num=<%=new Date()%> :清空缓存 --%>
<script type="text/javascript" src="${path}/static/js/thought.js?num=<%=new Date()%>"></script>

</body>
</html>
