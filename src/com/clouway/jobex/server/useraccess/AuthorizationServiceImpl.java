package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.AccountType;
import com.clouway.jobex.shared.SecuredActionsNamesProvider;
import com.clouway.jobex.shared.UserCredentials;
import com.clouway.jobex.shared.exceptions.EmailAlreadyExistsException;
import com.clouway.jobex.shared.exceptions.InvalidEmailFormatException;

import java.util.UUID;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationServiceImpl implements AuthorizationService {

  private AuthorizationRepository authorizationRepository;

  private Generator idGenerator;

  private final SecuredActionsNamesProvider namesProvider;

  public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository,
                                  Generator idGenerator,
                                  SecuredActionsNamesProvider namesProvider) {
    this.authorizationRepository = authorizationRepository;
    this.idGenerator = idGenerator;
    this.namesProvider = namesProvider;
  }

  public void register(String registrationType, String email, String password) {

    if(!email.matches("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*")){
      throw new InvalidEmailFormatException();
    }

    if(!authorizationRepository.isNotRegister(registrationType,email)){
      throw new EmailAlreadyExistsException();
    }
    authorizationRepository.register(registrationType, email, password);
  }



  public UserCredentials login(String loginType, String email, String password) {

    if (authorizationRepository.isAuthorized(loginType, email, password)) {

      String id = idGenerator.generateId();

      authorizationRepository.saveAsLogged(email, loginType, id);

      if (AccountType.USER.equals(loginType)) {

        return new UserCredentials(id, email, namesProvider.getUserActions());

      }

      if (AccountType.COMPANY.equals(loginType)) {

        return new UserCredentials(id, email, namesProvider.getCompanyActions());

      }

    }
    return null;
  }

  @Override
  public Boolean isValid(String sid) {
    return authorizationRepository.isSIDRegistered(sid);
  }

  @Override
  public void logout(String email) {
    authorizationRepository.deleteLoggedData(email);
  }
}
