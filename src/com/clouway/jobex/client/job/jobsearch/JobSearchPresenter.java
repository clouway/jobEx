package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchPresenter{


  private final JobExRequestFactory factory;
  private final JobSearchView jobSearchView;

  public JobSearchPresenter(JobExRequestFactory factory, JobSearchView jobSearchView) {
    this.factory = factory;
    this.jobSearchView = jobSearchView;
//    jobSearchView.setPresenter(this);
  }

  public void setPresenterToTheView() {
    jobSearchView.setPresenter(this);
  }


  public void onSearchButtonClicked() {
    search(jobSearchView.getLocationValue(), jobSearchView.getCategoryValue());
  }

  public void search(String location, String category) {


    jobSearchView.disableSearch();
    JobExRequestFactory.JobRequest jobSearchRequest = factory.jobRequest();
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
        jobSearchView.enableSearch();
        jobSearchView.showJobAds(response);

      }
    });

  }
}