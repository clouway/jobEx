package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.client.cv.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * An Implementation of JobApplicationService
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationServiceImpl implements JobApplicationService {

  private final JobApplicationRepository jobApplicationRepository;

  private final ErrorMessages errorMessages;

  public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ErrorMessages errorMessages) {

    this.jobApplicationRepository = jobApplicationRepository;

    this.errorMessages = errorMessages;
  }


  @Override
  public List<String> applyForJob(JobApplication application) {

    ArrayList<String> errors = new ArrayList<String>();

    if (jobApplicationRepository.getJobApplication(application.getCvId(), application.getJobId()) == null) {
      jobApplicationRepository.saveJobApplication(application);
    } else {
      errors.add(errorMessages.jobApplicationIsPreviouslySubmitted());
    }
    return errors;
  }

}
