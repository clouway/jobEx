package com.clouway.jobex.client.applyingforjob;

import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobApplicationProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserCVsPresenter extends AbstractActivity implements ApplyForJobEventHandler {

  private JobExRequestFactory requestFactory;

  private final UserCVsView view;

  private final UsernameProvider provider;

  private Long jobId;

  @Inject
  public UserCVsPresenter(JobExRequestFactory requestFactory, UserCVsView view, UsernameProvider provider) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.provider = provider;
  }

  /**
   * Apply A Cv for job. i.e Send a job application with the Job id which the user wants to apply for and the CV id
   * which the user wants to apply with.
   *
   * @param jobId
   * @param cvId             the id of the Cv with witch the user wants to apply for a job
   * @param employeeUsername : the username of person who applies for the JOB.
   */
  public void applyForJob(Long jobId, Long cvId, String employeeUsername) {

    JobExRequestFactory.JobApplicationRequestContext requestContext = requestFactory.jobApplicationContext();

    JobApplicationProxy applicationProxy = requestContext.create(JobApplicationProxy.class);

    applicationProxy.setCvId(cvId);

    applicationProxy.setJobId(jobId);

    applicationProxy.setUser(employeeUsername);

    requestContext.applyForJob(applicationProxy).fire(new Receiver<List<String>>() {
      @Override
      public void onFailure(ServerFailure error) {
        GWT.log(error.getExceptionType());
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

  /**
   * Handles ApplyForJobEvent.
   *
   * @param event the event to be handled
   */
  @Override
  public void onApplyForJob(ApplyForJobEvent event) {
    view.setJobId(event.getJobId());
    fetchCreatedCVs();
  }

  private void fetchCreatedCVs() {
    JobExRequestFactory.CVsRequestContext requestContext = requestFactory.cvsRequestContext();
    requestContext.fetchCreatedCVs(provider.getUsername()).fire(new Receiver<List<CVProxy>>() {
      @Override
      public void onSuccess(List<CVProxy> response) {
        if (response.size() == 0) {
          view.goToCreateNewCVForm();
        } else {
          view.showCreatedCVs(response);
        }
      }

    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    fetchCreatedCVs();
    panel.setWidget(view);
  }
}
