package cft2014.runner;

import javax.servlet.ServletContextEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cft2014.runner.entity.Client;

public class BootstrapListener extends ContextLoaderListener {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextInitialized");
    super.contextInitialized(arg0);
    initDatabase("SpringHibernate", arg0);
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    super.contextDestroyed(arg0);
    System.out.println("BootstrapListener: onContextDestroyed");
  }

  private void initDatabase(String type, ServletContextEvent arg0) {
    final WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0
        .getServletContext());
    PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
    TransactionTemplate tt = new TransactionTemplate(tm);
    tt.execute(new TransactionCallback<Void>() {
      @Override
      public Void doInTransaction(TransactionStatus status) {
        SessionFactory sf = ctx.getBean(SessionFactory.class);
        Session s = sf.getCurrentSession();
        initHibernateDatabase(s);
        return null;
      }
    });
  }

  private void initHibernateDatabase(Session session) {
    session.save(new Client("Alexander", "Sergeevich", "Kubyshkin"));
    session.save(new Client("Anastasiya", "Sergeevna", "Kubyshkina"));
  }
}
