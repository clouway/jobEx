package com.clouway.jobex.server.job;

import com.clouway.jobex.server.job.jobsearch.DomainObjectConverter;
import com.clouway.jobex.server.job.jobsearch.JobObjectConverter;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchLocator implements ServiceLocator{
  public Object getInstance(Class<?> clazz) {
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    JobRepository jobRepository = new JobRepositoryImpl(datastoreService);
    DomainObjectConverter<Job> jobConverter = new JobObjectConverter();
    return new JobSearchServiceImpl(jobRepository, jobConverter);
  }
}
