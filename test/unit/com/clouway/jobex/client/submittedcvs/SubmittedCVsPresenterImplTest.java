package com.clouway.jobex.client.submittedcvs;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.server.cv.CV;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.server.emailservice.EmailService;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
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
public class SubmittedCVsPresenterImplTest {

  @Mock
  private SubmittedCVsView view;

  @Mock
  private CvsService service;

  @Mock
  private EmailService emailService;

  @Captor
  private ArgumentCaptor<List<CVProxy>> submittedCVsCaptor;

  @Captor
  private ArgumentCaptor<Long> jobIdCaptor;

  @Captor
  private ArgumentCaptor<String> emailCaptor;

  private SubmittedCVsPresenter presenter;

  private final Long jobId = 1l;

  private List<CV> submittedCVs;

  @Before
  public void setUp() {

    initMocks(this);

    JobExRequestFactory requestFactory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(CvsService.class);

    emailService = RequestFactoryHelper.getService(EmailService.class);

    presenter = new SubmittedCVsPresenterImpl(requestFactory, view);

    submittedCVs = new ArrayList<CV>();
  }

  @Test
  public void showSubmittedCVsForGivenJob() {

    submittedCVs.add(new CV());

    when(service.getSubmittedCVs(jobIdCaptor.capture())).thenReturn(submittedCVs);

    presenter.reviewSubmittedCVs(jobId);

    verify(service).getSubmittedCVs(jobIdCaptor.capture());
    verify(view).showSubmittedCVs(submittedCVsCaptor.capture());
    verify(view, never()).showNoSubmittedCVsNotification();

    assertThat(jobId, is(equalTo(jobIdCaptor.getValue())));
    assertThat(submittedCVsCaptor.getValue().size(), is(equalTo(1)));
  }

  @Test
  public void showNoSubmittedCVsNotification() {

    when(service.getSubmittedCVs(jobIdCaptor.capture())).thenReturn(submittedCVs);

    presenter.reviewSubmittedCVs(jobId);

    verify(service).getSubmittedCVs(jobId);
    verify(view).showNoSubmittedCVsNotification();
    verify(view, never()).showSubmittedCVs(submittedCVsCaptor.capture());
  }

  @Test
  public void showSentEmailNotificationAfterApprovingCV() {

    Long jobId = 1l;
    String email = "ivan@mail.com";

    presenter.sendEmailApproval(jobId, email);

    verify(emailService).sendEmailApproval(jobIdCaptor.capture(), emailCaptor.capture());
    verify(view).showSentEmailNotification();

    assertThat(jobId, is(equalTo(jobIdCaptor.getValue())));
    assertThat(email, is(equalTo(emailCaptor.getValue())));
  }

  @Test
  public void emailIsNotSendWhenConfirmationIsFalse() {

    Long jobId = 1l;
    String email = "ivan@mail.com";

    presenter.sendEmailApproval(jobId, email);

    verify(emailService, never()).sendEmailApproval(jobIdCaptor.capture(), emailCaptor.capture());
  }
}
