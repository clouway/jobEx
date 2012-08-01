package com.clouway.jobex.client.security;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompanyAuthorizedEvent extends GwtEvent<CompanyAuthorizedEventHandler> {
  public static Type<CompanyAuthorizedEventHandler> TYPE = new Type<CompanyAuthorizedEventHandler>();

  public Type<CompanyAuthorizedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(CompanyAuthorizedEventHandler handler) {
    handler.onCompanyAuthorized(this);
  }
}
