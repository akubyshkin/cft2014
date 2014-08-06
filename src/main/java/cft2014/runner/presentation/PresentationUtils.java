package cft2014.runner.presentation;

public class PresentationUtils {

  public final static String VIEW = ".view";
  public final static String EDIT = ".edit";

  public static String getActionType(String pathInfo) {
    if (pathInfo.endsWith(EDIT)) {
      return EDIT;
    }
    return VIEW;
  }

  public static String getForwardPath(String pathInfo) {
    if (pathInfo.endsWith(EDIT)) {
      return cutSuffix(pathInfo, EDIT);
    } else if (pathInfo.endsWith(VIEW)) {
      return cutSuffix(pathInfo, VIEW);
    }
    return pathInfo;
  }

  public static String cutSuffix(String str, String suffix) {
    return str.substring(0, str.length() - suffix.length() - 1);
  }
}
