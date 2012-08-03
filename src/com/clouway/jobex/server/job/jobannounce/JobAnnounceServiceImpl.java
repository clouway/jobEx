package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceServiceImpl implements JobAnnounceService {

  private final JobRepository repository;

  @Inject
  public JobAnnounceServiceImpl(JobRepository repository) {
    this.repository = repository;
  }

  /**
   * Prepare a new Job with empty properties and auto-generated id
   *
   * @return - a Job
   */
  public Job prepareNewJob() {

    return repository.prepareNewJob();
  }

  /**
   * Announce new job
   *
   * @param companyName - the name of the company that announced the job
   * @param job - the job that will be announced
   */
  public void announceJob(String companyName, Job job) {
    repository.saveJob(companyName, job);
  }
}
