package cft2014.runner.presentation;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet(urlPatterns = "/cft/*")
public class GeneralServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private Map<String, IAction> actionsMap;

  private Collection<IAction> actions;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
    actions = ctx.getBeansOfType(IAction.class).values();
    actionsMap = new HashMap<String, IAction>();
    for (IAction action : actions) {
      actionsMap.put(action.getActionPath(), action);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    IAction action = actionsMap.get(req.getPathInfo());
    if (action != null) {
      String forwardPath = "/WEB-INF" + req.getServletPath() + action.getForwardPath(req, resp) + ".jsp";
      RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
