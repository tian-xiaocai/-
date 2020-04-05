<%@ page import="Model.Dao.GoodsDao.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sun.xml.internal.bind.v2.model.core.ID" %><%--
  Created by IntelliJ IDEA.
  User: tianshuainan
  Date: 2020/4/1
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>展示goods</title>
  </head>
  <body>
  <a href="logout">注销</a>
  <a href="add.jsp" style="color: red;font-size: xx-large;font-family: 'Adobe Caslon Pro'">添加商品</a>

  <table>
      <%--展示的要求是一行四个商品--%>
  <%
    List< Goods > list = (List< Goods >)request.getAttribute("list");
    int col=4;
      if (list!=null)
    for(int i=0;i<list.size();i++){
        Goods goods = list.get(i);
        if (i%col==0){
  %>
    <tr>
        <%}%>
        <td><img src="<%=goods.getImg()%>" width="200px" height="200px"><br>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;<%=goods.getName()%> &nbsp; &nbsp;&nbsp;<%=goods.getPrice()%>
        </td>
        <%if (i%col==col-1){%>
    </tr>
          <%}%>





  <%}%>

  </table>

  </body>
</html>
