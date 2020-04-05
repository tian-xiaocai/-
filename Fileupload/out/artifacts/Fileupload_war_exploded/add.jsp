<%--
  Created by IntelliJ IDEA.
  User: tianshuainan
  Date: 2020/4/2
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addGoods</title>
</head>
<body>
<div>
    <form action="add" method="post" enctype="multipart/form-data" >
    <input type="text" name="name" placeholder="商品名称"><br>
    <input name="price" type="text" placeholder="商品价格"><br>
    商品图片<br><input type="file" name="img" ><br>
    <input type="submit" value="添加商品"><br>
    </form>
</div>
</body>
</html>
