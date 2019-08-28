<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List,java.util.Map" %>

<c:set var="path" value="${pageContext.request.contextPath}" scope="session"/>

<%--引入js--%>
<script type="text/javascript" src="${path}/static/js/jquery-3.2.1.min.js"></script>

<%--引入cookie--%>
<script type="text/javascript" src="${path}/static/js/jquery.cookie.js"></script>

<%--引入easyui--%>
<link href="${path}/static/easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" >
<link href="${path}/static/easyui/themes/icon.css" rel="stylesheet" type="text/css" >

<script src="${path}/static/easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="${path}/static/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
    var path="${path}";
    var resArray = [];
</script>


<%--资源权限控制--%>
<%
    List<Map<String,Object>> resources
            = (List<Map<String, Object>>) session.getAttribute("resources");
%>
<%
    if(resources!=null && resources.size()>0){
        for(int i=0;i<resources.size();i++){%>
<script type="text/javascript">
    resArray.push('<%=resources.get(i).get("auth_url")%>');
</script>
<%  }
}
%>

<script type="text/javascript">
    function empowerResource(array) {
        console.log(resArray);
        for(var k=0;k<array.length;k++){
            if(resArray.indexOf(array[k])==-1){
                $("#"+array[k]).hide();
            }
        }
    }
</script>

<%--
在这准备一个数组，数组中存放所有资源权限的url，然后在auth.js中
声明auth.jsp中所有的资源权限，然后来此页面进行对比，auth.js中有，
但是上面数组中没有的，就给他隐藏掉

--%>

