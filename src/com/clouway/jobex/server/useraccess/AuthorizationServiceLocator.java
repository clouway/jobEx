package com.clouway.jobex.server.useraccess;

import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceServiceImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationServiceLocator implements ServiceLocator {
  public Object getInstance(Class<?> clazz) {
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    AuthorizationRepository authorizationRepository = new AuthorizationRepositoryImpl(datastoreService);

    return new AuthorizationServiceImpl(authorizationRepository);
  }
}
