package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecuredAction {


  public void execute();

  void onConditionsBreak();

}
