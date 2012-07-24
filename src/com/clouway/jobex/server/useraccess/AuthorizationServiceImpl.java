package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.exceptions.EmailAlreadyExistsException;import com.clouway.jobex.shared.exceptions.EmailIsNotRegisteredException;import com.clouway.jobex.shared.exceptions.WrongPasswordException;

import java.util.UUID;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationServiceImpl implements AuthorizationService {

  private AuthorizationRepository authorizationRepository;
  private Generator idGenerator;

  public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository, Generator idGenerator){

    this.authorizationRepository = authorizationRepository;
    this.idGenerator = idGenerator;
  }
  
  public void register(String registrationType, String email, String password){

    if(!authorizationRepository.isNotRegister(registrationType,email)){
      throw new EmailAlreadyExistsException();
    }
    authorizationRepository.register(registrationType,email,password);
  }

  public String login(String loginType, String email, String password) {
    
    if(authorizationRepository.isNotRegister(loginType, email)){
      return "";
    }
    if( !authorizationRepository.verifyUserPassword(loginType,email, password)){
      return "";
    }
    String id = idGenerator.generateId();
    saveAsLogged(email, loginType, id);
    return id;
  }

  private void saveAsLogged(String email, String loginType, String id){
    authorizationRepository.saveAsLogged(email, loginType, id);
  }

  public boolean isUserAuthorized(String email, String id){
    return authorizationRepository.isUserAuthorized(email, id);
  }
}
