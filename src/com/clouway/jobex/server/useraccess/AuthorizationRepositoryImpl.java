package com.clouway.jobex.server.useraccess;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationRepositoryImpl implements AuthorizationRepository {


  private DatastoreService service;

  public AuthorizationRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public boolean isNotRegister(String kind, String email) {
    try {
      service.get(KeyFactory.createKey(kind, email));
    } catch (EntityNotFoundException e) {
      return true;
    }
    return false;
  }

  @Override
  public void register(String kind, String email, String password) {
    Entity user = new Entity(kind, email);
    user.setProperty("password", password);
    service.put(user);
  }

  @Override
  public boolean isAuthorized(String kind, String email, String password) {

    Entity loginEntity;
    try {
      loginEntity = service.get(KeyFactory.createKey(kind, email));
    } catch (EntityNotFoundException e) {
      return false;
    }

    return password.equals(String.valueOf(loginEntity.getProperty("password")));

  }

  @Override
  public void saveAsLogged(String email, String loginType, String id) {
    Entity loggedUser = new Entity("Logged", email);
    loggedUser.setProperty("loginType", loginType);
    loggedUser.setProperty("id", id);

    service.put(loggedUser);
  }

  @Override
  public boolean isUserAuthorized(String email, String id) {
    Entity loggedUser;
    try {
      loggedUser = service.get(KeyFactory.createKey("Logged", email));
    } catch (EntityNotFoundException e) {
      return false;
    }
    return id.equals(String.valueOf(loggedUser.getProperty("id")));
  }

  @Override
  public boolean isSIDRegistered(String sid) {
    Query query = new Query("Logged");
    query.setFilter(new Query.FilterPredicate("id", Query.FilterOperator.EQUAL, sid));
    Entity entity = service.prepare(query).asSingleEntity();
    return entity != null;
  }
}
