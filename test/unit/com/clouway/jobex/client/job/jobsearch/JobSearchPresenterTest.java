package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.event.shared.EventBus;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
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


  private JobExRequestFactory factory;
  private EventBus eventBus;
  
  private JobSearchServiceImpl service;

  private JobSearchPresenter presenter;

  @Mock
  JobSearchView jobSearchView;

  Mockery context = new JUnit4Mockery();
  @Before
  public void setUp(){
    factory = RequestFactoryHelper.create(JobExRequestFactory.class);
    jobSearchView = context.mock(JobSearchView.class);
    presenter = new JobSearchPresenter(factory, jobSearchView);
  }

  @Test
  public void showJobAdsOnSuccess(){


    context.checking(new Expectations(){{
    oneOf(jobSearchView).disableSearch();
    oneOf(jobSearchView).enableSearch();
    oneOf(jobSearchView).showJobAds(new ArrayList<JobProxy>());
    }
    });

    presenter.search("loc1","cat1");

  }

}
