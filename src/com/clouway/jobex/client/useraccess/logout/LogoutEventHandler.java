package com.clouway.jobex.client.useraccess.logout;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface LogoutEventHandler extends EventHandler{

  void onLogout(LogoutEvent event);
}
