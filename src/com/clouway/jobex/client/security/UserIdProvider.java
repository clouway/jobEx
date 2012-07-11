package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserIdProvider {

  Long getUserId();

  void setUserId(Long id);

}
