package com.clouway.jobex.server.jobsreview;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.jobsearch.DomainObjectConverter;
import com.google.appengine.api.datastore.Entity;

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

  public List<Job> getAnnouncedJobsForCompany(String companyName) {

    List<Entity> announcedJobs;

    announcedJobs = repository.getAnnouncedJobsForCompany(companyName);

    return converter.convertToDomainsFrom(announcedJobs);
  }
}
