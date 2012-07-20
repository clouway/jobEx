package com.clouway.jobex.client.useraccess.register;

import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.job.jobsearch.JobSearchViewImpl;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationPresenter extends AbstractActivity{


  private JobExRequestFactory factory;
  private RegistrationView registrationView;

  @Inject
  public RegistrationPresenter(JobExRequestFactory factory, RegistrationView registrationView) {
    this.factory = factory;
    this.registrationView = registrationView;
  }


  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    registrationView.setPresenter(this);
    panel.setWidget((RegistrationViewImpl)registrationView);
  }

  public void register(String registrationType, String email, String password) {
    JobExRequestFactory.AuthorizationContext authorizationContext = factory.authorizationContext();


    authorizationContext.register(registrationType, email, password).to(new Receiver<Void>() {

      @Override
      public void onFailure(ServerFailure error) {
        registrationView.userExistsMessage();
        registrationView.enableRegisterButton();
      }

      @Override
      public void onSuccess(Void response) {
        registrationView.successfulRegistrationMessage();
        registrationView.enableRegisterButton();
      }
    }).fire();
  }

  public void onRegisterButtonClicked() {
    registrationView.disableRegisterButton();
    register(registrationView.getRegistrationType(), registrationView.getEmail(), registrationView.getPassword());
  }
}
