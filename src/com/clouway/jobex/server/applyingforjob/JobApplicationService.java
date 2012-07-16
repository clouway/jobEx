package com.clouway.jobex.server.applyingforjob;


import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobApplicationService {

  /**
   * Applies for a job with JobApplication  that contains
   * JobId And CvId
   *
   * @param application the job application to be applied with ;
   */
  List<String> applyForJob(JobApplication application);
}
