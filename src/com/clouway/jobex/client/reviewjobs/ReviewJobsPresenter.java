package com.clouway.jobex.client.reviewjobs;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ReviewJobsPresenter {

  /**
   * Review announced jobs for given company
   */
  void reviewAnnouncedJobs();

  /**
   * Delete announced job with given jobId
   *
   * @param jobId - jobId
   */
  void deleteAnnouncedJob(Long jobId, String companyName);
}
