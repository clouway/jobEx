package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchServiceImpl implements JobSearchService {

  private JobRepository jobRepository;
  private final DomainObjectConverter<Job> jobConverter;


  public JobSearchServiceImpl(JobRepository jobRepository, DomainObjectConverter<Job> jobConverter) {
    this.jobRepository = jobRepository;
    this.jobConverter = jobConverter;
  }

  /**
   * Search in the DatastoreService for jobs with given job parameters
   *
   * @param job the object from which the criteria for searching are taken
   * @return list of the jobs according to the criteria given
   */
  public List<Job> search(Job job) {

    List<Entity> jobList = null;


    String location = job.getLocation();

    String category = job.getCategory();


    if(("all locations").equals(location)) {
      jobList = jobRepository.getAllJobsByCategory(category);
    } else {
      jobList = jobRepository.getAllJobsByLocationAndCategory(location, category);
    }

    return jobConverter.convertToDomainsFrom(jobList);

  }
}