package com.clouway.jobex.server.inject;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@Singleton
public class MyRequestFactoryServlet  extends RequestFactoryServlet {

  @Inject
  public MyRequestFactoryServlet(MyServiceLayerDecorator decorator) {
    super(new DefaultExceptionHandler(), decorator);
  }
}