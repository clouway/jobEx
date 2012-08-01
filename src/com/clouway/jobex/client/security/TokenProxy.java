package com.clouway.jobex.client.security;

import com.clouway.jobex.server.useraccess.TokenLocator;
import com.clouway.jobex.shared.Token;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@ProxyFor(value = Token.class, locator = TokenLocator.class)
public interface TokenProxy extends EntityProxy {

  public String getEmail();

  public String getSid();

  public void setSid(String sid);

  public void setEmail(String email);

  List<String> getPermittedActions();

  public void setPermittedActions(List<String> permittedActions);

}
