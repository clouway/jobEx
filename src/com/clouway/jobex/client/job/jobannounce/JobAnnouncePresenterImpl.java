package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * JobAnnouncePresenterImpl class is used to announce new jobs
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnouncePresenterImpl extends AbstractActivity implements JobAnnouncePresenter {

  private final JobExRequestFactory requestFactory;
  private final JobAnnounceView view;
  private final UserCredentialsLocalStorage companyNameProvider;

  private JobExRequestFactory.JobRequestContext requestContext;
  private JobProxy editableJobProxy;

  @Inject
  public JobAnnouncePresenterImpl(JobExRequestFactory requestFactory, JobAnnounceView view, UserCredentialsLocalStorage companyNameProvider) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.companyNameProvider = companyNameProvider;
  }

  /**
   * Announce a job, i.e. fires the request
   */
  public void announceJob() {

    if(!view.isConfirmed()) {
      return;
    }

    requestContext.fire();
  }

  public void initialize() {

    requestContext = requestFactory.jobRequestContext();

    JobProxy jobProxy = requestContext.create(JobProxy.class);

    editableJobProxy = requestContext.edit(jobProxy);

    requestContext.announceJob(companyNameProvider.getUsername(), editableJobProxy).to(new JobAnnounceReceiver(view));

  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    this.initialize();

    view.setPresenter(this);

    view.edit(requestContext, editableJobProxy);

    panel.setWidget((IsWidget) view);

  }
}
