package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.shared.actions.FetchJobsAction;
import com.clouway.jobex.shared.entities.Job;
import com.clouway.jobex.shared.responses.FetchJobsResponse;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.clouway.jobex.client.jobsreview.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobReviewPresenterTest {


  @Mock
  ActionDispatcherServiceAsync service;
  @Mock
  private JobReviewView view;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void dispatchesFetchJobsAction() {

    JobReviewPresenter jobReviewPresenter = new JobReviewPresenter(service,view);

    FetchJobsAction action = new FetchJobsAction();

    ArrayList<Job> returnedJobs = new ArrayList<Job>();

    FetchJobsResponse response = new FetchJobsResponse(returnedJobs);

    doOnSuccess(new Throwable()).when(service).dispatch(isA(FetchJobsAction.class), isA(AsyncCallback.class));

    jobReviewPresenter.fetchJobs();

    verify(service).dispatch(isA(FetchJobsAction.class), isA(AsyncCallback.class));

    verify(view).showFetchedJobs(returnedJobs);

  }






}
