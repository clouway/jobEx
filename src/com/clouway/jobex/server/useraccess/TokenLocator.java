package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.shared.Token;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class TokenLocator extends Locator<Token, Long> {

  @Override
  public Token create(Class<? extends Token> clazz) {
    return new Token();
  }

  @Override
  public Token find(Class<? extends Token> clazz, Long id) {
    return new Token();
  }

  @Override
  public Class<Token> getDomainType() {
    return Token.class;
  }


  @Override
  public Long getId(Token domainObject) {
    return 1l;
  }


  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }


  @Override
  public Object getVersion(Token domainObject) {
    return 1;
  }
}
