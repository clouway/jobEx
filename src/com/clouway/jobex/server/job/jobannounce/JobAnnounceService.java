package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceService {

  /**
   * Prepare a new Job with empty properties and auto-generated id
   *
   * @return - a Job
   */
  Job prepareNewJob();

  /**
   * Announce new job
   *
   * @param companyName - the name of the company that announced the job
   * @param job - the job that will be announced
   */
  void announceJob(String companyName, Job job);
}
