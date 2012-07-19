package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsPresenterImpl extends AbstractActivity implements ReviewJobsPresenter {

  private JobExRequestFactory requestFactory;
  private ReviewJobsView reviewJobsView;
  private CompanyNameProvider companyNameProvider;

  @Inject
  public ReviewJobsPresenterImpl(JobExRequestFactory requestFactory, ReviewJobsView reviewJobsView, CompanyNameProvider companyNameProvider) {
    this.requestFactory = requestFactory;
    this.reviewJobsView = reviewJobsView;
    this.companyNameProvider = companyNameProvider;
  }

  public void reviewAnnouncedJobs(String companyName) {

    requestFactory.jobsReviewContext().getAnnouncedJobsForCompany(companyName).to(new ReviewJobsReceiver(reviewJobsView)).fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    reviewJobsView.setPresenter(this);

    panel.setWidget((IsWidget) reviewJobsView);

    reviewAnnouncedJobs(companyNameProvider.getCompanyName());
  }
}
