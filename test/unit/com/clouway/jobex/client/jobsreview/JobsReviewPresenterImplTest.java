package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.jobsreview.JobsReviewService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobsReviewPresenterImplTest {

  private JobsReviewPresenter jobsReviewPresenter;
  private JobExRequestFactory.JobsReviewContext jobsReviewContext;
  private JobsReviewService jobsReviewService;
  private JobsReviewReceiver jobsReviewReceiver;

  @Mock
  private JobsReviewView jobsReviewView;

  @Mock
  private CompanyNameProvider companyNameProvider;

  @Captor
  private ArgumentCaptor<String> companyNameCaptor;

  @Captor
  private ArgumentCaptor<List<JobProxy>> announcedJobsCaptor;

  private List<Job> announcedJobs;

  private final String companyName = "clouway";

  @Before
  public void setUp() {
    initMocks(this);

    JobExRequestFactory requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    jobsReviewContext = requestFactory.jobsReviewContext();

    jobsReviewReceiver = new JobsReviewReceiver(jobsReviewView);

    jobsReviewService = RequestFactoryHelper.getService(JobsReviewService.class);

    jobsReviewPresenter = new JobsReviewPresenterImpl(requestFactory, jobsReviewView, companyNameProvider);

    announcedJobs = new ArrayList<Job>();
  }

  @Test
  public void getAnnouncedJobsForGivenCompany() {

    announcedJobs.add(new Job());

    when(jobsReviewService.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(announcedJobs);

    jobsReviewPresenter.getAnnouncedJobsForCompany(companyName);

    verify(jobsReviewService).getAnnouncedJobsForCompany(companyNameCaptor.capture());
    verify(jobsReviewView, never()).noAnnouncedJobs();
    verify(jobsReviewView).showAnnouncedJobs(announcedJobsCaptor.capture());

    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }

  @Test
  public void noAnnouncedJobsAreMadeByTheCompany() {

    when(jobsReviewService.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(announcedJobs);

    jobsReviewPresenter.getAnnouncedJobsForCompany(companyName);

    verify(jobsReviewView).noAnnouncedJobs();
    verify(jobsReviewView, never()).showAnnouncedJobs(announcedJobsCaptor.capture());
  }
}
