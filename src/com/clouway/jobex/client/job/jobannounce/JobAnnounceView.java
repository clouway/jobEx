package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceView {

  /**
   * Set presenter, used to drive the view
   *
   * @param presenter a presenter
   */
  void setPresenter(JobAnnouncePresenter presenter);

  /**
   * Go to JobsReviewPlace after announcing new job
   */
  void goToReviewJobsPlace();


  public void edit(JobExRequestFactory.JobRequestContext context, JobProxy proxy);

  /**
   * Show occurred errors when announcing new job
   *
   * @param listOfErrors - list of occurred errors
   */
  void showOccurredErrors(List<String> listOfErrors);
}
