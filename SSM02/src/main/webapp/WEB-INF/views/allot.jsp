<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<%@include file="commons.jsp"%>
<html>
<head>
    <title>客户分配</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
    <table id="allotDataGrid">
    </table>

</div>




<script type="text/javascript" src="${path}/static/js/allot.js?num=<%=new Date()%>"></script>



</body>
</html>
