package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchServiceImpl implements JobSearchService {

  //  @Inject(optional = true)
  private JobRepository jobRepository;

  //
//  public JobSearchServiceImpl(){
//
//  }
//  @Inject
  public JobSearchServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  /**
   * Search in the DatastoreService for jobs with given job parameters
   *
   * @param job the object from which the criteria for searching are taken
   * @return list of the jobs according to the criteria given
   */
  public List<Job> search(Job job) {

    List<Job> jobList = null;


    String location = job.getLocation();

    String category = job.getCategory();

    if(("").equals(location) && !("").equals(category)){
      jobList = jobRepository.getAllJobsByCategory(category);
    }
    if(!("").equals(location) && ("").equals(category)) {
      jobList = jobRepository.getAllJobsByLocation(location);
    }
    if(!("").equals(location) && !("").equals(category)){
      jobList = jobRepository.getAllJobsByLocationAndCategory(location, category);
    }

    return jobList;

//    return jobRepository.getAllJobsByLocationAndCategory(job.getLocation(), job.getCategory());

  }
}