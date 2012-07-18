package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobsReviewReceiver extends Receiver<List<JobProxy>> {

  private JobsReviewView jobsReviewView;

  public JobsReviewReceiver(JobsReviewView jobsReviewView) {
    this.jobsReviewView = jobsReviewView;
  }

  public void onSuccess(List<JobProxy> response) {

    if (response.size() == 0) {
      jobsReviewView.noAnnouncedJobs();
      return;
    }

    jobsReviewView.showAnnouncedJobs(response);
  }
}
