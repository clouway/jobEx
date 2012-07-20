package com.clouway.jobex.server.useraccess;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AuthorizationService {
  
  public void register(String registrationType, String email, String password);

  public boolean verifyLogin(String loginType, String email, String password);
}
