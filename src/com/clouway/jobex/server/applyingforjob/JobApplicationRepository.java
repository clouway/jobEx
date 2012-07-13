package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.shared.entities.JobApplication;

/**
 * An Implementation of this interface is responsible for saving and Retrieving JobApplications
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobApplicationRepository {

  /**
   * Save JobApplication in the repository;
   *
   * @param jobApplication:the Job Application to be saved
   */
  void saveJobApplication(JobApplication jobApplication);

  /**
   * Returns A JobApplication that was applied for Job wih jobId and with Cv with cvId;
   *
   * @param cvId: the Id of the Cv that is user for job application;
   * @param jobId the Id of the Job to which the user applies.
   * @return Job Application, null if the Job Does not exist.
   */
  JobApplication getJobApplication(Long cvId, Long jobId);


}
