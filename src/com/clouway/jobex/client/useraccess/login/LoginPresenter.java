package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.security.AuthorizationActivity;
import com.clouway.jobex.client.security.TokenProxy;
import com.clouway.jobex.client.security.UserAuthorizedEvent;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPresenter extends AuthorizationActivity {


  private LoginView view;

  private EventBus eventBus;

  private JobExRequestFactory factory;


  @Inject
  public LoginPresenter(JobExRequestFactory factory, LoginView view, EventBus eventBus) {
    this.factory = factory;
    this.view = view;
    this.eventBus = eventBus;
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    panel.setWidget((LoginViewImpl) view);
  }

  public void onLoginButtonClicked() {
    view.disableLoginButton();
    login(view.getLoginType(), view.getEmail(), view.getPassword());
  }

  public void login(final String loginType, final String email, final String password) {

    JobExRequestFactory.AuthorizationRequestContext authorizationRequestContext = factory.authorizationContext();


    authorizationRequestContext.login(loginType, email, password).fire(new Receiver<TokenProxy>() {
      @Override
      public void onSuccess(TokenProxy response) {
        if (response != null) {
          eventBus.fireEvent(new UserAuthorizedEvent(response.getSid(), response.getEmail(), response.getPermittedActions()));
          view.goToWhereCameFrom();
        } else {
          view.notifyIncorrectUsernameOrPassword();
        }
        view.enableLoginButton();
      }
    });
  }


  @Override
  public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
    view.setPresenter(this);
    panel.setWidget((IsWidget) view);
  }
}
