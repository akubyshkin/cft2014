<%@page import="cft2014.runner.service.IClientManagementService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="cft2014.runner.entity.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Клиент</title>
    <link rel="stylesheet" type="text/css" href="/general.css">
  </head>
  <body>
    <h3>Информация о клиенте</h3>
    <%
      Client c = (Client) request.getAttribute("client");
    %>
    <form action="/cft/client" method="POST">
      <table>
        <tr><td><table>
          <%if(c.getId() != null) { %>
          <tr><td>Идентификатор: </td><td><input type="text" name="id" value="<%=c.getId() %>" readonly/></td></tr> <%} %>
          <tr><td>Фамилия: </td><td><input type="text" name="lastName" value="<%=c.getLastName() %>"
          <% if("view".equals(request.getAttribute("type"))) { %>
          readonly
          <%}%>/></td></tr>
          <tr><td>Имя: </td><td><input type="text" name="firstName" value="<%=c.getFirstName() %>"
          <% if("view".equals(request.getAttribute("type"))) { %>
          readonly
          <%}%>/></td></tr>
          <tr><td>Отчество: </td><td><input type="text" name="secondName" value="<%=c.getSecondName() %>"
          <% if("view".equals(request.getAttribute("type"))) { %>
          readonly
          <%}%>/></td></tr>
        </table>
        <% if(!"view".equals(request.getAttribute("type"))) { %><input type="submit" name="submit" value="Сохранить"/>
        <input type="submit" name="reject" value="Отменить"/>
        <%} %>
        </td>
        <td>
          <table><tr>
            <td><a href="/cft/clients">Клиенты</a></td></tr>
            <% if("view".equals(request.getAttribute("type"))) { %>
            <tr><td><a href="/cft/client?id=<%=c.getId()%>&type=edit">Редактировать</a></td></tr>
            <%} %>
          </table>
        </td></tr>
      </table>
    </form>
  </body>
</html>