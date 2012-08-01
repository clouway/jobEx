package com.clouway.jobex.client.navigation;

import com.google.gwt.place.shared.Place;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredPlace extends Place {

  private  String linkText;

  public SecuredPlace(String linkText) {

    this.linkText = linkText;

  }


  public SecuredPlace() {
  }


  public String getLinkText() {
    return linkText;
  }

  public void setLinkText(String linkText) {
    this.linkText = linkText;
  }
}
