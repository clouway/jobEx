package com.clouway.jobex.client.cv;

import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCvWorkflow extends AbstractActivity {

  private final JobExRequestFactory factory;

  private final EditCVWorkflowView view;

  private final UserCredentialsLocalStorage provider;

  @Inject
  public EditCvWorkflow(JobExRequestFactory factory, EditCVWorkflowView view, UserCredentialsLocalStorage provider) {
    this.factory = factory;
    this.view = view;
    this.provider = provider;
  }

  public void editCv(long cvId) {
    final JobExRequestFactory.CVsRequestContext context = factory.cvsRequestContext();
    context.fetchCreatedCv(provider.getUsername(), cvId).fire(new Receiver<CVProxy>() {
      @Override
      public void onSuccess(CVProxy response) {
        JobExRequestFactory.CVsRequestContext requestContext = factory.cvsRequestContext();
        view.edit(response, requestContext);
      }
    });

  }

  public void update(CVProxy proxy, JobExRequestFactory.CVsRequestContext context) {
    context.add(provider.getUsername(), proxy).fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        view.goToCvPreview();
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    panel.setWidget(view);
  }
}
