package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.google.inject.Inject;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

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
   * Announce new job for given company
   *
   * @param companyName - name of company
   * @param job - announced job
   * @return - list of eventually occurred errors
   */
  public List<String> announceJob(String companyName, Job job) {

    List<String> listOfErrors = new ArrayList<String>();
    listOfErrors.add("Error while saving");
    return listOfErrors;
  }
}
