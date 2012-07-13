package com.clouway.jobex.client.applyingforjob;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobApplicationProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationPresenter implements ApplyForJobEventHandler {

  private JobExRequestFactory requestFactory;

  private final JobApplicationView view;
  private final UsernameProvider provider;


  public JobApplicationPresenter(JobExRequestFactory requestFactory, JobApplicationView view,UsernameProvider provider) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.provider = provider;
  }

  /**
   * Apply A Cv for job. i.e Send a job application with the Job id which the user wants to apply for and the CV id
   * which the user wants to apply with.
   *
   * @param jobId the id of the job that the user wants to apply for
   *
   * @param cvId the id of the Cv with witch the user wants to apply for a job
   */
  public void applyForJob(Long jobId, Long cvId) {

    JobExRequestFactory.JobApplicationRequestContext requestContext = requestFactory.jobApplicationContext();

    JobApplicationProxy applicationProxy = requestContext.create(JobApplicationProxy.class);

    applicationProxy.setCvId(cvId);

    applicationProxy.setJobId(jobId);

    requestContext.applyForJob(applicationProxy).fire(new Receiver<Void>() {
      @Override
      public void onFailure(ServerFailure error) {
        view.notifyUserOfCommunicationError();
      }

      @Override
      public void onSuccess(Void response) {
        view.notifyUserOfSuccessfulAppliance();
      }
    });
  }

  /**
   * Handles ApplyForJobEvent.
   * @param event the event to be handled
   */
  @Override
  public void onApplyForJob(ApplyForJobEvent event) {
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
}
