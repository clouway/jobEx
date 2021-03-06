package com.clouway.jobex.server.cv;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CvsService {

  /**
   * Fetches All created CV by the specified user;
   *
   * @return A list of CV contains all created Cv by the user;
   */
  List<CV> fetchCreatedCVs(String username);

  void add(String username, CV cv);


  CV fetchCreatedCv(String username, Long cvId);

  public List<CV> delete(String username, long cvId);

  /**
   * Get list of submitted CVs for given job
   *
   * @param jobId - a jobId
   * @return - list of submitted CVs
   */
  List<CV> getSubmittedCVs(Long jobId);

  /**
   * Prepare new CV with empty properties and auto-generated id
   *
   * @return - a CV
   */
  CV prepareNewCV();
}
