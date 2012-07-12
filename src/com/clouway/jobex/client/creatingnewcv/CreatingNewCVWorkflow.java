package com.clouway.jobex.client.creatingnewcv;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.creatingnewcv.view.CreatingNewCVWorkflowView;
import com.clouway.jobex.shared.proxies.CVProxy;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflow {


  private CreatingNewCVWorkflowView view;

  private final JobExRequestFactory factory;

  private JobExRequestFactory.CVsRequestContext context;

  public CreatingNewCVWorkflow(CreatingNewCVWorkflowView view, JobExRequestFactory factory) {

    this.view = view;

    this.factory = factory;

  }

  public void initialize() {

    context = factory.cvsRequestContext();

    CVProxy proxy = context.create(CVProxy.class);

    context.create(proxy).to(new CreatingNewCvReceiver(view));

    CVProxy mutableProxy = context.edit(proxy);

    view.initializeEditorWithRequestFactory(factory);

    view.edit(context, mutableProxy);

  }


  public void create() {

   view.flush().fire();

  }
}
