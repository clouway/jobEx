package com.clouway.jobex.server.emailservice;

import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EmailServiceLocator implements ServiceLocator {

  public Object getInstance(Class<?> clazz) {

    JobRepository jobRepository = new JobRepositoryImpl(DatastoreServiceFactory.getDatastoreService());
    EmailSender emailSender = new EmailSenderImpl();

    return new EmailServiceImpl(jobRepository, emailSender);
  }
}
