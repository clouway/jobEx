package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.client.job.jobsearch.RequestFactoryHelper;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class JobsReviewReceiverTest {

  private Mockery context = new JUnit4Mockery();

  private JobsReviewView jobsReviewView = context.mock(JobsReviewView.class);

  private JobsReviewReceiver jobsReviewReceiver;

  private JobExRequestFactory requestFactory;

  private JobExRequestFactory.JobsReviewContext reviewContext;

  @Before
  public void setUp() {
    jobsReviewReceiver = new JobsReviewReceiver(jobsReviewView);

    requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    reviewContext = requestFactory.jobsReviewContext();
  }

  @Test
  public void announcedJobsAreShownAfterExecutionOfRequest() {

    final List<JobProxy> listOfAnnouncedJobs = new ArrayList<JobProxy>();
    listOfAnnouncedJobs.add(reviewContext.create(JobProxy.class));

    context.checking(new Expectations() {{
      oneOf(jobsReviewView).showAnnouncedJobs(listOfAnnouncedJobs);
    }});

    jobsReviewReceiver.onSuccess(listOfAnnouncedJobs);
  }

  @Test
  public void whenCompanyHasNotAnnouncedJobsShowMessage() {

    final List<JobProxy> listOfAnnouncedJobs = new ArrayList<JobProxy>();

    context.checking(new Expectations() {{
      oneOf(jobsReviewView).noAnnouncedJobs();
    }});

    jobsReviewReceiver.onSuccess(listOfAnnouncedJobs);
  }
}
