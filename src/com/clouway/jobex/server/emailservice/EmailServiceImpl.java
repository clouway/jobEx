package com.clouway.jobex.server.emailservice;


import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EmailServiceImpl implements EmailService {

  private JobRepository jobRepository;
  private EmailSender emailSender;

  public EmailServiceImpl(JobRepository jobRepository, EmailSender emailSender) {
    this.jobRepository = jobRepository;
    this.emailSender = emailSender;
  }

  public void sendEmailApproval(Long jobId, String email) {

    Job job = jobRepository.getJob(jobId);

    emailSender.sendEmail(job, email);
  }
}
