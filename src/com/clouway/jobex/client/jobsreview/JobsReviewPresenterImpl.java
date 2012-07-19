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
public class JobsReviewPresenterImpl extends AbstractActivity implements JobsReviewPresenter {

  private JobExRequestFactory requestFactory;
  private JobsReviewView jobsReviewView;
  private CompanyNameProvider companyNameProvider;

  @Inject
  public JobsReviewPresenterImpl(JobExRequestFactory requestFactory, JobsReviewView jobsReviewView, CompanyNameProvider companyNameProvider) {
    this.requestFactory = requestFactory;
    this.jobsReviewView = jobsReviewView;
    this.companyNameProvider = companyNameProvider;
  }

  public void getAnnouncedJobsForCompany(String companyName) {

    requestFactory.jobsReviewContext().getAnnouncedJobsForCompany(companyName).to(new JobsReviewReceiver(jobsReviewView)).fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    jobsReviewView.setPresenter(this);

    panel.setWidget((IsWidget) jobsReviewView);

    getAnnouncedJobsForCompany(companyNameProvider.getCompanyName());
  }
}
