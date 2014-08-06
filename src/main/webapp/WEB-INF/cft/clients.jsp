<%@page import="cft2014.runner.entity.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Клиенты</title>
    <link rel="stylesheet" type="text/css" href="/general.css">
  </head>
  <body>
    <h3>Список клиентов</h3>
    <br>
    <table border="1">
      <tr>
        <td>Идентификатор</td>
        <td>Фамилия</td>
        <td>Имя</td>
        <td>Отчетство</td>
      </tr>
    <%
    List<Client> list = (List<Client>) request.getAttribute("clients");
    for(Client c : list) {
      %>
      <tr>
        <td><a href="/cft/client?id=<%=c.getId()%>"><%=c.getId() %></a></td>
        <td><a href="/cft/client?id=<%=c.getId()%>"><%=c.getLastName() %></a></td>
        <td><a href="/cft/client?id=<%=c.getId()%>"><%=c.getFirstName() %></a></td>
        <td><a href="/cft/client?id=<%=c.getId()%>"><%=c.getSecondName() %></a></td>
      </tr>
      <%
    }
    %>
    </table>
    <a href="/cft/client?type=edit">Добавить</a>
  </body>
</html>