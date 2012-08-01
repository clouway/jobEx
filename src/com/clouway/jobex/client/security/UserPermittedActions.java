package com.clouway.jobex.client.security;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserPermittedActions {

//  public void add(Class<?extends SecuredAction> securedAction);

  public boolean isPermitted(Class<? extends SecuredAction> actionClass);

  void setPermittedActions(List<String> permission);

  boolean isPermitted(String permission);
}
