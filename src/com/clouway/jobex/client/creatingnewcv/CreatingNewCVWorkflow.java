package com.clouway.jobex.client.creatingnewcv;


import com.clouway.jobex.client.security.UsernameProvider;

import com.clouway.jobex.shared.CVProxy;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflow extends AbstractActivity {


  private CreatingNewCVWorkflowView view;

  private final JobExRequestFactory factory;

  private final UsernameProvider provider;

  private JobExRequestFactory.CVsRequestContext context;

  @Inject
  public CreatingNewCVWorkflow(CreatingNewCVWorkflowView view, JobExRequestFactory factory, UsernameProvider provider) {

    this.view = view;

    this.factory = factory;

    this.provider = provider;

  }

  public void initialize() {

    context = factory.cvsRequestContext();

    CVProxy proxy = context.create(CVProxy.class);

    context.add(provider.getUsername(), proxy).to(new CreatingNewCvReceiver(view));

    CVProxy mutableProxy = context.edit(proxy);

    view.initializeEditorWithRequestFactory(factory);

    view.edit(context, mutableProxy);

  }


  public void create() {

    view.flush();

    context.fire();

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setWorkFlow(this);
    panel.setWidget((CreatingNewCVWorkflowViewImpl) view);
    initialize();
  }
}
