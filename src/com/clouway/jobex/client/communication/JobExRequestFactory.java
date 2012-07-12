package com.clouway.jobex.client.communication;

import com.clouway.jobex.server.applyingforjob.JobApplicationService;
import com.clouway.jobex.server.applyingforjob.JobApplicationServiceLocator;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.shared.proxies.CVProxy;
import com.clouway.jobex.shared.proxies.JobApplicationProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobExRequestFactory extends RequestFactory {


  @Service(value = JobApplicationService.class, locator = JobApplicationServiceLocator.class)

  public interface JobApplicationRequestContext extends RequestContext {

    Request<Void> applyForJob(JobApplicationProxy applicationProxy);


  }

  @Service(value = CvsService.class, locator = CvsServiceLocator.class)
  public interface CVsRequestContext extends RequestContext {
    Request<List<CVProxy>> fetchCreatedCVs();
  }

  CVsRequestContext cvsRequestContext();

  JobApplicationRequestContext jobApplicationContext();

}
