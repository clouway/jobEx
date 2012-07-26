package com.clouway.jobex.client.job.jobannounce;

import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * JobAnnounceReceiver implements Receiver<Void>
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceReceiver extends Receiver<List<String>> {

  private JobAnnounceView view;

  public JobAnnounceReceiver(JobAnnounceView view) {
    this.view = view;
  }

  /**
   * Go to given page if there are no errors, otherwise show them.
   *
   * @param response - list of occurred errors
   */
  public void onSuccess(List<String> response) {

    if (response.size() > 0) {
      view.showOccurredErrors(response);
      return;
    }

    view.goToReviewJobsPlace();
  }
}
