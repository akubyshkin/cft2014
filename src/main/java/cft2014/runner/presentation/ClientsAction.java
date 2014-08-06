package cft2014.runner.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft2014.runner.service.IClientManagementService;

@Component
public class ClientsAction implements IAction {

  @Autowired
  private IClientManagementService clientMS;

  @Override
  public String getForwardPath(HttpServletRequest req, HttpServletResponse resp) {
    prepare(req, resp);
    return req.getPathInfo();
  }

  @Override
  public String getActionPath() {
    return "/clients";
  }

  private void prepare(HttpServletRequest req, HttpServletResponse resp) {
    req.setAttribute("clients", clientMS.listAll());
  }
}
