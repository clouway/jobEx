package com.clouway.jobex.client.security;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompanyRegisteredEvent extends GwtEvent<CompanyRegisteredEventHandler> {
  public static Type<CompanyRegisteredEventHandler> TYPE = new Type<CompanyRegisteredEventHandler>();
  private String email;
  private String SID;

  public Type<CompanyRegisteredEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(CompanyRegisteredEventHandler handler) {
    handler.onCompanyRegistered(this);
  }

  public String getEmail() {
    return email;
  }

  public String getSID() {
    return SID;
  }
}
