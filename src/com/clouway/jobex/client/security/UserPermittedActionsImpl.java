package com.clouway.jobex.client.security;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserPermittedActionsImpl implements UserPermittedActions {

  private List<String> permittedActions = new ArrayList<String>();


  @Override
  public void setPermittedActions(List<String> permissions) {
    permittedActions=permissions;
  }

  @Override
  public boolean isPermitted(String permission) {
    for (String action : permittedActions) {
      if (permission.equals(action)) {
        return true;
      }
    }
    return false;
  }
}
