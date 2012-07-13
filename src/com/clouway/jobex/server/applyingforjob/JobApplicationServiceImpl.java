package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.shared.entities.JobApplication;

/**
 * An Implementation of JobApplicationService
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationServiceImpl implements JobApplicationService {

  private final JobApplicationRepository jobApplicationRepository;

  public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {

    this.jobApplicationRepository = jobApplicationRepository;

  }


  @Override
  public void applyForJob(JobApplication application) {
    if (jobApplicationRepository.getJobApplication(application.getCvId(), application.getJobId()) == null) {
      jobApplicationRepository.saveJobApplication(application);
    }
  }



}
