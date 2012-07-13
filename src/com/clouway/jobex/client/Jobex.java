package com.clouway.jobex.client;

import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceView;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceViewImpl;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.client.security.CompanyNameProviderImpl;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Jobex implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    EventBus eventBus = new SimpleEventBus();

    JobExRequestFactory requestFactory = GWT.create(JobExRequestFactory.class);

    requestFactory.initialize(eventBus);

    JobExRequestFactory.JobRequestContext requestContext = requestFactory.jobRequestContext();

    JobAnnounceView view = new JobAnnounceViewImpl();

    CompanyNameProvider companyNameProvider = new CompanyNameProviderImpl();
    companyNameProvider.setCompanyName("company");

    JobAnnounceView.Presenter presenter = new JobAnnouncePresenterImpl(requestContext, view, companyNameProvider);

    RootPanel.get().add((Widget) view);
  }
}

