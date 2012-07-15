package com.clouway.jobex.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobExServiceLocator implements ServiceLocator {

  private Injector injector;

  @Inject
  public JobExServiceLocator(Injector injector) {
    this.injector = injector;
  }

  public Object getInstance(Class<?> clazz) {
    return injector.getInstance(clazz);
  }
}