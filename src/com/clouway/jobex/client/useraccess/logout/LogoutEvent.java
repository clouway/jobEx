package com.clouway.jobex.client.useraccess.logout;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LogoutEvent extends GwtEvent<LogoutEventHandler>{

  public static Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();
  private String loggedEmail;


  public LogoutEvent(String loggedEmail){
    this.loggedEmail = loggedEmail;
  }
  
  @Override
  public Type<LogoutEventHandler> getAssociatedType() {
    return TYPE;
  }

  public String getLoggedEmail() {
    return loggedEmail;
  }

  @Override
  protected void dispatch(LogoutEventHandler handler) {
    handler.onLogout(this);
  }
}
