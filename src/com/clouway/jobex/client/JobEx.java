package com.clouway.jobex.client;


import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflowView;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflowViewImpl;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class JobEx implements EntryPoint {

  /**
   * This is the entry point metho
   * d.
   */
  public void onModuleLoad() {


    EventBus eventBus = new SimpleEventBus();

    JobExRequestFactory requestFactory = GWT.create(JobExRequestFactory.class);

    requestFactory.initialize(eventBus);

    JobExRequestFactory.JobRequestContext requestContext = requestFactory.jobRequestContext();

    CreatingNewCVWorkflowView view = new CreatingNewCVWorkflowViewImpl();

    CreatingNewCVWorkflow creatingNewCVWorkflow = new CreatingNewCVWorkflow(view, requestFactory, new UsernameProvider() {
      @Override
      public String getUsername() {
        return "Username";
      }

      @Override
      public void setUsername(String username) {

      }
    });
    view.setWorkFlow(creatingNewCVWorkflow);
    RootPanel.get().add((IsWidget) view);

  }
}