package com.clouway.jobex.client;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.creatingnewcv.view.CreatingNewCVWorkflowViewImpl;
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

    CreatingNewCVWorkflowViewImpl view = new CreatingNewCVWorkflowViewImpl();

    CreatingNewCVWorkflow creatingNewCVWorkflow = new CreatingNewCVWorkflow(view, factory);

    view.setWorkFlow(creatingNewCVWorkflow);

    RootPanel.get().add(view);

  }
}

