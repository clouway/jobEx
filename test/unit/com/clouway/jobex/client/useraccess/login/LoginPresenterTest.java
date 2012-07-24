package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.job.jobsearch.RequestFactoryHelper;
import com.clouway.jobex.client.security.SecurityProvider;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.never;
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
  private SecurityProvider securityProvider;

  private AuthorizationService authorizationService;
  private String email = "test@email.com";
  private String password = "password";
  private String loginType = "User";

  @Before
  public void setUp() {
    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    authorizationService = RequestFactoryHelper.getService(AuthorizationService.class);

    presenter = new LoginPresenter(factory, loginView, securityProvider);
  }

  
  @Test
  public void loginButtonIsDisabledOnClick() {

    presenter.onLoginButtonClicked();

    verify(loginView).disableLoginButton();

  }

  @Test
  public void authorisesUserWhenCredentialsIsValid() {

    String id = "generatedId";

    when(authorizationService.login(loginType, email, password)).thenReturn(id);

    presenter.login(loginType, email, password);

    verify(securityProvider).setCredentials(id, email, loginType);

    verify(loginView).goToSearchPlace();

    verify(loginView).enableLoginButton();

  }

  @Test
  public void notifiesUserWhenAuthorizationCredentialsAreInValid() {

    when(authorizationService.login(loginType, email, password)).thenReturn("");

    presenter.login(loginType, email, password);

    verify(loginView).unsuccessfulLoginMessage();

    verify(loginView).enableLoginButton();

    verify(securityProvider,never()).setCredentials("", email, loginType);

  }

}
