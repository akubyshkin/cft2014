package cft2014.runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cft2014.runner.entity.Client;

public class BootstrapListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextInitialized");
    // initDatabase("JDBC");
    initDatabase("Hibernate");
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextDestroyed");
  }

  private void initDatabase(String type){
    if ("JDBC".equals(type)) {
      initJDBCDatabase();
    } else if ("Hibernate".equals(type)) {
      initHibernateDatabase();
    }
  }

  private void initJDBCDatabase() {
    DataSource ds = AppUtil.getDataSource();
    // JDBCDataSource ds = new JDBCDataSource();
    // ds.setUrl("jdbc:hsqldb:mem:clients");
    // ds.setUser("sa");
    // ds.setPassword("");

    try (Connection c = ds.getConnection();) {
      String createTable = "CREATE TABLE CLIENTS (ID INTEGER NOT NULL IDENTITY, "
          + "FIRST_NAME VARCHAR(250) NOT NULL, SECOND_NAME VARCHAR(250), LAST_NAME VARCHAR(250) NOT NULL)";
      PreparedStatement s = c.prepareStatement(createTable);
      s.executeUpdate();
      s = c.prepareStatement("insert into CLIENTS(FIRST_NAME, SECOND_NAME, LAST_NAME) "
          + "values('Alexander','Sergeevich','Kubyshkin');");
      s.executeUpdate();
      s = c.prepareStatement("insert into CLIENTS(FIRST_NAME, SECOND_NAME, LAST_NAME) "
          + "values('Anastasiya','Sergeevna','Kubyshkina');");
      s.executeUpdate();
      c.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void initHibernateDatabase() {
    SessionFactory sf = AppUtil.getSessionFactory();
    Session session = sf.openSession();
    session.save(new Client("Alexander", "Sergeevich", "Kubyshkin"));
    session.save(new Client("Anastasiya", "Sergeevna", "Kubyshkina"));
    session.close();
  }
}
