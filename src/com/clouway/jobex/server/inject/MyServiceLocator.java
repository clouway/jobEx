package com.clouway.jobex.server.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MyServiceLocator implements ServiceLocator {

  private Injector injector;

  @Inject
  public MyServiceLocator(Injector injector) {
    this.injector = injector;
  }

  public Object getInstance(Class<?> clazz) {
    return injector.getInstance(clazz);
  }
}