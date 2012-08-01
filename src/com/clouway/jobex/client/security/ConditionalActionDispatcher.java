package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ConditionalActionDispatcher {


  public void dispatch(final SecuredAction securedAction, Condition... conditions);


  public void addConditions(Class<? extends SecuredAction> testActionClass, Condition... conditions);

}
