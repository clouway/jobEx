package com.clouway.jobex.server.job;

import com.clouway.jobex.server.job.jobannounce.JobAnnounceServiceImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobServiceLocator implements ServiceLocator {

  public Object getInstance(Class<?> clazz) {

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    JobRepository jobRepository = new JobRepositoryImpl(datastoreService);

    return new JobAnnounceServiceImpl(jobRepository, validator);

  }
}
