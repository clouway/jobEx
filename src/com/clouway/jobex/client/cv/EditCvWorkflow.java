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

  private JobExRequestFactory.CVsRequestContext requestContext;

  @Inject
  public EditCvWorkflow(JobExRequestFactory factory, EditCVWorkflowView view, UserCredentialsLocalStorage provider) {
    this.factory = factory;
    this.view = view;
    this.provider = provider;
  }

  /**
   * Edit CV with given cvId
   *
   * @param cvId - cvId
   */
  public void editCv(long cvId) {

    final String username = provider.getUsername();

    factory.cvsRequestContext().fetchCreatedCv(username, cvId).fire(new Receiver<CVProxy>() {

      public void onSuccess(CVProxy response) {

        requestContext = factory.cvsRequestContext();
        CVProxy editableProxy = requestContext.edit(response);

        view.edit(editableProxy, requestContext);

        requestContext.add(username, editableProxy).to(new EditCVWorkflowReceiver(view));
      }
    });
  }

  /**
   * Save the edited CV
   */
  public void saveEditedCV() {

    requestContext.fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    view.setPresenter(this);
    panel.setWidget(view);
  }
}
