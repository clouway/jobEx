package com.clouway.jobex.client.useraccess.logout;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LogoutEventHandlerImpl implements LogoutEventHandler{

  private JobExRequestFactory factory;

  @Inject
  public LogoutEventHandlerImpl(JobExRequestFactory factory){

    this.factory = factory;
  }

  @Override
  public void onLogout(LogoutEvent event) {
    JobExRequestFactory.AuthorizationContext logoutRequestContext = factory.authorizationContext();

    logoutRequestContext.logout(event.getLoggedEmail()).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        //TODO: clear the cookies and redirect page
      }
    }).fire();
  }
}
