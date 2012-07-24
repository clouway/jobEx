package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.confirmation.Confirmation;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceView extends Confirmation {

  /**
   * Set presenter, used to drive the view
   *
   * @param presenter a presenter
   */
  void setPresenter(JobAnnouncePresenter presenter);

  /**
   * Go to SearchPlace after announcing new job
   */
  void goToSearchPlace();


  public void edit(JobExRequestFactory.JobRequestContext context, JobProxy proxy);
}
