package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobRepository {


  /**
   * Get list of job objects by location
   * @param location a job location
   * @return list with all jobs that have the current location parameter
   */
  List<Entity> getAllJobsByLocation(String location);


  /**
   * Get list of job objects by category
   * @param category a job category
   * @return list with all jobs that have the current category parameter
   */
  List<Entity> getAllJobsByCategory(String category);

  /**
   * Get list of job objects by location and category
   * @param location a job location
   * @param category a job category
   * @return list with all jobs that have the current location and category parameters
   */
  List<Entity> getAllJobsByLocationAndCategory(String location, String category);

  /**
   * Save announced job for given company
   *
   * @param companyName - company announcing the job
   * @param job - a job
   */
  void saveJob(String companyName, Job job);

  /**
   * Get announced jobs for given company
   *
   * @param companyName - company name
   * @return - list of announced jobs
   */
  List<Entity> getAnnouncedJobsForCompany(String companyName);


  Key[] getExpiredJobsKeys();

  /**
   * Remove all job ads that are no longer valid
   */
  void removeExpiredJobs();

  /**
   * Get Job by given jobId
   *
   * @param jobId - jobId
   * @return - a Job
   */
  Job getJob(Long jobId);

}
