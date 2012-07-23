package com.clouway.jobex.client.cvsreview;

import com.clouway.jobex.shared.CVProxy;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ReviewCVView {

  /**
   * Show submitted CVs for a job
   *
   * @param submittedCVs - list of submitted CVs
   */
  void showSubmittedCVs(List<CVProxy> submittedCVs);

  /**
   * Show notification for no submitted CVs
   */
  void showNoSubmittedCVsNotification();

  /**
   * Set presenter which will drive the view
   *
   * @param reviewCVPresenter - a reviewCVPresenter
   */
  void setPresenter(ReviewCVPresenterImpl reviewCVPresenter);

  void goToReviewJobPlace();

  /**
   * Show notification that email was sent
   */
  void showSentEmailNotification();

  /**
   * Set jobId for which submitted CVs will be reviewed
   *
   * @param jobId - a jobId
   */
  void setJobId(Long jobId);
}
