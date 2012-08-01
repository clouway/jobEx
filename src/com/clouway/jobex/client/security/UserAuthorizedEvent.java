package com.clouway.jobex.client.security;

import com.google.gwt.event.shared.GwtEvent;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthorizedEvent extends GwtEvent<UserAuthorizedEventHandler> {

  public static Type<UserAuthorizedEventHandler> TYPE = new Type<UserAuthorizedEventHandler>();

  private String sid;


  private final List<String> permittedPlaces;

  private String email;


  public UserAuthorizedEvent(String sid, String email, List<String> permittedPlaces) {
    this.sid = sid;
    this.email = email;
    this.permittedPlaces = permittedPlaces;
  }

  public Type<UserAuthorizedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserAuthorizedEventHandler handler) {
    handler.onUserAuthorized(this);
  }


  public String getEmail() {
    return email;
  }

  public String getSID() {
    return sid;
  }

  public List<String> getPermittedActions() {
    return permittedPlaces;
  }
}
