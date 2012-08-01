package com.clouway.jobex.client.security;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.clouway.jobex.server.useraccess.SecuredPlaceLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@ProxyFor(value = SecuredPlace.class,locator = SecuredPlaceLocator.class)
public interface SecuredPlaceProxy extends EntityProxy {

  public String getLinkText();

  public void setLinkText(String linkText);
}
