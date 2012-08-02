package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.UserCredentials;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserCredentialsLocator extends Locator<UserCredentials, Long> {

  @Override
  public UserCredentials create(Class<? extends UserCredentials> clazz) {
    return new UserCredentials();
  }

  @Override
  public UserCredentials find(Class<? extends UserCredentials> clazz, Long id) {
    return new UserCredentials();
  }

  @Override
  public Class<UserCredentials> getDomainType() {
    return UserCredentials.class;
  }


  @Override
  public Long getId(UserCredentials domainObject) {
    return 1l;
  }


  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }


  @Override
  public Object getVersion(UserCredentials domainObject) {
    return 1;
  }
}
