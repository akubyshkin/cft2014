package cft2014.runner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BootstrapListener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextInitialized");
  }

  public void contextDestroyed(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextDestroyed");
  }
}
