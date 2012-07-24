package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPresenter;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.reviewjobs.ReviewJobsReceiver;
import com.clouway.jobex.client.reviewjobs.ReviewJobsView;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.reviewjobs.JobsReviewService;
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

  private ReviewJobsPresenter presenter;
  private JobsReviewService service;
  private ReviewJobsReceiver receiver;

  @Mock
  private ReviewJobsView view;

  @Mock
  private UserCredentialsLocalStorage companyNameProvider;

  @Captor
  private ArgumentCaptor<String> companyNameCaptor;

  @Captor
  private ArgumentCaptor<List<JobProxy>> announcedJobsCaptor;

  @Captor
  private ArgumentCaptor<Long> jobIdCaptor;

  private List<Job> listOfAnnouncedJobs;

  private final String companyName = "clouway";

  @Before
  public void setUp() {
    initMocks(this);

    JobExRequestFactory requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    receiver = new ReviewJobsReceiver(view);

    service = RequestFactoryHelper.getService(JobsReviewService.class);

    presenter = new ReviewJobsPresenterImpl(requestFactory, view, companyNameProvider);

    listOfAnnouncedJobs = new ArrayList<Job>();
  }

  @Test
  public void showAnnouncedJobsForGivenCompany() {

    listOfAnnouncedJobs.add(new Job());

    when(companyNameProvider.getUsername()).thenReturn(companyName);
    when(service.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(listOfAnnouncedJobs);

    presenter.reviewAnnouncedJobs();

    verify(service).getAnnouncedJobsForCompany(companyNameCaptor.capture());
    verify(view, never()).showNoAnnouncedJobsNotification();
    verify(service).getAnnouncedJobsForCompany(companyName);
    verify(view).showAnnouncedJobs(announcedJobsCaptor.capture());

    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }

  @Test
  public void showNotificationWhenCompanyHasNoAnnouncedJobs() {

    when(service.getAnnouncedJobsForCompany(companyNameCaptor.capture())).thenReturn(listOfAnnouncedJobs);

    presenter.reviewAnnouncedJobs();

    verify(view).showNoAnnouncedJobsNotification();
    verify(view, never()).showAnnouncedJobs(announcedJobsCaptor.capture());
  }

  @Test
  public void deleteAnnouncedJobByGivenId() {

    Long jobId = 1l;

    when(service.deleteAnnouncedJob(jobIdCaptor.capture(), companyNameCaptor.capture())).thenReturn(listOfAnnouncedJobs);

    presenter.deleteAnnouncedJob(jobId, companyName);

    verify(service).deleteAnnouncedJob(jobIdCaptor.capture(), companyNameCaptor.capture());
    verify(view).updateAnnounceJobs(announcedJobsCaptor.capture());

    assertThat(jobId, is(equalTo(jobIdCaptor.getValue())));
    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }


}
