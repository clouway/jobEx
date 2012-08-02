package com.clouway.jobex.client.security;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserPermissionsImpl implements UserPermissions {

  private List<String> permittedActions = new ArrayList<String>();


  @Override
  public void setPermissions(List<String> permissions) {
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
