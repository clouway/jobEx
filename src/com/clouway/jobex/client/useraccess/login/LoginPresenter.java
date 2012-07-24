package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.security.AuthorizationActivity;
import com.clouway.jobex.client.security.SecurityProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenter extends AuthorizationActivity {

  private LoginView loginView;
  private final SecurityProvider securityProvider;


  private JobExRequestFactory factory;

  @Inject
  public LoginPresenter(JobExRequestFactory factory, LoginView loginView, SecurityProvider securityProvider) {
    this.factory = factory;
    this.loginView = loginView;
    this.securityProvider = securityProvider;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    loginView.setPresenter(this);
    panel.setWidget((LoginViewImpl) loginView);
  }

  public void onLoginButtonClicked() {
    loginView.disableLoginButton();
    login(loginView.getLoginType(), loginView.getEmail(), loginView.getPassword());
  }

  public void login(final String loginType, final String email, String password) {
    JobExRequestFactory.AuthorizationContext authorizationContext = factory.authorizationContext();

    authorizationContext.login(loginType, email, password).fire(new Receiver<String>() {
      @Override
      public void onSuccess(String response) {
        if ("".equals(response)) {
          loginView.unsuccessfulLoginMessage();
        } else {

          securityProvider.setCredentials(response, email,loginType);

          loginView.goToSearchPlace();
        }
        loginView.enableLoginButton();
      }
    });
  }
}
