package com.clouway.jobex.client.security;

import com.google.gwt.user.client.Cookies;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserCredentialsLocalStorageImpl implements UserCredentialsLocalStorage {

  private final String sid = "SID";
  private final String username = "USERNAME";
  private final String companyName = "COMPANY_NAME";
  private final String loginType = "TYPE";


  @Override
  public String getSID() {
    return Cookies.getCookie(sid);
  }

  @Override
  public String getUsername() {
    return Cookies.getCookie(username);
  }

  @Override
  public void setUserCredentials(String email, String sessionID) {
    Cookies.setCookie(sid, sessionID);
    Cookies.setCookie(username, email);
  }

  @Override
  public boolean isAuthorized() {
    String sessionID = Cookies.getCookie(sid);
    return sessionID != null && !sid.equals("");

  }

  @Override
  public void deleteCookies() {
    Cookies.removeCookie(sid);
    Cookies.removeCookie(username);
  }
}
