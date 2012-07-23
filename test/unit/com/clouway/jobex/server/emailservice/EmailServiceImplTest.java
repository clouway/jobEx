package com.clouway.jobex.server.emailservice;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class EmailServiceImplTest {

  private Mockery context = new JUnit4Mockery();

  private JobRepository jobRepository = context.mock(JobRepository.class);

  private EmailSender emailSender = context.mock(EmailSender.class);

  private EmailService emailService;

  @Before
  public void setUp() {
    emailService = new EmailServiceImpl(jobRepository, emailSender);
  }

  @Test
  public void sendEmailApprovalToUsersEmail() {

    final Long jobId = 1l;
    final String email = "ivan@mail.com";

    final Job job = new Job();

    context.checking(new Expectations() {{
      oneOf(jobRepository).getJob(jobId);
      will(returnValue(job));

      oneOf(emailSender).sendEmail(job, email);
    }});

    emailService.sendEmailApproval(jobId, email);
  }
}
