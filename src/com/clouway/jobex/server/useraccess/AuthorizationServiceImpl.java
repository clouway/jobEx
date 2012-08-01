package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.SecuredActionsNamesProvider;
import com.clouway.jobex.shared.Token;
import com.clouway.jobex.shared.exceptions.EmailAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

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

    if (!authorizationRepository.isNotRegister(registrationType, email)) {
      throw new EmailAlreadyExistsException();
    }
    authorizationRepository.register(registrationType, email, password);
  }



  public Token login(String loginType, String email, String password) {

    if (authorizationRepository.isAuthorized(loginType, email, password)) {
      String id = idGenerator.generateId();
      authorizationRepository.saveAsLogged(email, loginType, id);

      if (UserCredentialsLocalStorage.USER.equals(loginType)) {

        return new Token(id, email, namesProvider.getUserActions());

      }

      if (UserCredentialsLocalStorage.COMPANY.equals(loginType)) {

        return new Token(id, email, namesProvider.getCompanyActions());

      }
    }
    return null;
  }


  public List<Class<? extends SecuredAction>> testAction() {
    return new ArrayList<Class<? extends SecuredAction>>() {{
      add(SecuredAction.class);
    }};
  }

  @Override
  public Boolean isValid(String sid) {
    return authorizationRepository.isSIDRegistered(sid);
  }
}
