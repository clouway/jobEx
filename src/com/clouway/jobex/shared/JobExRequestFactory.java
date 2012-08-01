package com.clouway.jobex.shared;

import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.client.security.TokenProxy;
import com.clouway.jobex.server.applyingforjob.JobApplicationService;
import com.clouway.jobex.server.applyingforjob.JobApplicationServiceLocator;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.server.cv.CvsServiceLocator;
import com.clouway.jobex.server.emailservice.EmailService;
import com.clouway.jobex.server.emailservice.EmailServiceLocator;
import com.clouway.jobex.server.job.JobSearchLocator;
import com.clouway.jobex.server.job.JobServiceLocator;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.clouway.jobex.server.jobsreview.JobsReviewService;
import com.clouway.jobex.server.jobsreview.JobsReviewServiceLocator;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.server.useraccess.AuthorizationServiceLocator;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobExRequestFactory extends RequestFactory {


  @Service(value = JobSearchServiceImpl.class, locator = JobSearchLocator.class)
  public interface JobRequest extends RequestContext {

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

    Request<CVProxy> fetchCreatedCv(String username, Long cvId);


    Request<List<CVProxy>> delete(String username, long cvId);

    Request<List<CVProxy>> getSubmittedCVs(Long jobId);

  }

  @Service(value = JobAnnounceService.class, locator = JobServiceLocator.class)
  public interface JobRequestContext extends RequestContext {

    /**
     * Announce a job with given companyName and JobProxy
     *
     * @param companyName a companyName
     * @param jobProxy    a jobProxy
     * @return - null
     */
    Request<Void> announceJob(String companyName, JobProxy jobProxy);
  }

  @Service(value = JobsReviewService.class, locator = JobsReviewServiceLocator.class)
  public interface JobsReviewContext extends RequestContext {

    /**
     * Get list of all announced jobs for given company
     *
     * @param companyName - company name
     * @return - list of announced jobs
     */
    Request<List<JobProxy>> getAnnouncedJobsForCompany(String companyName);

    Request<List<JobProxy>> deleteAnnouncedJob(Long jobId, String companyName);
  }


  @Service(value = AuthorizationService.class, locator = AuthorizationServiceLocator.class)
  public interface AuthorizationRequestContext extends RequestContext {

    Request<Void> register(String registrationType, String email, String password);

    Request<TokenProxy> login(String loginType, String email, String password);

    Request<Boolean> isValid(String sid);


  }


  @Service(value = EmailService.class, locator = EmailServiceLocator.class)
  public interface EmailServiceContext extends RequestContext {
    Request<Void> sendEmailApproval(Long jobId, String email);
  }


  JobsReviewContext jobsReviewContext();

  JobRequestContext jobRequestContext();

  JobRequest jobRequest();

  CVsRequestContext cvsRequestContext();

  JobApplicationRequestContext jobApplicationContext();

  AuthorizationRequestContext authorizationContext();

  EmailServiceContext emailServiceContext();

}
