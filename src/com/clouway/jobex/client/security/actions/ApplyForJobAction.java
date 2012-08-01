package com.clouway.jobex.client.security.actions;

import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.shared.JobApplicationProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
* @author Adelin Ghanayem adelin.ghanaem@clouway.com
*/
public class ApplyForJobAction implements SecuredAction {
  private final JobExRequestFactory.JobApplicationRequestContext requestContext;
  private final JobApplicationProxy applicationProxy;
  private UserCVsView view;

  public ApplyForJobAction(UserCVsView view, JobExRequestFactory.JobApplicationRequestContext requestContext, JobApplicationProxy applicationProxy) {
    this.view = view;
    this.requestContext = requestContext;
    this.applicationProxy = applicationProxy;
  }

  @Override
  public void execute() {

    requestContext.applyForJob(applicationProxy).fire(new Receiver<List<String>>() {
      @Override
      public void onFailure(ServerFailure error) {
        view.notifyUserOfCommunicationError();
      }

      @Override
      public void onSuccess(List<String> response) {
        if (response != null && response.size() > 0) {
          view.showErrors(response);
        } else {
          view.notifyUserOfSuccessfulAppliance();
        }
      }
    });
  }

  @Override
  public void onConditionsBreak() {

  }

}
