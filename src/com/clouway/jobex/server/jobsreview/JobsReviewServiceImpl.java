package com.clouway.jobex.server.jobsreview;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.jobsearch.DomainObjectConverter;
import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobsReviewServiceImpl implements JobsReviewService {

  private JobRepository repository;

  private DomainObjectConverter<Job> converter;

  public JobsReviewServiceImpl(JobRepository repository, DomainObjectConverter<Job> converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * Get list of announced jobs for given company
   *
   * @param companyName - company name
   * @return
   */
  public List<Job> getAnnouncedJobsForCompany(String companyName) {

    List<Entity> announcedJobs;

    announcedJobs = repository.getAnnouncedJobsForCompany(companyName);

    return converter.convertToDomainsFrom(announcedJobs);
  }

  /**
   * Delete announced job with given jobId
   *
   * @param jobId - jobId
   * @return
   */
  public List<Job> deleteAnnouncedJob(Long jobId, String companyName) {

    repository.deleteJob(jobId);

    List<Entity> entityList = repository.getAnnouncedJobsForCompany(companyName);

    List<Job> returnedJobList = new ArrayList<Job>();

    for (Entity entity : entityList) {
      returnedJobList.add(
              new Job(entity.getKey().getId(),
                      entity.getProperty("company").toString(),
                      entity.getProperty("position").toString(),
                      entity.getProperty("category").toString(),
                      entity.getProperty("location").toString(),
                      (Date) entity.getProperty("expirationDate")));
    }

    return returnedJobList;
  }
}
