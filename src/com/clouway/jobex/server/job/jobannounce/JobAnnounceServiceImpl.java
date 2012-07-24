package com.clouway.jobex.server.job.jobannounce;


import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.google.inject.Inject;

import javax.validation.Validator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceServiceImpl implements JobAnnounceService {

  private final JobRepository repository;

  private final Validator validator;

  @Inject
  public JobAnnounceServiceImpl(JobRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  /**
   * Announce job with given companyName and Job
   *
   * @param companyName a companyName
   * @param job a job
   */
  public void announceJob(String companyName, Job job) {
    repository.saveJob(companyName, job);
  }
}
