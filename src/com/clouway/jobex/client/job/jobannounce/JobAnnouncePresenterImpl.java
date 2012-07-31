package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

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

  @Inject
  public JobAnnouncePresenterImpl(JobExRequestFactory requestFactory, JobAnnounceView view, UserCredentialsLocalStorage companyNameProvider) {
    this.requestFactory = requestFactory;
    this.view = view;
    this.companyNameProvider = companyNameProvider;
  }

  /**
   * Announce the prepared Job.
   */
  public void announceJob() {
    requestContext.fire();
  }


  public void prepareJob() {

    requestFactory.jobRequestContext().prepareNewJob().fire(new Receiver<JobProxy>() {

      public void onSuccess(JobProxy response) {

        requestContext = requestFactory.jobRequestContext();
        JobProxy editableProxy = requestContext.edit(response);

        view.edit(requestContext, editableProxy);

        requestContext.announceJob(companyNameProvider.getUsername(), editableProxy).to(new JobAnnounceReceiver(view));
      }
    });
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    prepareJob();

    view.setPresenter(this);
    view.reset();

    panel.setWidget((IsWidget) view);
  }
}
