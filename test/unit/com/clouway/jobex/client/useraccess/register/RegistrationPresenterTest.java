package com.clouway.jobex.client.useraccess.register;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationPresenterTest {

  private JobExRequestFactory factory;

  private RegistrationPresenter presenter;

  @Mock
  private RegistrationView registrationView;

  private AuthorizationService authorizationService;
  private String email = "test@email.com";
  private String password = "password";

  private String registrationType = "typeIsNotImportantInThisTest";

  @Before
  public void setUp(){
    initMocks(this);
    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    authorizationService = RequestFactoryHelper.getService(AuthorizationService.class);
    presenter = new RegistrationPresenter(factory, registrationView);
  }


  @Test
  public void registrationButtonIsDisabledOnClick(){
    presenter.register(registrationType, email, password);

    verify(registrationView).disableRegisterButton();

  }


  @Test
  public void willRegisterUserOnSuccess() {


    presenter.register(registrationType, email, password);
    verify(registrationView).successfulRegistrationMessage();
    verify(registrationView).enableRegisterButton();
  }

  @Test
  public void willNotRegisterUserIfExceptionOccurs() {

    doThrow(new RuntimeException()).when(authorizationService).register(registrationType, email, password);

    presenter.register(registrationType, email, password);
    verify(registrationView).registrationErrorMessage();
    verify(registrationView).enableRegisterButton();
  }
}
