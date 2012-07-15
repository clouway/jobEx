package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * JobAnnouncePresenterImpl class is used to announce new jobs
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImpl extends AbstractActivity implements JobAnnouncePresenter {

  private final JobExRequestFactory.JobRequestContext requestContext;
  private final JobAnnounceView view;
  private final CompanyNameProvider companyNameProvider;

  private JobProxy editableJobProxy;

  public JobAnnouncePresenterImpl(JobExRequestFactory.JobRequestContext requestContext, JobAnnounceView view, CompanyNameProvider companyNameProvider) {
    this.requestContext = requestContext;
    this.view = view;
    this.companyNameProvider = companyNameProvider;
  }

  /**
   * Announce a job, i.e. fires the request
   */
  public void announceJob() {
    requestContext.fire();
  }

  public void initialize() {

    JobProxy jobProxy = requestContext.create(JobProxy.class);

    editableJobProxy = requestContext.edit(jobProxy);

    requestContext.announceJob(companyNameProvider.getCompanyName(), editableJobProxy).to(new JobAnnounceReceiver(view));
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    this.initialize();

    view.setPresenter(this);

    view.edit(requestContext, editableJobProxy);

    panel.setWidget((IsWidget) view);
  }
}
