package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.exceptions.EmailAlreadyExistsException;import com.clouway.jobex.shared.exceptions.EmailIsNotRegisteredException;import com.clouway.jobex.shared.exceptions.WrongPasswordException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationServiceImpl implements AuthorizationService {

  private AuthorizationRepository authorizationRepository;

  public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository){

    this.authorizationRepository = authorizationRepository;
  }
  
  public void register(String registrationType, String email, String password){

    if(!authorizationRepository.isNotRegister(registrationType,email)){
      throw new EmailAlreadyExistsException();
    }
    authorizationRepository.register(registrationType,email,password);
  }

  public boolean verifyLogin(String loginType, String email, String password) {
    return !authorizationRepository.isNotRegister(loginType, email) && authorizationRepository.verifyUserPassword(loginType, email, password);

  }
}
