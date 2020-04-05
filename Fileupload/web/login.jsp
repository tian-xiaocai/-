<%--
  Created by IntelliJ IDEA.
  User: tianshuainan
  Date: 2020/4/3
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<%              String nameError =(String) request.getAttribute("nameError");
                String passwordError =(String) request.getAttribute("passwordError");
              String flag_true =(String) request.getAttribute("true");
             String flag_false =(String) request.getAttribute("false");
             String uerError =(String) request.getAttribute("uerError");
            String erroradmin = (String) request.getAttribute("erroradmin");
         String errorA = (String) request.getAttribute("errorA");

%>
<form action="login" >
    <input type="text" placeholder="用户名" name="username"><span style="color:red;"><%=nameError == null ? "" : nameError %></span><br>
    <input  type="password" placeholder="密码" name="password"><br><span style="color:red;"><%=passwordError == null ? "" : passwordError %></span>
    <button type="submit" >登录</button><br>
    <span style="color: red"><%=uerError == null ? "" : uerError %></span>
    <span style="color: red"><%=erroradmin == null ? "" : erroradmin %></span>
    <span style="color: red"><%=errorA == null ? "" : errorA %></span>




</form>
</body>
</html>
