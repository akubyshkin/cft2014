<%@page import="cft2014.runner.entity.Client"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="cft2014.runner.AppUtil"%>
<html>
  <head>
    <title>Clients2</title>
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
    SessionFactory fs = AppUtil.getSessionFactory();
    Session s = fs.openSession();
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