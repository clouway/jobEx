package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * Handle the logic for the JobSearchViewImpl
 *
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchPresenter extends AbstractActivity {


  private final JobExRequestFactory factory;
  private final JobSearchView jobSearchView;

  @Inject
  public JobSearchPresenter(JobExRequestFactory factory, JobSearchView jobSearchView) {
    this.factory = factory;
    this.jobSearchView = jobSearchView;
  }


  public void onSearchButtonClicked() {
    search(jobSearchView.getLocationValue(), jobSearchView.getCategoryValue());
  }

  /**
   * Search for jobs and show the results in a cell table
   *
   * @param location criteria for searching
   * @param category criteria for searching
   */
  public void search(String location, String category) {

    jobSearchView.disableSearch();
    JobExRequestFactory.JobRequest jobSearchRequest = factory.jobRequest();
    JobProxy jobProxy = jobSearchRequest.create(JobProxy.class);

    jobProxy.setLocation(location);
    jobProxy.setCategory(category);

    jobSearchRequest.search(jobProxy).fire(new Receiver<List<JobProxy>>() {
      @Override
      public void onFailure(ServerFailure error) {
      }

      @Override
      public void onSuccess(List<JobProxy> response) {

        jobSearchView.enableSearch();

        jobSearchView.showJobAds(response);

      }
    });

  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    jobSearchView.setPresenter(this);

    panel.setWidget((JobSearchViewImpl) jobSearchView);

  }
}
