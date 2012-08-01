package com.clouway.jobex.shared;

import java.util.List;

/**
 * An DTO used to transfer the user credentials used on client side
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserCredentials {

  private String sid;

  private String email;


  private List<String> permittedActions;


  public UserCredentials() {

  }

  public UserCredentials(String id, String email, List<String> permittedPlaces) {

    this.sid = id;

    this.email = email;

    this.permittedActions = permittedPlaces;

  }

  public String getEmail() {
    return email;
  }

  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getPermittedActions() {
    return permittedActions;
  }

  public void setPermittedActions(List<String> permittedActions) {
    this.permittedActions = permittedActions;
  }
}
