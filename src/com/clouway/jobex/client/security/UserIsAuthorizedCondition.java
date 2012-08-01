package com.clouway.jobex.client.security;

import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserIsAuthorizedCondition implements Condition {


  private final UserCredentialsLocalStorage storage;

  @Inject
  public UserIsAuthorizedCondition(UserCredentialsLocalStorage storage) {

    this.storage = storage;
  }

  @Override
  public boolean isTrue() {
    String username = storage.getUsername();
    String sid = storage.getSID();
    return !(sid == null || username == null);
  }
}
