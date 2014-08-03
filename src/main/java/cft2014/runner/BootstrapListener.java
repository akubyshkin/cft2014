package cft2014.runner;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cft2014.runner.entity.Client;
import cft2014.runner.service.IClientManagementService;

public class BootstrapListener extends ContextLoaderListener {

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("BootstrapListener: onContextInitialized");
    super.contextInitialized(arg0);
    initDatabase(arg0);
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    super.contextDestroyed(arg0);
    System.out.println("BootstrapListener: onContextDestroyed");
  }

  private void initDatabase(ServletContextEvent arg0) {
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
    IClientManagementService clientMS = ctx.getBean(IClientManagementService.class);
    clientMS.create(new Client("Alexander", "Sergeevich", "Kubyshkin"));
    clientMS.create(new Client("Anastasiya", "Sergeevna", "Kubyshkina"));
  }
}
