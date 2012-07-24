package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenter extends AbstractActivity {

  private LoginView loginView;
  private UsernameProvider usernameProvider;
  private CompanyNameProvider companyNameProvider;
  private JobExRequestFactory factory;

  @Inject
  public LoginPresenter(JobExRequestFactory factory, LoginView loginView, UsernameProvider usernameProvider, CompanyNameProvider companyNameProvider) {
    this.factory = factory;
    this.loginView = loginView;
    this.usernameProvider = usernameProvider;
    this.companyNameProvider = companyNameProvider;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    loginView.setPresenter(this);
    panel.setWidget((LoginViewImpl) loginView);
  }

  public void onLoginButtonClicked() {
    loginView.disableLoginButton();
    verifyLogin(loginView.getLoginType(), loginView.getEmail(), loginView.getPassword());
  }

  public void verifyLogin(final String loginType, final String email, String password) {
    JobExRequestFactory.AuthorizationContext authorizationContext = factory.authorizationContext();

    authorizationContext.login(loginType, email, password).fire(new Receiver<String>() {
      @Override
      public void onSuccess(String response) {
        if ("".equals(response)) {
          loginView.unsuccessfulLoginMessage();
        } else {
          //provider.setName(email);
          //provider.setLoginType(loginType);
          //provider.setId(response);

          loginView.goToSearchPlace();
        }
        loginView.enableLoginButton();
      }
    });

  }
}
