package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImplTest {

  private JobAnnouncePresenterImpl presenter;
  private JobExRequestFactory.JobRequestContext requestContext;
  private JobAnnounceService service;
  private Receiver<Void> receiver;

  @Mock
  private JobAnnounceView view;

  @Mock
  private CompanyNameProvider companyNameProvider;

  @Before
  public void setUp() {

    initMocks(this);

    JobExRequestFactory requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    requestContext = requestFactory.jobRequestContext();

    receiver = new JobAnnounceReceiver(view);

    service = RequestFactoryHelper.getService(JobAnnounceService.class);

    presenter = new JobAnnouncePresenterImpl(requestFactory, view, companyNameProvider);
  }

  @Test
  public void announceJob() {

    JobProxy jobProxy = requestContext.create(JobProxy.class);

    presenter.initialize();

    presenter.announceJob();

    ArgumentCaptor<Job> jobCaptor = ArgumentCaptor.forClass(Job.class);
    ArgumentCaptor<String> companyNameCaptor = ArgumentCaptor.forClass(String.class);

    verify(service).announceJob(companyNameCaptor.capture(), jobCaptor.capture());
    verify(view).goToSearchPlace();

    Job job = jobCaptor.getValue();

    assertThat(jobProxy.getCompany(), is(equalTo(job.getCompany())));
    assertThat(jobProxy.getPosition(), is(equalTo(job.getPosition())));
    assertThat(jobProxy.getCategory(), is(equalTo(job.getCategory())));
  }
}
