package cft2014.runner.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
  public String getForwardPath(HttpServletRequest req, HttpServletResponse resp);

  public String getActionPath();
}
