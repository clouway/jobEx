package com.clouway.jobex.server.job.jobsearch;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobServiceLocator implements ServiceLocator{
//
  @Override
  public Object getInstance(Class<?> clazz) {
//    if (clazz == JobSearchService.class) {
//      return new JobSearchServiceImpl();
//    }
    return null;
  }
}
