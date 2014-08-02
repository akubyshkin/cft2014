package cft2014.runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cft2014.runner.entity.Client;

//public class BootstrapListener implements ServletContextListener {
public class BootstrapListener extends org.springframework.web.context.ContextLoaderListener {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextInitialized");
    super.contextInitialized(arg0);
    // initDatabase("JDBC", null);
    // initDatabase("Hibernate", null);
    initDatabase("SpringHibernate", arg0);
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    super.contextDestroyed(arg0);
    System.out.println("BootstrapListener: onContextDestroyed");
  }

  private void initDatabase(String type, ServletContextEvent arg0) {
    if ("JDBC".equals(type)) {
      initJDBCDatabase();
    } else if ("Hibernate".equals(type)) {
      initHibernateDatabase();
    } else if ("SpringHibernate".equals(type)) {
      initSpringHibernateDatabase(arg0);
    }
  }

  private void initJDBCDatabase() {
    DataSource ds = AppUtil.getDataSource();

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
    initHibernateDatabase(session);
  }

  private void initHibernateDatabase(Session session) {
    session.save(new Client("Alexander", "Sergeevich", "Kubyshkin"));
    session.save(new Client("Anastasiya", "Sergeevna", "Kubyshkina"));
    session.close();
  }

  private void initSpringHibernateDatabase(ServletContextEvent arg0) {
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
    SessionFactory sf = ctx.getBean(SessionFactory.class);
    Session s = sf.openSession();
    initHibernateDatabase(s);
  }
}
