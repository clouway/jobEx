package com.clouway.jobex.client.creatingnewcv;


import com.clouway.jobex.client.security.UsernameProvider;

import com.clouway.jobex.shared.CVProxy;

import com.clouway.jobex.shared.JobExRequestFactory;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflow {


  private CreatingNewCVWorkflowView view;

  private final JobExRequestFactory factory;

  private final UsernameProvider provider;

  private JobExRequestFactory.CVsRequestContext context;

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
}
