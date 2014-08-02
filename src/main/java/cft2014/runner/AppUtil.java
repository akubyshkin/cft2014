package cft2014.runner;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hsqldb.jdbc.JDBCDataSource;

public class AppUtil {
  private static DataSource dataSource;
  private static SessionFactory sessionFactory;
  
  public static DataSource getDataSource() {
    if (dataSource == null) {
      JDBCDataSource ds = new JDBCDataSource();
      ds.setUrl("jdbc:hsqldb:mem:clients");
      ds.setUser("sa");
      ds.setPassword("");
      dataSource = ds;
    }
    return dataSource;
  }
  
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    return sessionFactory;
  }
}
