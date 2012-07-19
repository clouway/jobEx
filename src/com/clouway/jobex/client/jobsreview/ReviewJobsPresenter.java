package com.clouway.jobex.client.jobsreview;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ReviewJobsPresenter {

  /**
   * Review announced jobs for given company
   *
   * @param companyName - the name of the company
   */
  void reviewAnnouncedJobs(String companyName);
}
