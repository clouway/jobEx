package com.clouway.jobex.client.jobsearch;

<<<<<<< HEAD
import com.clouway.jobex.client.JobProxy;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
=======
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.clouway.jobex.server.jobsearch.JobSearchServiceImpl;
>>>>>>> merged the 1st with the 3th users stories and fixed conflicts
import com.clouway.jobex.shared.entities.Job;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RunWith(JMock.class)
public class JobSearchPresenterTest {

<<<<<<< HEAD
=======
//  Mockery context = new JUnit4Mockery();
//
//  JobSearchView jobSearchView = context.mock(JobSearchView.class);
//  JobSearchServiceAsync rpcService = context.mock(JobSearchServiceAsync.class);
//  InstanceMatcher<AsyncCallback<List<Job>>> jobListInstanceMatcher = new InstanceMatcher<AsyncCallback<List<Job>>>();
//
//  JobSearchPresenter jobSearchPresenter;
//
//  @Before
//  public void setUp(){
//    jobSearchPresenter = new JobSearchPresenter(rpcService,jobSearchView);
////    jobSearchPresenter.setPresenterToTheView();
//  }
//
//  @Test
//  public void willShowAllJobsFromTheSearchCriteria(){
//
//    final String location = "Varna";
//    final String category = "";
//
//    context.checking(new Expectations(){{
//      oneOf(rpcService).search(with(location), with(category), with(jobListInstanceMatcher));
//      oneOf(jobSearchView).fillTableWithJobAds(null);
//    }});
//
//
//    jobSearchPresenter.search(location,category);
//    jobListInstanceMatcher.getInstance().onSuccess(null);
//  }


//      Version 2
//====================================================================================================================================
//  class JobPresenter {
//    private final JobSearchView view;
//    private final JobExRequestFactory factory;
//
//    public JobPresenter(JobSearchView view, JobExRequestFactory factory){
//      this.view = view;
//      this.factory = factory;
//    }
//
//    void onSearchJob(){
//      JobExRequestFactory.JobRequest searchJobRequest = factory.jobRequest();
//      JobProxy jobProxy = searchJobRequest.create(JobProxy.class);
//
//      jobProxy.setId(25l);
//      jobProxy.setCategory(view.getCategoryValue());
//      jobProxy.setLocation(view.getLocationValue());
//
//      searchJobRequest.search(jobProxy).to(new Receiver<List<JobProxy>>(){
//
//        @Override
//        public void onFailure(ServerFailure error) {
//        }
//
//        @Override
//        public void onSuccess(List<JobProxy> response) {
//          view.fillTableWithJobAds(response);
//        }
//      }).fire();
//    }
//  }
//
//  private class MockJobSearchView implements JobSearchView{
//    public boolean jobWereFound = false;
//
//    @Override
//    public void fillTableWithJobAds(List<JobProxy> listOfJobObjects) {
//      jobWereFound = true;
//    }
//
//    @Override
//    public void setPresenter(Presenter presenter) {
//
//    }
//
//    @Override
//    public String getCategoryValue() {
//      return "cat1";
//    }
//
//    @Override
//    public String getLocationValue() {
//      return "loc1";
//    }
//  }
//
//  JobExRequestFactory factory = RequestFactorySource.create(JobExRequestFactory.class);
//  SimpleEventBus eventBus = new SimpleEventBus();
//
//  @Before
//  public void initialize(){
//    factory.initialize(eventBus, new InProcessRequestTransport(new SimpleRequestProcessor(ServiceLayer.create())));
//  }
//
//  @Test
//  public void fooThing(){
//
//    MockJobSearchView view = new MockJobSearchView();
//
//    JobPresenter jobPresenter =  new JobPresenter(view, factory);
//
//    jobPresenter.onSearchJob();
//
//    assertThat(view.jobWereFound, is(equalTo(true)));
//  }
>>>>>>> merged the 1st with the 3th users stories and fixed conflicts

  private JobExRequestFactory factory;
  private EventBus eventBus;
  
  private JobSearchServiceImpl service;

  private JobSearchPresenter presenter;

  @Mock
  JobSearchView jobSearchView;

  Mockery context = new JUnit4Mockery();
  @Before
  public void setUp(){
//    initMocks(this);
    factory = RequestFactoryHelper.create(JobExRequestFactory.class);
//    eventBus = new SimpleEventBus();
//    factory.initialize(eventBus, new InProcessRequestTransport(new SimpleRequestProcessor(ServiceLayer.create())));
    jobSearchView = context.mock(JobSearchView.class);
    service = RequestFactoryHelper.getService(JobSearchServiceImpl.class);
    presenter = new JobSearchPresenter(factory, jobSearchView);
  }

  @Test
  public void someKindOfTest(){


    context.checking(new Expectations(){{
    oneOf(jobSearchView).disableSearchButton();
    oneOf(jobSearchView).enableSearchButton();
    oneOf(jobSearchView).fillTableWithJobAds(new ArrayList<JobProxy>());
    }
    });

    presenter.search("loc1","cat1");

    ArgumentCaptor<Job> jobArgumentCaptor = ArgumentCaptor.forClass(Job.class);

//    service.search(jobArgumentCaptor.capture());
//    verify(jobSearchView).fillTableWithJobAds(null);
//    verify(service).search(jobArgumentCaptor.capture());

  }
}
