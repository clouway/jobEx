package com.clouway.jobex.server.jobsreview;

import com.clouway.jobex.server.job.Job;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobsReviewService {

  /**
   * Get list of announced jobs for given company
   *
   * @param companyName - company name
   * @return - list of announced jobs
   */
  List<Job> getAnnouncedJobsForCompany(String companyName);

  /**
   * Delete announced job with given jobId
   *
   * @param jobId - jobId
   * @return - list of announced jobs
   */
  List<Job> deleteAnnouncedJob(Long jobId, String companyName);
}
