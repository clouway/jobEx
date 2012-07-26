package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImplTest {

  @Mock
  private JobAnnounceView view;

  @Mock
  private UserCredentialsLocalStorage companyNameProvider;

  @Captor
  private ArgumentCaptor<String> companyNameCaptor;

  @Captor
  private ArgumentCaptor<Job> jobCaptor;

  private JobAnnounceService service;
  private JobExRequestFactory requestFactory;
  private JobAnnouncePresenter presenter;
  private Receiver<Void> receiver;

  private final String companyName = "company";

  @Before
  public void setUp() {

    initMocks(this);

    requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);
    service = RequestFactoryHelper.getService(JobAnnounceService.class);
    presenter = new JobAnnouncePresenterImpl(requestFactory, view, companyNameProvider);
    receiver = new JobAnnounceReceiver(view);
  }

  @Test
  public void jobIsAnnouncedAfterConfirmation() {

    when(companyNameProvider.getUsername()).thenReturn(companyName);


    when(companyNameProvider.getUsername()).thenReturn(companyName);

    presenter.prepareJob();

    presenter.announceJob();

    verify(companyNameProvider).getUsername();
    verify(service).announceJob(companyNameCaptor.capture(), jobCaptor.capture());
    verify(view).goToReviewJobsPlace();

    verify(service).announceJob(companyNameCaptor.capture(), jobCaptor.capture());
    verify(view).goToReviewJobsPlace();

    assertThat(companyName, is(equalTo(companyNameCaptor.getValue())));
  }

}
