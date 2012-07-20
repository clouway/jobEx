package com.clouway.jobex.server.useraccess;

import com.google.appengine.api.datastore.DatastoreService;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AuthorizationRepository {
  boolean isNotRegister(String kind, String email);
  
  void register(String kind, String email, String password);

  boolean verifyUserPassword(String kind, String email, String password);
}
