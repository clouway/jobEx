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

  private ReviewJobsPresenter reviewJobsPresenter;
  private JobExRequestFactory.JobsReviewContext jobsReviewContext;
  private JobsReviewService jobsReviewService;
  private ReviewJobsReceiver reviewJobsReceiver;

  @Mock
  private ReviewJobsView reviewJobsView;

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

    reviewJobsReceiver = new ReviewJobsReceiver(reviewJobsView);

    jobsReviewService = RequestFactoryHelper.getService(JobsReviewService.class);

    reviewJobsPresenter = new ReviewJobsPresenterImpl(requestFactory, reviewJobsView, companyNameProvider);

    announcedJobs = new ArrayList<Job>();
  }

  @Test
  public void showAnnouncedJobsForGivenCompany() {

    announcedJobs.add(new Job());

    when(jobsReviewService.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(announcedJobs);

    reviewJobsPresenter.reviewAnnouncedJobs(companyName);

    verify(jobsReviewService).getAnnouncedJobsForCompany(companyNameCaptor.capture());
    verify(reviewJobsView, never()).showNoAnnouncedJobsNotification();
    verify(reviewJobsView).showAnnouncedJobs(announcedJobsCaptor.capture());

    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }

  @Test
  public void showNotificationWhenCompanyHasNoAnnouncedJobs() {

    when(jobsReviewService.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(announcedJobs);

    reviewJobsPresenter.reviewAnnouncedJobs(companyName);

    verify(reviewJobsView).showNoAnnouncedJobsNotification();
    verify(reviewJobsView, never()).showAnnouncedJobs(announcedJobsCaptor.capture());
  }

  @Test
  public void deleteAnnouncedJobByGivenId() {

    Long jobId = 1l;
    String companyName = "clouway";
    List<Job> announcedJobs = new ArrayList<Job>();

    when(jobsReviewService.deleteAnnouncedJob(jobId, companyName)).thenReturn(announcedJobs);

    reviewJobsPresenter.deleteAnnouncedJob(jobId, companyName);

    ArgumentCaptor<Long> jobIdCaptor = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<String> companyNameCaptor = ArgumentCaptor.forClass(String.class);

    verify(jobsReviewService).deleteAnnouncedJob(jobIdCaptor.capture(), companyNameCaptor.capture());
    verify(reviewJobsView).updateAnnounceJobs(announcedJobsCaptor.capture());

    assertThat(jobId, is(equalTo(jobIdCaptor.getValue())));
    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }
}
