package com.clouway.jobex.client;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.applyingforjob.ApplyForJobEvent;
import com.clouway.jobex.client.applyingforjob.JobApplicationPresenter;
import com.clouway.jobex.client.applyingforjob.view.JobApplicationViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
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

    JobExRequestFactory factory = GWT.create(JobExRequestFactory.class);

    factory.initialize(eventBus);

    final JobApplicationViewImpl view = new JobApplicationViewImpl();

    final JobExRequestFactory.JobApplicationRequestContext jobApplicationContext = factory.jobApplicationContext();

    JobApplicationPresenter presenter = new JobApplicationPresenter(factory, view);

    view.setPresenter(presenter);

    presenter.onApplyForJob(new ApplyForJobEvent(2l));

    RootPanel.get().add(view);

  }
}

