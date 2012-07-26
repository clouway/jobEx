package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceService {

  /**
   * Announce new job for given company
   *
   * @param companyName - name of company
   * @param job - announced job
   * @return - list of any occurred errors
   */
  List<String> announceJob(String companyName, Job job);
}
