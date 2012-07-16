package com.clouway.jobex.client.security;

import com.google.gwt.user.client.Cookies;

/**
 * CompanyNameProviderImpl implements CompanyNameProvider.
 * Uses Cookies object to set/get company's name.
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class CompanyNameProviderImpl implements CompanyNameProvider {

  public void setCompanyName(String companyName) {
    Cookies.setCookie("companyName", "clouway");
  }

  public String getCompanyName() {
    //return Cookies.getCookie("companyName");
    return "clouway";
  }
}
