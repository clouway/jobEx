package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.AccountType;
import com.clouway.jobex.shared.SecuredActionsNamesProvider;
import com.clouway.jobex.shared.UserCredentials;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

public class AuthorizationServiceImplTest {


  private AuthorizationServiceImpl service;

  private String email = "test@email.com";

  private String password = "password";

  private String loinType = "type";

  private String generatedId = "123abc";


  @Mock
  AuthorizationRepository authorizationRepository;

  @Mock
  Generator idGenerator;

  @Mock
  private SecuredActionsNamesProvider namesProvider;

  @Before
  public void setUp() {

    initMocks(this);

    service = new AuthorizationServiceImpl(authorizationRepository, idGenerator,namesProvider);

  }





  @Test
  public void returnsTokenWithUserConstraintWhenUserCredentialsAreValid() {

    when(authorizationRepository.isAuthorized(AccountType.USER, email, password)).thenReturn(true);

    when(idGenerator.generateId()).thenReturn("123");

    UserCredentials userCredentials = service.login(AccountType.USER, email, password);

    verify(idGenerator).generateId();

    verify(authorizationRepository).isAuthorized(AccountType.USER, email, password);

    verify(namesProvider).getUserActions();

    assertThat(userCredentials, is(notNullValue()));

    assertThat(userCredentials.getSid(), is(equalTo("123")));

  }

  public void returnsTokenWithCompanyConstraintWhenCompanyCredentialsAreValid() {

    when(authorizationRepository.isAuthorized(AccountType.COMPANY, email, password)).thenReturn(true);

    when(idGenerator.generateId()).thenReturn("123");

    UserCredentials userCredentials = service.login(AccountType.COMPANY, email, password);

    verify(idGenerator).generateId();

    verify(authorizationRepository).isAuthorized(AccountType.COMPANY, email, password);

    verify(namesProvider).getUserActions();

    assertThat(userCredentials, is(notNullValue()));

    assertThat(userCredentials.getSid(), is(equalTo("123")));

  }


  @Test
  public void savesAuthorizedUserWhenCredentialsAreValid() {

    when(idGenerator.generateId()).thenReturn("value");

    when(authorizationRepository.isAuthorized(loinType, email, password)).thenReturn(true);

    service.login(loinType, email, password);

    verify(authorizationRepository).saveAsLogged(email, loinType, "value");
  }

  @Test
  public void returnsNullTokenIdWhenCredentialsAreInvalid() {

    when(authorizationRepository.isAuthorized(loinType, email, password)).thenReturn(false);

    UserCredentials userCredentials = service.login(loinType, email, password);

    assertThat(userCredentials, is(nullValue()));
  }


  @Test
  public void returnsFalseIfSIDIsNotRegistered() {

    String sid = "asd";

    when(authorizationRepository.isSIDRegistered(sid)).thenReturn(true);

    Boolean aBoolean = service.isValid(sid);

    verify(authorizationRepository).isSIDRegistered(sid);

    assertThat(aBoolean, is(true));

  }


  @Test
  public void returnsTrueIfSIDIsNotRegistered() {

    String sid = "asd";

    when(authorizationRepository.isSIDRegistered(sid)).thenReturn(false);

    Boolean aBoolean = service.isValid(sid);

    verify(authorizationRepository).isSIDRegistered(sid);

    assertThat(aBoolean, is(false));

  }

}
