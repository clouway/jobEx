package com.clouway.jobex.client.security;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ConditionalActionDispatcherImpl implements ConditionalActionDispatcher {


  Map<Class<? extends SecuredAction>, List<Condition>> actionsConditionsMap;

  private final JobExRequestFactory factory;

  private UserCredentialsLocalStorage localStorage;

  private final EventBus eventBus;

  @Inject
  public ConditionalActionDispatcherImpl(Map<Class<? extends SecuredAction>, List<Condition>> actionsConditionsMap,
                                         JobExRequestFactory factory,
                                         UserCredentialsLocalStorage localStorage,
                                         EventBus eventBus) {

    this.actionsConditionsMap = actionsConditionsMap;

    this.factory = factory;

    this.localStorage = localStorage;

    this.eventBus = eventBus;

  }

  public void dispatch(final SecuredAction securedAction, Condition... conditions) {

    List<Condition> definedCondition = actionsConditionsMap.get(securedAction.getClass());
    if (definedCondition != null) {
      if (checkCondition(definedCondition)) {
        securedAction.onConditionsBreak();
        return;
      }
    }

    if (checkCondition(Arrays.asList(conditions))) {
      securedAction.onConditionsBreak();
      return;
    }

    securedAction.execute();

//    executeInSecureManner(securedAction);
  }

//  private void executeInSecureManner(final SecuredAction securedAction) {
//
//    String sid = localStorage.getSID();
//
//    if (sid != null && !sid.equals("")) {
//      JobExRequestFactory.AuthorizationRequestContext context = factory.authorizationContext();
//      context.isValid(sid).fire(new Receiver<Boolean>() {
//        @Override
//        public void onSuccess(Boolean response) {
//          if (response) {
//            securedAction.execute();
//          } else {
//            eventBus.fireEvent(new UserAuthorizationEvent());
//          }
//        }
//      });
//    } else {
//      eventBus.fireEvent(new UserAuthorizationEvent());
//    }
//  }

  private boolean checkCondition(List<Condition> definedCondition) {
    for (Condition condition : definedCondition) {
      if (!condition.isTrue()) {
        return true;
      }
    }
    return false;
  }

  public void addConditions(Class<? extends SecuredAction> testActionClass, Condition... conditions) {
    actionsConditionsMap.put(testActionClass, Arrays.asList(conditions));
  }
}
