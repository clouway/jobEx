package com.clouway.jobex.client.cvsreview;

import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EmailNotificator extends Receiver<Void> {

  private ReviewCVView reviewCVView;

  public EmailNotificator(ReviewCVView reviewCVView) {
    this.reviewCVView = reviewCVView;
  }

  public void onSuccess(Void response) {
    reviewCVView.showSentEmailNotification();
  }
}
