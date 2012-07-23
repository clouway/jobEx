package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.job.jobsearch.RequestFactoryHelper;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenterTest {

  private JobExRequestFactory factory;

  private LoginPresenter presenter;

  @Mock
  private LoginView loginView;

  @Mock
  private UsernameProvider usernameProvider;

  @Mock
  private CompanyNameProvider companyNameProvider;

  private AuthorizationService authorizationService;
  private String email = "test@email.com";
  private String password = "password";
  private String loginType = "typeIsNotImportentInThisTest";

  @Before
  public void setUp(){
    initMocks(this);
    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    authorizationService = RequestFactoryHelper.getService(AuthorizationService.class);
    presenter = new LoginPresenter(factory, loginView, usernameProvider, companyNameProvider);
  }

  @Test
  public void loginButtonIsDisabledOnClick(){
    presenter.onLoginButtonClicked();

    verify(loginView).disableLoginButton();

  }

  @Test
  public void willNotLoginWithUnregisteredEmail(){

    when(authorizationService.verifyLogin(loginType, email, password)).thenReturn(false);

    presenter.verifyLogin(loginType, email, password);

    verify(loginView).unsuccessfulLoginMessage();
    verify(loginView).enableLoginButton();
  }

  @Test
  public void willLoginSuccessfullyWithCorrectDataAsUser(){

    when(authorizationService.verifyLogin("User", email, password)).thenReturn(true);

    presenter.verifyLogin("User", email, password);

    verify(usernameProvider).setUsername(email);
    verify(loginView).goToSearchPlace();
    verify(loginView).enableLoginButton();
  }

  @Test
  public void willLoginSuccessfullyWithCorrectDataAsCompany(){

    when(authorizationService.verifyLogin("Company", email, password)).thenReturn(true);

    presenter.verifyLogin("Company", email, password);

    verify(companyNameProvider).setCompanyName(email);
    verify(loginView).goToSearchPlace();
    verify(loginView).enableLoginButton();
  }
}
