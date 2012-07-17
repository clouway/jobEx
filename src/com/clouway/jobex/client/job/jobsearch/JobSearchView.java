package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.shared.JobProxy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchView {

  /**
   * Show a given list of job ads
   *
   * @param listOfJobObjects the list that is going to be shown
   */
  void showJobAds(List<JobProxy> listOfJobObjects);

  /**
   *
   * @return the value of a job category
   */
  String getCategoryValue();

  /**
   *
   * @return the value of a job location
   */
  String getLocationValue();


  /**
   * disable the search functionality
   */
  void disableSearch();

  /**
   * enable the search functionality
   */
  void enableSearch();


  /**
   * Set view's presenter
   *
   * @param presenter a presenter which will drive the view
   */
  void setPresenter(JobSearchPresenter presenter);

}
