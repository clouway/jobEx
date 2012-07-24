package com.clouway.jobex.server.reviewjobs;

import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.clouway.jobex.server.job.jobsearch.JobObjectConverter;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobsReviewServiceLocator implements ServiceLocator {

  public Object getInstance(Class<?> clazz) {
    return new JobsReviewServiceImpl(new JobRepositoryImpl(DatastoreServiceFactory.getDatastoreService()), new JobObjectConverter());
  }
}
