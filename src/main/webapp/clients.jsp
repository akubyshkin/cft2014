<%@page import="cft2014.runner.service.IClientManagementService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="cft2014.runner.entity.Client"%>
<%@page import="java.util.List"%>
<html>
  <head>
    <title>Clients3</title>
  </head>
  <body>
    List of clients:
    <br>
    <table border="1">
      <tr>
        <td>ID</td>
        <td>FIRST_NAME</td>
        <td>SECOND_NAME</td>
        <td>LAST_NAME</td>
      </tr>
    <%
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    IClientManagementService clientMS = ctx.getBean(IClientManagementService.class);
    List<Client> list = clientMS.listAll();
    for(Client c : list) {
      %>
      <tr>
        <td><%=c.getId() %></td>
        <td><%=c.getFirstName() %></td>
        <td><%=c.getSecondName() %></td>
        <td><%=c.getLastName() %></td>
      </tr>
      <%
    }
    %>
    </table>
  </body>
</html>