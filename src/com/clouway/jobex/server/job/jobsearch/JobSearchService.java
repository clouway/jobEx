package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchService {

  /**
   * Search for jobs with given job parameters
   * @param job the object from which the criteria for searching are taken
   * @return list of the jobs according to the criteria given
   */
  List<Job> search(Job job);
}
