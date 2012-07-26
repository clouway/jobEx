package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobProxy;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnouncePresenter {

  /**
   * The method is used to add JobProxy object and after that sets the RequestContext
   * which will be fired with given Receiver<Void>
   */
  void initialize();

  void announceJob(JobProxy jobProxy);

  JobProxy createProxy();
}
