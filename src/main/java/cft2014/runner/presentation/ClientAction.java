package cft2014.runner.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft2014.runner.entity.Client;
import cft2014.runner.service.IClientManagementService;

@Component
public class ClientAction implements IAction {

  @Autowired
  private IClientManagementService clientMS;

  @Override
  public String getForwardPath(HttpServletRequest req, HttpServletResponse resp) {
    prepare(req, resp);
    return req.getPathInfo();
  }

  @Override
  public String getActionPath() {
    return "/client";
  }

  private void prepare(HttpServletRequest req, HttpServletResponse resp) {
    String type = req.getParameter("type");
    String id = req.getParameter("id");
    Client c;
    if (id != null) {
      c = clientMS.find(Integer.parseInt(id));
    } else {
      if (req.getParameter("submit") != null) {
        c = new Client(req.getParameter("firstName"), req.getParameter("secondName"), req.getParameter("lastName"));
        c = clientMS.update(c);
      } else {
        c = new Client("", "", "");
      }
    }
    if (type == null) {
      type = "view";
      if (req.getParameter("submit") != null) {
        c.setFirstName(req.getParameter("firstName"));
        c.setSecondName(req.getParameter("secondName"));
        c.setLastName(req.getParameter("lastName"));
        c = clientMS.update(c);
      }
    }
    req.setAttribute("client", c);
    req.setAttribute("type", type);
  }
}
