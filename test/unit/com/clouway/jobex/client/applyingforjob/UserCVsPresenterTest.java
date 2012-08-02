package com.clouway.jobex.client.applyingforjob;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.applyingforjob.JobApplication;
import com.clouway.jobex.server.applyingforjob.JobApplicationService;
import com.clouway.jobex.server.cv.CV;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserCVsPresenterTest {


  private JobExRequestFactory factory;

  private JobApplicationService jobApplicationService;

  private CvsService cvsService;

  private UserCVsPresenter presenter;

  private JobExRequestFactory.JobApplicationRequestContext context;

  private String username = "user";

  @Mock
  UserCVsView view;

  @Mock
  UserCredentialsLocalStorage provider;

  @Captor
  ArgumentCaptor<ArrayList<CVProxy>> returnedCVArgumentCaptures;

  @Captor
  ArgumentCaptor<List<CVProxy>> captor;


  @Before
  public void setUp() throws Exception {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    jobApplicationService = RequestFactoryHelper.getService(JobApplicationService.class);

    cvsService = RequestFactoryHelper.getService(CvsService.class);

    context = factory.jobApplicationContext();

    presenter = new UserCVsPresenter(factory, view, provider);

  }


  @Test
  public void applyForJobWithSelectedCV() {



    presenter.applyForJob(1l, 2l, "username");

    ArgumentCaptor<JobApplication> jobApplicationArgumentCaptor = ArgumentCaptor.forClass(JobApplication.class);

    verify(jobApplicationService).applyForJob(jobApplicationArgumentCaptor.capture());

    JobApplication jobApplication = jobApplicationArgumentCaptor.getValue();

    assertThat(jobApplication.getCvId(), is(equalTo(2l)));

    assertThat(jobApplication.getJobId(), is(equalTo(1l)));

    verify(view).notifyUserOfSuccessfulAppliance();

  }


  @Test
  public void notifiesUserOfSystemError() {

    doThrow(new RuntimeException()).when(jobApplicationService).applyForJob(isA(JobApplication.class));

    presenter.applyForJob(1l, 2l, "username");

    ArgumentCaptor<JobApplication> jobApplicationArgumentCaptor = ArgumentCaptor.forClass(JobApplication.class);

    verify(jobApplicationService).applyForJob(jobApplicationArgumentCaptor.capture());

    JobApplication jobApplication = jobApplicationArgumentCaptor.getValue();

    assertThat(jobApplication.getCvId(), is(equalTo(2l)));

    assertThat(jobApplication.getJobId(), is(equalTo(1l)));

    verify(view, never()).notifyUserOfSuccessfulAppliance();

    verify(view).notifyUserOfCommunicationError();

  }


  @Test
  public void listsAllCreatedCVsOnApplyForJob() {

    Long jobId = 1l;

    ArrayList<CV> cvs = new ArrayList<CV>();

    cvs.add(new CV());

    when(cvsService.fetchCreatedCVs(username)).thenReturn(cvs);

    when(provider.getUsername()).thenReturn(username);

    presenter.fetchCreatedCVs();

    verify(cvsService).fetchCreatedCVs(username);

    verify(view).showCVs(returnedCVArgumentCaptures.capture());

    assertThat(returnedCVArgumentCaptures.getValue(), is(notNullValue()));

    assertThat(returnedCVArgumentCaptures.getValue().size(), is(equalTo(1)));


  }


  @Test
  public void redirectsUserToCreatingNewCVFormIfReturnedCVListIsEmpty() {

    ArrayList<CV> cvs = new ArrayList<CV>();

    when(cvsService.fetchCreatedCVs(username)).thenReturn(cvs);

    when(provider.getUsername()).thenReturn(username);

    presenter.fetchCreatedCVs();

    verify(cvsService).fetchCreatedCVs(username);

    verify(view).goToCreateNewCVForm();

  }

  @Test
  public void notifiesUserWhenJobApplicationIsPreviouslySubmitted() {

    Long jobId = 1l;

    Long cvId = 1l;

    final String error = "some Error";

    ArrayList<String> errors = new ArrayList<String>();

    errors.add(error);

    when(jobApplicationService.applyForJob(isA(JobApplication.class))).thenReturn(errors);

    presenter.applyForJob(jobId, cvId, "username");

    verify(view, never()).notifyUserOfSuccessfulAppliance();

    verify(view).showErrors(errors);

  }

  @Test
  public void deletesUserCv() {

    Long cvId = 1l;

    when(provider.getUsername()).thenReturn("username");

    List<CV> cvList = new ArrayList<CV>();

    cvList.add(new CV());

    when(cvsService.delete("username", cvId)).thenReturn(cvList);

    presenter.deleteCv(cvId);

    verify(cvsService).delete("username", cvId);

    verify(view).showCVs(captor.capture());

    List<CVProxy> cvProxies = captor.getValue();

    assertThat(cvProxies, is(notNullValue()));

    assertThat(cvProxies.size(), is(equalTo(1)));

  }


}
