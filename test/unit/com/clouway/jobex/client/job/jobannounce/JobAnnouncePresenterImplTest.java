package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImplTest {

  private JobAnnouncePresenterImpl presenter;
  private JobAnnounceService service;
  private Receiver<Void> receiver;

  @Mock
  private JobAnnounceView view;

  @Mock
  private CompanyNameProvider companyNameProvider;

  @Captor
  ArgumentCaptor<Job> jobCaptor;

  @Captor
  ArgumentCaptor<String> companyCaptor;

  @Before
  public void setUp() {

    initMocks(this);

    JobExRequestFactory requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    receiver = new JobAnnounceReceiver(view);

    service = RequestFactoryHelper.getService(JobAnnounceService.class);

    presenter = new JobAnnouncePresenterImpl(requestFactory, view, companyNameProvider);
  }

  @Test
  public void announceJobOnConfirmation() {

    String company = "Qwerty";

    when(view.isConfirmed()).thenReturn(true);
    when(companyNameProvider.getCompanyName()).thenReturn(company);

    presenter.initialize();
    presenter.announceJob();

    verify(view).isConfirmed();
    verify(companyNameProvider).getCompanyName();
    verify(service).announceJob(companyCaptor.capture(), jobCaptor.capture());
    verify(view).goToSearchPlace();
  }

  @Test
  public void jobCannotBeAnnounceWithoutConfirmation() {

    when(view.isConfirmed()).thenReturn(false);
    when(companyNameProvider.getCompanyName()).thenReturn("Qwerty");

    presenter.initialize();
    presenter.announceJob();

    verify(view).isConfirmed();
    verify(service, never()).announceJob(companyCaptor.capture(), jobCaptor.capture());
  }
}
