package com.clouway.jobex.client.cv;

import com.google.web.bindery.requestfactory.shared.Receiver;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class EditCVWorkflowReceiver extends Receiver<Void> {

  private EditCVWorkflowView view;

  public EditCVWorkflowReceiver(EditCVWorkflowView view) {
    this.view = view;
  }

  /**
   * Go to PreviewCVPlace after successful editing of existing CV
   *
   * @param response - void
   */
  public void onSuccess(Void response) {

    view.reset();
    view.goToCvPreview();
  }

  /**
   * Show constraint violations in the view
   *
   * @param violations - list of constraint violations
   */
  public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {

    List<String> constraintViolations = new ArrayList<String>();

    for (ConstraintViolation violation : violations) {
      constraintViolations.add(violation.getMessage());
    }

    view.showConstraintViolations(constraintViolations);
  }
}
