package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.applyingforjob.JobApplication;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CVRepository {

  List<CV> getCreatedCVs(String username);

  void save(String username, CV cv);

  CV getCv(long cvId);


  void delete(long cvId);

  /**
   * Get submitted CVs for given jobId from JobApplications
   *
   * @param jobId - a jobId
   * @param jobApplications - list of jobApplications
   * @return - list of submitted CVs
   */
  List<CV> getSubmittedCVs(Long jobId, List<JobApplication> jobApplications);

  /**
   * Get all JobApplications with given jobId
   *
   * @param jobId - a jobId
   * @return - list of JobApplications
   */
  List<JobApplication> getJobApplications(Long jobId);

}
