package com.clouway.jobex.client.security;

import com.google.inject.Inject;

import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredActionMapperImpl implements SecuredActionMapper {


  private final Map<String, Class<? extends SecuredAction>> securedActionMap;

  @Inject
  public SecuredActionMapperImpl(Map<String, Class<? extends SecuredAction>> securedActionMap) {

    this.securedActionMap = securedActionMap;

  }

  public Class<? extends SecuredAction> getSecuredAction(String actionName) {

    Class<? extends SecuredAction> securedAction = securedActionMap.get(actionName);

    if (securedAction == null) {
//      throw new NotActionBoundException("action:"+actionName+" to:"+securedAction);
  }
    return securedAction;
  }


}
