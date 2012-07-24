package com.clouway.jobex.client.cvsreview;

import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SentEmailReceiver extends Receiver<Void> {

  private SubmittedCVsView submittedCVsView;

  public SentEmailReceiver(SubmittedCVsView submittedCVsView) {
    this.submittedCVsView = submittedCVsView;
  }

  public void onSuccess(Void response) {
    submittedCVsView.showSentEmailNotification();
  }
}
