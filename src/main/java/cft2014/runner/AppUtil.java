package cft2014.runner;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class AppUtil {
  private static DataSource dataSource;
  
  static {
    JDBCDataSource ds = new JDBCDataSource();
    ds.setUrl("jdbc:hsqldb:mem:clients");
    ds.setUser("sa");
    ds.setPassword("");
    dataSource = ds;
  }

  public static DataSource getDataSource() {
    return dataSource;
  }
}
