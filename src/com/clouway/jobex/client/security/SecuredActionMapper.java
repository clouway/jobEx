package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecuredActionMapper {

  public Class<? extends SecuredAction> getSecuredAction(String actionName);

}
