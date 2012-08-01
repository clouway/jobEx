package com.clouway.jobex.client.cv;

import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface EditCVWorkflowView extends IsWidget{

  /**
   * Load (load view's driver) with given RequestContext and CVProxy
   *
   * @param cvProxy - proxy that will be edited
   * @param context - RequestContext that will be used to send the edited proxy to the server
   */
  void edit(CVProxy cvProxy, JobExRequestFactory.CVsRequestContext context);

  /**
   * Go to CVPreviewPlace
   */
  void goToCvPreview();

  /**
   * Set presenter that will drive the view
   *
   * @param presenter - presenter
   */
  void setPresenter(EditCvWorkflow presenter);

  /**
   * Show occured constraint violations when editing CV
   *
   * @param constraintViolations - list of constraint violations
   */
  void showConstraintViolations(List<String> constraintViolations);

  /**
   * Reset the view's widgets
   */
  void reset();
}
