package com.clouway.jobex.server.emailservice;

import com.clouway.jobex.server.job.Job;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EmailSender {

  /**
   * Send email to user
   *
   * @param job - job
   * @param email - user's email
   */
  void sendEmail(Job job, String email);
}
