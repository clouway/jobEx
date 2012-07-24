package com.clouway.jobex.client.submittedcvs;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface SubmittedCVsPresenter {

  /**
   * Review submitted CVs for a job
   *
   * @param jobId - jobId
   */
  void reviewSubmittedCVs(Long jobId);

  /**
   * Send email approval to given user's email
   *
   * @param jobId - jobId
   * @param email - user's email
   */
  void sendEmailApproval(Long jobId, String email);
}
