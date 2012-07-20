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

  private AuthorizationService service;
  
  private String email = "test@email.com";
  private String password = "password";
  private String authorizationType = "registrationTypeIsNotImportant";

  @Before
  public void setUp(){
    service = new AuthorizationServiceImpl(authorizationRepository);
  }

  @Test
  public void willRegisterUserInTheRepository(){

    context.checking(new Expectations(){{
    oneOf(authorizationRepository).isNotRegister(authorizationType,email);
      will(returnValue(true));
    oneOf(authorizationRepository).register(authorizationType,email,password);
    }
    });

    service.register(authorizationType,email,password);
  }

  @Test (expected = EmailAlreadyExistsException.class)
  public void willNotRegisterWithEmailThatAlreadyExistsInTheRepository(){

    context.checking(new Expectations(){{
      oneOf(authorizationRepository).isNotRegister(authorizationType,email);
      will(returnValue(false));

    }
    });
    
    service.register(authorizationType,email, password);
  }

  @Test
  public void loginWithUnregisteredEmailWillReturnFalse(){

    context.checking(new Expectations(){{
      oneOf(authorizationRepository).isNotRegister(authorizationType, email);
      will(returnValue(true));
    }
    });

    boolean isVerified = service.verifyLogin(authorizationType, email, password);
    assertThat(isVerified, is(equalTo(false)));
  }

  @Test 
  public void loginWithWrongPassword(){

    context.checking(new Expectations(){{
      oneOf(authorizationRepository).isNotRegister(authorizationType, email);
      will(returnValue(false));
      oneOf(authorizationRepository).verifyUserPassword(authorizationType, email, password);
      will(returnValue(false));
    }
    });

    boolean isVerified = service.verifyLogin(authorizationType, email, password);
    assertThat(isVerified, is(equalTo(false)));
  }

  @Test
  public void willVerifyLogin(){
    context.checking(new Expectations(){{
    oneOf(authorizationRepository).isNotRegister(authorizationType,email);
      will(returnValue(false));
    oneOf(authorizationRepository).verifyUserPassword(authorizationType, email, password);
      will(returnValue(true));
    }
    });

    boolean isVerified = service.verifyLogin(authorizationType, email, password);
    assertThat(isVerified, is(equalTo(true)));
  }


}
