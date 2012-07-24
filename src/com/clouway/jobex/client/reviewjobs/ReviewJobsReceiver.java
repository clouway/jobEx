package com.clouway.jobex.client.reviewjobs;

import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsReceiver extends Receiver<List<JobProxy>> {

  private ReviewJobsView reviewJobsView;

  public ReviewJobsReceiver(ReviewJobsView reviewJobsView) {
    this.reviewJobsView = reviewJobsView;
  }

  public void onSuccess(List<JobProxy> response) {

    if (response.size() == 0) {
      reviewJobsView.showNoAnnouncedJobsNotification();
      return;
    }

    reviewJobsView.showAnnouncedJobs(response);
  }
}
