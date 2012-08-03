package com.clouway.jobex.client.cv;


import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflow extends AbstractActivity {

  private CreatingNewCVWorkflowView view;

  private final JobExRequestFactory factory;

  private final UserCredentialsLocalStorage provider;

  private JobExRequestFactory.CVsRequestContext context;


  @Inject
  public CreatingNewCVWorkflow(CreatingNewCVWorkflowView view, JobExRequestFactory factory, UserCredentialsLocalStorage provider) {

    this.view = view;

    this.factory = factory;

    this.provider = provider;

  }

  public void createCV() {

    view.flush();

    context.fire();
  }

  public void prepareCV() {

    factory.cvsRequestContext().prepareNewCV().fire(new Receiver<CVProxy>() {

      public void onSuccess(CVProxy response) {

        context = factory.cvsRequestContext();
        CVProxy editableProxy = context.edit(response);

        view.edit(context, editableProxy);

        context.add(provider.getUsername(), editableProxy).to(new CreatingNewCvReceiver(view));
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    prepareCV();

    view.setWorkFlow(this);
    view.reset();

    panel.setWidget((IsWidget) view);
  }

}
