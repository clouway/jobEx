package com.clouway.jobex.client.security;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserPermittedActionsImpl implements UserPermittedActions {


  private final List<Class<? extends SecuredAction>> securedActions = new ArrayList<Class<? extends SecuredAction>>();

  private List<String> permittedActions = new ArrayList<String>();

  private final SecuredActionMapper mapper;

  @Inject
  public UserPermittedActionsImpl(SecuredActionMapper mapper) {
    this.mapper = mapper;
  }

  public boolean isPermitted(Class<? extends SecuredAction> actionClass) {
    for (Class<? extends SecuredAction> action : securedActions) {
      if (action.equals(actionClass)) {
        return true;
      }
    }
    return false;
  }

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

  private void add(Class<? extends SecuredAction> securedAction) {
    for (Class<? extends SecuredAction> actions : securedActions) {
      if (actions.equals(securedAction)) {
        throw new ActionHasBeenAddedTwinceException();
      }
    }
    securedActions.add(securedAction);
  }
}
