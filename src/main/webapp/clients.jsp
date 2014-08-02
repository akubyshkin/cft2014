<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="cft2014.runner.AppUtil"%>
<html>
  <head>
    <title>Clients</title>
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
    DataSource ds = AppUtil.getDataSource();
    Connection c = ds.getConnection();
    PreparedStatement s = c.prepareStatement("select * from clients");
    ResultSet rs = s.executeQuery();
    while(rs.next()) {
      Integer id = rs.getInt(1);
      String firstName = rs.getString(2);
      String secondName = rs.getString(3);
      String lastName = rs.getString(4);
      %>
      <tr>
        <td><%=id %></td>
        <td><%=firstName %></td>
        <td><%=secondName %></td>
        <td><%=lastName %></td>
      </tr>
      <%
    }
    c.commit();
    c.close();
    %>
    </table>
  </body>
</html>