package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImplTest {

  private JobAnnouncePresenter presenter;

  @Mock
  private JobAnnounceView view;

  @Mock
  private UserCredentialsLocalStorage companyNameProvider;

  @Mock
  private JobRepository repository;

  private JobAnnounceService service;

  @Captor
  ArgumentCaptor<JobExRequestFactory.JobRequestContext> requestContextCaptor;

  @Captor
  ArgumentCaptor<JobProxy> jobProxyCaptor;

  @Captor
  ArgumentCaptor<String> companyNameCaptor;

  @Captor
  ArgumentCaptor<Job> jobCaptor;

  @Before
  public void setUp() {

    initMocks(this);

    JobExRequestFactory factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(JobAnnounceService.class);

    presenter = new JobAnnouncePresenterImpl(factory, view, companyNameProvider);
  }

  @Test
  public void prepareNewJob() {

    when(service.prepareNewJob()).thenReturn(new Job());

    presenter.prepareJob();

    verify(service).prepareNewJob();
    verify(view).edit(requestContextCaptor.capture(), jobProxyCaptor.capture());
    verify(companyNameProvider).getUsername();
  }

  @Test
  public void announceJob() {

    Job job = new Job();

    when(service.prepareNewJob()).thenReturn(job);

    job.setCategory("category");
    job.setCompany("company");
    job.setLocation("location");
    job.setPosition("position");
    job.setExpirationDate(new Date());

    presenter.prepareJob();
    presenter.announceJob();

    verify(service).announceJob(companyNameCaptor.capture(), jobCaptor.capture());
    verify(view).reset();
    verify(view).goToReviewJobsPlace();
  }
}