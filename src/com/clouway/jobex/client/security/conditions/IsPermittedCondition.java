package com.clouway.jobex.client.security.conditions;

import com.clouway.jobex.client.security.Condition;
import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.client.security.UserPermittedActions;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class IsPermittedCondition implements Condition {

  private final Class<? extends SecuredAction> testActionClass;
  private final UserPermittedActions permittedActions;

  public IsPermittedCondition(Class<? extends SecuredAction> testActionClass, UserPermittedActions permittedActions) {

    this.testActionClass = testActionClass;
    this.permittedActions = permittedActions;
  }

  @Override
  public boolean isTrue() {
    return permittedActions.isPermitted(testActionClass);
  }
}
