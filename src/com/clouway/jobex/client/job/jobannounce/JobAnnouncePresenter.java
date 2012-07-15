package com.clouway.jobex.client.job.jobannounce;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnouncePresenter {

  /**
   * The method is used to create JobProxy object and after that sets the RequestContext
   * which will be fired with given Receiver<Void>
   */
  void initialize();

  /**
   * Announce new Job, i.e. fires RequestContext
   */
  void announceJob();
}
