package com.clouway.jobex.client.submittedcvs;

import com.clouway.jobex.shared.CVProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SubmittedCVsReceiver extends Receiver<List<CVProxy>> {

  private SubmittedCVsView view;

  public SubmittedCVsReceiver(SubmittedCVsView view) {
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
