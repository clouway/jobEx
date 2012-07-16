package com.clouway.jobex.server.cv;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvsServiceLocator implements ServiceLocator {
  @Override
  public Object getInstance(Class<?> clazz) {
    return new CvsServiceImpl(new CvRepositoryImpl(DatastoreServiceFactory.getDatastoreService()));
  }
}
