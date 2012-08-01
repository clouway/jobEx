package com.clouway.jobex.client.cv;

import com.google.web.bindery.requestfactory.shared.Receiver;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCvReceiver extends Receiver<Void> {


  private final CreatingNewCVWorkflowView view;

  public CreatingNewCvReceiver(CreatingNewCVWorkflowView view) {

    this.view = view;
  }

  public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {

    List<String> constraintViolations = new ArrayList<String>();

    for (ConstraintViolation<?> violation : violations) {
      constraintViolations.add(violation.getMessage());
    }

    view.showConstraintViolations(constraintViolations);
  }

  public void onSuccess(Void response) {

    view.reset();
    view.goToSelectCv();
  }

}
