package com.clouway.jobex.server.emailservice;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface EmailService {

  void sendEmailApproval(Long jobId, String email);
}
