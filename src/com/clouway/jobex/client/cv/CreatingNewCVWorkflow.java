package com.clouway.jobex.client.cv;


import com.clouway.jobex.client.security.Condition;
import com.clouway.jobex.client.security.ConditionalActionDispatcher;
import com.clouway.jobex.client.security.ConditionalActionDispatcherImpl;
import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.client.security.SecuredActionMapperImpl;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.client.security.UserPermittedActionsImpl;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflow extends AbstractActivity {


  private class CreateCVAction implements SecuredAction {
    @Override
    public void execute() {

    }

    @Override
    public void onConditionsBreak() {

    }
  }

  private class IsUserPermitted implements Condition {


    private final Class<SecuredAction> securedActionClass;

    private final UserPermittedActionsImpl userPermittedActions;

    public IsUserPermitted(Class<SecuredAction> securedActionClass, UserPermittedActionsImpl userPermittedActions) {

      this.securedActionClass = securedActionClass;
      this.userPermittedActions = userPermittedActions;
    }

    @Override
    public boolean isTrue() {
      return userPermittedActions.isPermitted(securedActionClass);
    }
  }

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

  public void initialize() {

    context = factory.cvsRequestContext();

    CVProxy proxy = context.create(CVProxy.class);

    context.add(provider.getUsername(), proxy).to(new CreatingNewCvReceiver(view));

    CVProxy mutableProxy = context.edit(proxy);

    view.initializeEditorWithRequestFactory(factory);

    view.edit(context, mutableProxy);

  }

  public void create() {
    ConditionalActionDispatcher dispatcher = new ConditionalActionDispatcherImpl(null, null, null, null);
    dispatcher.dispatch(new SecuredAction() {
      @Override
      public void execute() {
        view.flush();

        context.fire();
      }

      @Override
      public void onConditionsBreak() {
        Window.alert("Ebi si maikata ... !");
      }
    }, new IsUserConfirmed("Iska6 li da se ebe6 ?"), new IsUserPermitted(SecuredAction.class, new UserPermittedActionsImpl(new SecuredActionMapperImpl(null))));


  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setWorkFlow(this);
    panel.setWidget((CreatingNewCVWorkflowViewImpl) view);
    initialize();
  }

  private static class IsUserConfirmed implements Condition {

    private final String s;

    public IsUserConfirmed(String s) {

      this.s = s;
    }

    @Override
    public boolean isTrue() {
      return Window.confirm(s);
    }
  }

}
