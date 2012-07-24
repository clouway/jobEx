package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.exceptions.EmailAlreadyExistsException;
import com.clouway.jobex.shared.exceptions.EmailIsNotRegisteredException;
import com.clouway.jobex.shared.exceptions.WrongPasswordException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class AuthorizationServiceImplTest {

  Mockery context = new JUnit4Mockery();

  private AuthorizationRepository authorizationRepository = context.mock(AuthorizationRepository.class);
  private Generator idGenerator = context.mock(Generator.class);

  private AuthorizationService service;

  private String email = "test@email.com";
  private String password = "password";
  private String authorizationType = "registrationTypeIsNotImportant";
  private String generatedId = "123abc";

  @Before
  public void setUp() {
    service = new AuthorizationServiceImpl(authorizationRepository, idGenerator);
  }

  @Test
  public void willRegisterUserInTheRepository() {

    context.checking(new Expectations() {
      {
        oneOf(authorizationRepository).isNotRegister(authorizationType, email);
        will(returnValue(true));
        oneOf(authorizationRepository).register(authorizationType, email, password);
      }
    });

    service.register(authorizationType, email, password);
  }

  @Test(expected = EmailAlreadyExistsException.class)
  public void willNotRegisterWithEmailThatAlreadyExistsInTheRepository() {

    context.checking(new Expectations() {
      {
        oneOf(authorizationRepository).isNotRegister(authorizationType, email);
        will(returnValue(false));

      }
    });

    service.register(authorizationType, email, password);
  }

  @Test
  public void loginWithUnregisteredEmailWillReturnEmptyId() {

    context.checking(new Expectations() {
      {
        oneOf(authorizationRepository).isNotRegister(authorizationType, email);
        will(returnValue(true));
      }
    });

    String id = service.login(authorizationType, email, password);
    assertThat(id, is(equalTo("")));
  }

  @Test
  public void loginWithWrongPasswordWillReturnEmptyId() {

    context.checking(new Expectations() {
      {
        oneOf(authorizationRepository).isNotRegister(authorizationType, email);
        will(returnValue(false));
        oneOf(authorizationRepository).verifyUserPassword(authorizationType, email, password);
        will(returnValue(false));
      }
    });

    String id = service.login(authorizationType, email, password);
    assertThat(id, is(equalTo("")));
  }

  @Test
  public void willVerifyLogin() {
    context.checking(new Expectations() {
      {
        oneOf(authorizationRepository).isNotRegister(authorizationType, email);
        will(returnValue(false));
        oneOf(authorizationRepository).verifyUserPassword(authorizationType, email, password);
        will(returnValue(true));
        oneOf(idGenerator).generateId();
        will(returnValue(generatedId));
        oneOf(authorizationRepository).saveAsLogged(email, authorizationType, generatedId);
      }
    });

    String id = service.login(authorizationType, email, password);
    assertThat(id, is(equalTo(generatedId)));
  }


}
