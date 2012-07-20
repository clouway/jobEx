package com.clouway.jobex.client.useraccess.login;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface LoginView {
  void setPresenter(LoginPresenter loginPresenter);

  void disableLoginButton();

  void enableLoginButton();

  void unsuccessfulLoginMessage();

  String getEmail();

  String getPassword();

  String getLoginType();
  
  void goToSearchPlace();
}
