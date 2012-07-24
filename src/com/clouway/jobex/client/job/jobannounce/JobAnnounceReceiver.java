package com.clouway.jobex.client.job.jobannounce;

import com.google.web.bindery.requestfactory.shared.Receiver;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * JobAnnounceReceiver implements Receiver<Void>
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceReceiver extends Receiver<Void> {

  private JobAnnounceView view;

  public JobAnnounceReceiver(JobAnnounceView view) {
    this.view = view;
  }

  /**
   * When onSuccess method is invoked,
   * the view loads (goTo) MainPlace.
   *
   * @param response a returned response
   */
  public void onSuccess(Void response) {
    view.goToSearchPlace();
  }


  public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {

  }
}
