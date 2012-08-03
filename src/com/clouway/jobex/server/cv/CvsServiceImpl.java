package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.applyingforjob.JobApplication;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvsServiceImpl implements CvsService {


  private final CVRepository repository;

  /**
   * Creates New CVServiceImpl
   *
   * @param repository: Cv Repository
   */
  public CvsServiceImpl(CVRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<CV> fetchCreatedCVs(String username) {
    return repository.getCreatedCVs(username);
  }

  @Override
  public void add(String username, CV cv) {
    repository.save(username, cv);
  }

  @Override
  public CV fetchCreatedCv(String username, Long cvId) {
    List<CV> cvs = repository.getCreatedCVs(username);
    for (CV cv : cvs) {
      if (cv.getId().equals(cvId)) {
        return cv;
      }
    }
    return null;
  }

  @Override
  public List<CV> delete(String username, long cvId) {
    repository.delete(cvId);
    return repository.getCreatedCVs(username);
  }

  /**
   * Get list of submitted CVs for given job
   *
   * @param jobId - a jobId
   * @return - list of submitted CVs
   */
  public List<CV> getSubmittedCVs(Long jobId) {

    List<JobApplication> jobApplications = repository.getJobApplications(jobId);

    return repository.getSubmittedCVs(jobId, jobApplications);
  }

  /**
   * Prepare new CV with empty properties and auto-generated id.
   *
   * @return - a CV
   */
  public CV prepareNewCV() {

    return repository.prepareNewCV();
  }
}
