package com.clouway.jobex.client.cvsreview;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewCVPresenterImpl extends AbstractActivity implements ReviewCVPresenter {

  private JobExRequestFactory requestFactory;

  private ReviewCVView view;

  private Long jobId;

  @Inject
  public ReviewCVPresenterImpl(JobExRequestFactory requestFactory, ReviewCVView view) {
    this.requestFactory = requestFactory;
    this.view = view;
  }

  public void reviewSubmittedCVs(Long jobId) {

    this.jobId = jobId;

    requestFactory.cvsRequestContext().getSubmittedCVs(jobId).to(new ReviewCVReceiver(view)).fire();
  }

  /**
   * Send email approval to given user's email
   *
   * @param jobId - jobId
   * @param email - user's email
   */
  public void sendEmailApproval(Long jobId, String email) {

    if(!view.isConfirmed()) {
      return;
    }

    requestFactory.emailServiceContext().sendEmailApproval(jobId, email).to(new EmailNotificator(view)).fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    view.setPresenter(this);

    view.setJobId(jobId);

    panel.setWidget((IsWidget) view);
  }

}
