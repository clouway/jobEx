package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.UserCredentials;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AuthorizationService {
  
  public void register(String registrationType, String email, String password);

  public UserCredentials login(String loginType, String email, String password);

  Boolean isValid(String sid);
}
