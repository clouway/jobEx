package com.clouway.jobex.client.jobapplication;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.jobapplication.view.JobApplicationView;
import com.clouway.jobex.shared.proxies.CVProxy;
import com.clouway.jobex.shared.proxies.JobApplicationProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationPresenter implements ApplyForJobEventHandler {

  private JobExRequestFactory requestFactory;

  private final JobApplicationView view;


  public JobApplicationPresenter(JobExRequestFactory requestContext, JobApplicationView view) {
    this.requestFactory = requestContext;
    this.view = view;
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
    JobExRequestFactory.JobApplicationRequestContext requestContext = requestFactory.jobApplicationContext();
    requestContext.fetchCreatedCVs().fire(new Receiver<List<CVProxy>>() {
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
