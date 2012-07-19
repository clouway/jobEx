package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.shared.JobProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ReviewJobsView {

  /**
   * Show announced jobs made be a company
   *
   * @param announcedJobs - returned list of announced jobs
   */
  void showAnnouncedJobs(List<JobProxy> announcedJobs);

  /**
   * Notifies that the company has not announced any jobs
   */
  void showNoAnnouncedJobsNotification();

  /**
   * Set presenter which will drive the view
   *
   * @param reviewJobsPresenter - a reviewJobsPresenter
   */
  void setPresenter(ReviewJobsPresenter reviewJobsPresenter);
}
