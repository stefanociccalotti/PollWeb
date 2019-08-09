<%@ page import="it.univaq.disim.model.UserModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
    <%
        UserModel u = (UserModel) request.getAttribute("userModel");

        out.println(u);
    %>
</body>
</html>
