package com.clouway.jobex.shared;

import com.clouway.jobex.server.applyingforjob.JobApplicationService;
import com.clouway.jobex.server.applyingforjob.JobApplicationServiceLocator;
import com.clouway.jobex.server.cv.CvsServiceLocator;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.server.job.JobSearchLocator;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.server.job.JobServiceLocator;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobExRequestFactory extends RequestFactory{


  @Service(value = JobSearchServiceImpl.class, locator = JobSearchLocator.class)
  public interface JobRequest extends RequestContext{

    Request<List<JobProxy>> search(JobProxy jobProxy);

  }

  @Service(value = JobApplicationService.class, locator = JobApplicationServiceLocator.class)
  public interface JobApplicationRequestContext extends RequestContext {
    Request<List<String>> applyForJob(JobApplicationProxy applicationProxy);
  }

  @Service(value = CvsService.class, locator = CvsServiceLocator.class)
  public interface CVsRequestContext extends RequestContext {

    Request<List<CVProxy>> fetchCreatedCVs(String username);

    Request<Void> add(String username, CVProxy proxy);

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

  JobRequest jobRequest();

  CVsRequestContext cvsRequestContext();

  JobApplicationRequestContext jobApplicationContext();

}
