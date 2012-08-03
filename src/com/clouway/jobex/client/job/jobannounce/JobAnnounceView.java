package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceView {

  /**
   * Set presenter that will drive the view
   *
   * @param presenter a presenter
   */
  void setPresenter(JobAnnouncePresenter presenter);

  /**
   * Go to JobsReviewPlace
   */
  void goToReviewJobsPlace();

  /**
   * Edit (load the view's Driver) with given RequestContext and JobProxy
   *
   * @param context - RequestContext through which the proxy object will be sent to the server
   * @param proxy - the proxy that will be sent
   */
  public void edit(JobExRequestFactory.JobRequestContext context, JobProxy proxy);

  /**
   * Show occurred constraint violations when announcing new Job
   *
   * @param listOfConstraintViolations - list of constraint violations
   */
  void showConstraintViolations(List<String> listOfConstraintViolations);

  /**
   * Reset the view's widgets
   */
  void reset();
}
