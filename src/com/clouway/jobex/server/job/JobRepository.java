package com.clouway.jobex.server.job;

import com.clouway.jobex.server.job.Job;
import com.google.appengine.api.datastore.Entity;

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
  List<Job> getAllJobsByLocation(String location);

  List<Job> convertToListOfJobObjects(List<Entity> listOfEntities);

  /**
   * Get list of job objects by category
   * @param category a job category
   * @return list with all jobs that have the current category parameter
   */
  List<Job> getAllJobsByCategory(String category);

  /**
   * Get list of job objects by location and category
   * @param location a job location
   * @param category a job category
   * @return list with all jobs that have the current location and category parameters
   */
  List<Job> getAllJobsByLocationAndCategory(String location, String category);

  void saveJob(String companyName, Job job);
}
