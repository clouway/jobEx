package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.UserAuthorizedEvent;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.UserCredentials;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
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
  private UserCredentialsLocalStorage securityProvider;

  @Mock
  private EventBus bus;

  private AuthorizationService authorizationService;

  private String email = "test@email.com";

  private String password = "password";

  private String loginType = "User";

  @Captor
  ArgumentCaptor<UserAuthorizedEvent> authorizedEventCaptor;

  @Before
  public void setUp() {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    authorizationService = RequestFactoryHelper.getService(AuthorizationService.class);

    presenter = new LoginPresenter(factory, loginView, bus);

  }


  @Test
  public void returnsSecurityTokenUserWhenCredentialsAreValid() {

    String sid = "sid";

    List<String> permittedActions = new ArrayList<String>();

    UserCredentials userCredentials = new UserCredentials(sid,email, permittedActions);

    when(authorizationService.login(loginType, email, password)).thenReturn(userCredentials);

    presenter.login(loginType, email, password);

    verify(authorizationService).login(loginType, email, password);

    verify(bus).fireEvent(authorizedEventCaptor.capture());

    UserAuthorizedEvent event = authorizedEventCaptor.getValue();

    assertThat(event, is(notNullValue()));

    assertThat(event.getSID(), is(equalTo(sid)));

    assertThat(event.getEmail(), is(equalTo(email)));

    verify(loginView).goToWhereCameFrom();

    verify(loginView).enableLoginButton();


  }

  @Test
  public void notifiesUserWhenAuthorizationCredentialsAreInvalid() {

    when(authorizationService.login(loginType, email, password)).thenReturn(null);

    presenter.login(loginType, email, password);

    verify(loginView).notifyIncorrectUsernameOrPassword();

    verify(loginView).enableLoginButton();

  }

}
