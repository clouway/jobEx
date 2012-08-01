package com.clouway.jobex.client.security;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserAuthorizationEventHandler extends EventHandler {
  void onUserAuthorization(UserAuthorizationEvent event);
}
