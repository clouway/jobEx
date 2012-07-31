package com.clouway.jobex.client.job.jobannounce;


/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnouncePresenter {

  /**
   * Prepare a new Job with empty properties and auto-generated id.
   */
  void prepareJob();

  /**
   * Announce the prepared Job.
   */
  void announceJob();

}
