package com.clouway.jobex.client.reviewjobs;

import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsPresenterImpl extends AbstractActivity implements ReviewJobsPresenter {

  private JobExRequestFactory requestFactory;
  private ReviewJobsView reviewJobsView;
  private UserCredentialsLocalStorage userCredentials;

  @Inject
  public ReviewJobsPresenterImpl(JobExRequestFactory requestFactory, ReviewJobsView reviewJobsView, UserCredentialsLocalStorage userCredentials) {
    this.requestFactory = requestFactory;
    this.reviewJobsView = reviewJobsView;
    this.userCredentials = userCredentials;
  }

  /**
   * Review announced jobs for given company
   */
  public void reviewAnnouncedJobs() {

    requestFactory.jobsReviewContext().getAnnouncedJobsForCompany(userCredentials.getUsername()).to(new ReviewJobsReceiver(reviewJobsView)).fire();
  }

  /**
   * Delete announced job with given jobId
   *
   * @param jobId - jobId
   */
  public void deleteAnnouncedJob(final Long jobId, final String companyName) {

    requestFactory.jobsReviewContext().deleteAnnouncedJob(jobId, companyName).to(new Receiver<List<JobProxy>>() {
      public void onSuccess(List<JobProxy> response) {
        reviewJobsView.updateAnnounceJobs(response);
      }
    }).fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    reviewAnnouncedJobs();

    reviewJobsView.setPresenter(this);

    panel.setWidget((IsWidget) reviewJobsView);

  }
}