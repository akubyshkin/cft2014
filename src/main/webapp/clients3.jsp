<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="cft2014.runner.entity.Client"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
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
    SessionFactory sf = ctx.getBean(SessionFactory.class);
    Session s = sf.openSession();
    Query q = s.createQuery("from Client c");
    List<Client> list = q.list();
    s.close();
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