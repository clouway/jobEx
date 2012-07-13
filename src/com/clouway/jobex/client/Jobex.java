package com.clouway.jobex.client;


import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.clouway.jobex.client.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.jobsearch.JobSearchViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Jobex implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {



    JobExRequestFactory jobexRequestFactory = GWT.create(JobExRequestFactory.class);
    JobSearchViewImpl jobSearchView = new JobSearchViewImpl();
    JobSearchPresenter jobSearchPresenter = new JobSearchPresenter(jobexRequestFactory,jobSearchView);
    jobSearchPresenter.setPresenterToTheView();
    RootPanel.get().add(jobSearchView);
  }
}

