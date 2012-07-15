package com.clouway.jobex.shared;

import com.clouway.jobex.server.applyingforjob.JobApplicationService;
import com.clouway.jobex.server.applyingforjob.JobApplicationServiceLocator;
import com.clouway.jobex.server.cv.CvsServiceLocator;
import com.clouway.jobex.server.cv.CvsService;
<<<<<<< HEAD
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.server.job.JobServiceLocator;
=======
>>>>>>> commit
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

    Request<List<CVProxy>> fetchCreatedCVs(String username);

    Request<Void> create(String username,CVProxy proxy);

  }

  @Service(value = JobAnnounceService.class, locator = JobServiceLocator.class)
  public interface JobRequestContext extends RequestContext {

    /**
     * Announce a job with given companyName and JobProxy
     *
     * @param companyName a companyName
     * @param jobProxy a jobProxy
     * @return
     */
    Request<Void> announceJob(String companyName, JobProxy jobProxy);
  }

  JobRequestContext jobRequestContext();

  CVsRequestContext cvsRequestContext();

  JobApplicationRequestContext jobApplicationContext();

}
