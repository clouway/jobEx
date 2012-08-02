package com.clouway.jobex.client.useraccess.register;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationPresenter extends AbstractActivity {


  private JobExRequestFactory factory;
  private RegistrationView view;

  @Inject
  public RegistrationPresenter(JobExRequestFactory factory, RegistrationView view) {
    this.factory = factory;
    this.view = view;
  }


  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    panel.setWidget((RegistrationViewImpl) view);
  }

  public void register(String registrationType, String email, String password) {

    view.disableRegisterButton();

    JobExRequestFactory.AuthorizationRequestContext authorizationRequestContext = factory.authorizationContext();

    authorizationRequestContext.register(registrationType, email, password).to(new Receiver<Void>() {

      @Override
      public void onFailure(ServerFailure error) {
        view.registrationErrorMessage();
        view.enableRegisterButton();
      }

      @Override
      public void onSuccess(Void response) {
        view.successfulRegistrationMessage();
        view.enableRegisterButton();
      }
    }).fire();
  }

}
