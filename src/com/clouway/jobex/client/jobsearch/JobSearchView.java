package com.clouway.jobex.client.jobsearch;

import com.clouway.jobex.shared.JobProxy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchView {


  void fillTableWithJobAds(List<JobProxy> listOfJobObjects);


  String getCategoryValue();
  String getLocationValue();
  void disableSearchButton();
  void enableSearchButton();

  public interface Presenter{
    void onSearchButtonClicked();
  }

  void setPresenter(Presenter presenter);
}
