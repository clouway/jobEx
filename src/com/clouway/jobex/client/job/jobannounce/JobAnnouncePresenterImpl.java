package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * JobAnnouncePresenterImpl class is used to announce new jobs
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImpl implements JobAnnounceView.Presenter {

  private final JobExRequestFactory.JobRequestContext requestContext;
  private final JobAnnounceView view;
  private final CompanyNameProvider companyNameProvider;

  public JobAnnouncePresenterImpl(JobExRequestFactory.JobRequestContext requestContext, JobAnnounceView view, CompanyNameProvider companyNameProvider) {
    this.requestContext = requestContext;
    this.view = view;
    this.companyNameProvider = companyNameProvider;
    this.view.setPresenter(this);
  }

  /**
   * Create request which will be fired with given JobProxy and Receiver
   *
   * @param jobProxy a jobProxy
   * @param receiver a receiver
   */
  public void createRequest(JobProxy jobProxy, Receiver<Void> receiver) {
    requestContext.announceJob(companyNameProvider.getCompanyName(), jobProxy).to(receiver);
  }

  /**
   * Announce a job, i.e. fires the request
   */
  public void announceJob() {
    requestContext.fire();
  }

  /**
   * Get JobRequestContext
   *
   * @return a JobRequestContext
   */
  public JobExRequestFactory.JobRequestContext getJobRequestContext() {
    return requestContext;
  }

  /**
   * Get JobProxy
   *
   * @return a JobProxy
   */
  public JobProxy getJobProxy() {
    return requestContext.create(JobProxy.class);
  }
}
