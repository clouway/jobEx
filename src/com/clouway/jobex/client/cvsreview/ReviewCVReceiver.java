package com.clouway.jobex.client.cvsreview;

import com.clouway.jobex.shared.CVProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewCVReceiver extends Receiver<List<CVProxy>> {

  private ReviewCVView view;

  public ReviewCVReceiver(ReviewCVView view) {
    this.view = view;
  }

  public void onSuccess(List<CVProxy> response) {

    if (response.size() == 0) {
      view.showNoSubmittedCVsNotification();
      view.goToReviewJobPlace();
      return;
    }

    view.showSubmittedCVs(response);
  }
}
