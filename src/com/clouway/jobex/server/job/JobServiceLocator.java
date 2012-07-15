package com.clouway.jobex.server.job;

import com.clouway.jobex.server.job.jobannounce.JobAnnounceServiceImpl;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobServiceLocator implements ServiceLocator {

  private JobRepository repository = new JobRepositoryImpl();

  public Object getInstance(Class<?> clazz) {
    return new JobAnnounceServiceImpl(repository);
  }
}
