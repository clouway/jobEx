package com.clouway.jobex.client;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.creatingnewcv.view.CreatingNewCVWorkflowViewImpl;
import com.clouway.jobex.client.security.UsernameProvider;
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

    UsernameProvider usernameProvider = new UsernameProvider() {
      String username = "abc";

      @Override
      public String getUsername() {
        return username;
      }

      @Override
      public void setUsername(String username) {
        this.username = username;
      }
    };

    CreatingNewCVWorkflow creatingNewCVWorkflow = new CreatingNewCVWorkflow(view, factory,usernameProvider);

    view.setWorkFlow(creatingNewCVWorkflow);

    RootPanel.get().add(view);

  }
}

