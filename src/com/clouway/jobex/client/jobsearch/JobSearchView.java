package com.clouway.jobex.client.jobsearch;

import com.clouway.jobex.client.JobProxy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchView {

  public void fillTableWithJobAds(List<JobProxy> listOfJobObjects);
  public String getCategoryValue();
  public String getLocationValue();
  public void disableSearchButton();
  public void enableSearchButton();
//  public void clearTable();

  public interface Presenter{
    void onSearchButtonClicked();
  }

  public void setPresenter(Presenter presenter);
}
