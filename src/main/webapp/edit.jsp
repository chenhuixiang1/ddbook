<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>退出</title>
</head>
<body>
<%
    request.removeAttribute("Logined");
    response.sendRedirect("login.jsp");
%>
</body>
</html>
