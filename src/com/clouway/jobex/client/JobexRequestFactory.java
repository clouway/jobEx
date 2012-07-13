package com.clouway.jobex.client;

import com.clouway.jobex.server.inject.MyServiceLocator;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobexRequestFactory extends RequestFactory{

  @Service(value = JobSearchServiceImpl.class, locator = MyServiceLocator.class)
  public interface JobRequest extends RequestContext{

    Request<List<JobProxy>> search(JobProxy jobProxy);
  }

  JobRequest jobRequest();
}
