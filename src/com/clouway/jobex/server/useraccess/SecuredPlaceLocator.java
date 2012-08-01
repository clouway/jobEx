package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredPlaceLocator extends Locator<SecuredPlace, Long> {
  @Override
  public SecuredPlace create(Class<? extends SecuredPlace> clazz) {
    return new SecuredPlace();
  }


  @Override
  public SecuredPlace find(Class<? extends SecuredPlace> clazz, Long id) {
    return new SecuredPlace();
  }


  @Override
  public Class<SecuredPlace> getDomainType() {
    return SecuredPlace.class;
  }

  @Override
  public Long getId(SecuredPlace domainObject) {
    return 2l;
  }

  @Override
  public Class<Long> getIdType() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object getVersion(SecuredPlace domainObject) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
