package com.clouway.jobex.client.security;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Fired when unauthorized user attempts to do some secured action.
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthorizationEvent extends GwtEvent<UserAuthorizationEventHandler> {

  public static Type<UserAuthorizationEventHandler> TYPE = new Type<UserAuthorizationEventHandler>();

  public UserAuthorizationEvent() {

  }

  public Type<UserAuthorizationEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserAuthorizationEventHandler handler) {
    handler.onUserAuthorization(this);
  }
}
