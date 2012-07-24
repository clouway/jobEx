package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityProviderImpl implements SecurityProvider {

  private String id = "";

  private String email = "";

  private String loginType = "";

  @Override
  public boolean isAuthorized() {
    return id != "" && id != "" && loginType != "";
  }

  @Override
  public void setCredentials(String id, String email, String loginType) {
    this.id = id;
    this.email = email;
    this.loginType = loginType;
  }

  public String getLoginType() {
    return loginType;
  }


}
