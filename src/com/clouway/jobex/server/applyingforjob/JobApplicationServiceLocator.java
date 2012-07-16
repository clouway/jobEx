package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.client.applyingforjob.ErrorMessages;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationServiceLocator implements ServiceLocator {
  @Override
  public Object getInstance(Class<?> clazz) {
    return new JobApplicationServiceImpl(new JobApplicationRepositoryImpl(DatastoreServiceFactory.getDatastoreService()), new ErrorMessages() {
      @Override
      public String jobApplicationIsPreviouslySubmitted() {
        return "Job Application Is already Submitted. ";
      }
    });
  }
}
