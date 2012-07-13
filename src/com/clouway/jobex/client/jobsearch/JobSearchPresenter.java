package com.clouway.jobex.client.jobsearch;

import com.clouway.jobex.client.JobProxy;
import com.clouway.jobex.client.JobexRequestFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchPresenter implements JobSearchView.Presenter {


  private final JobexRequestFactory factory;
  private final JobSearchView jobSearchView;

  public JobSearchPresenter(JobexRequestFactory factory, JobSearchView jobSearchView) {
    this.factory = factory;
    this.jobSearchView = jobSearchView;
//    jobSearchView.setPresenter(this);
  }

  public void setPresenterToTheView() {
    jobSearchView.setPresenter(this);
  }

  //
  @Override
  public void onSearchButtonClicked() {
    search(jobSearchView.getLocationValue(), jobSearchView.getCategoryValue());
  }

  public void search(String location, String category) {


    jobSearchView.disableSearchButton();
    JobexRequestFactory.JobRequest jobSearchRequest = factory.jobRequest();
    JobProxy jobProxy = jobSearchRequest.create(JobProxy.class);

    jobProxy.setLocation(location);
    jobProxy.setCategory(category);

    jobSearchRequest.search(jobProxy).fire(new Receiver<List<JobProxy>>() {
      @Override
      public void onFailure(ServerFailure error) {
//        Window.alert("ERROR");
      }

      @Override
      public void onSuccess(List<JobProxy> response) {
        jobSearchView.enableSearchButton();
        jobSearchView.fillTableWithJobAds(response);

      }
    });

  }
}
