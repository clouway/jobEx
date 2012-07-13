package com.clouway.jobex.client.jobannounce;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.jobannounce.view.JobAnnounceView;
import com.clouway.jobex.server.domain.Job;
import com.clouway.jobex.server.jobannounce.JobAnnounceService;
import com.clouway.jobex.shared.JobProxy;
import com.clouway.jobex.shared.JobRequestContext;
import com.clouway.jobex.shared.JobexRequestFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImplTest {

  private JobAnnouncePresenterImpl presenter;
  private JobRequestContext requestContext;
  private JobAnnounceService service;
  private Receiver<Void> receiver;

  @Mock
  private JobAnnounceView view;

  @Mock
  private CompanyNameProvider companyNameProvider;

  @Before
  public void setUp() {

    initMocks(this);

    JobexRequestFactory requestFactory = RequestFactoryHelper.create(JobexRequestFactory.class);

    requestContext = requestFactory.jobRequest();

    receiver = new JobAnnounceReceiver(view);

    service = RequestFactoryHelper.getService(JobAnnounceService.class);

    presenter = new JobAnnouncePresenterImpl(requestContext, view, companyNameProvider);
  }

  @Test
  public void announceJob() {

    JobProxy jobProxy = requestContext.create(JobProxy.class);

    presenter.createRequest(jobProxy, receiver);
    presenter.announceJob();

    ArgumentCaptor<Job> jobCaptor = ArgumentCaptor.forClass(Job.class);
    ArgumentCaptor<String> companyNameCaptor = ArgumentCaptor.forClass(String.class);

    verify(service).announceJob(companyNameCaptor.capture(), jobCaptor.capture());
    verify(view).goToMainPlace();

    Job job = jobCaptor.getValue();

    assertThat(jobProxy.getCompany(), is(equalTo(job.getCompany())));
    assertThat(jobProxy.getPosition(), is(equalTo(job.getPosition())));
    assertThat(jobProxy.getCategory(), is(equalTo(job.getCategory())));
  }

  @Test
  public void getJobRequestContext() {

    assertNotNull(presenter.getJobRequestContext());
  }

  @Test
  public void getJobProxy() {

    assertNotNull(presenter.getJobProxy());
  }
}
