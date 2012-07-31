package com.clouway.jobex.client.job.jobannounce;

import com.google.web.bindery.requestfactory.shared.Receiver;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
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

  public void onSuccess(Void response) {

    view.reset();
    view.goToReviewJobsPlace();
  }

  /**
   * Show constraint violations in the view
   *
   * @param violations - set of constraint violations
   */
  public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {

    List<String> listOfConstraintViolations = new ArrayList<String>();

    for (ConstraintViolation<?> violation : violations) {
      listOfConstraintViolations.add(violation.getMessage());
    }

    view.showConstraintViolations(listOfConstraintViolations);
  }
}
