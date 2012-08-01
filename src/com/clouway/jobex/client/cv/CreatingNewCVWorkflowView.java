package com.clouway.jobex.client.cv;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.CVProxy;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CreatingNewCVWorkflowView {

  void edit(JobExRequestFactory.CVsRequestContext a, CVProxy mutableProxy);

  RequestContext flush();

  void setWorkFlow(CreatingNewCVWorkflow presenter);

  void goToSelectCv();

  /**
   * Show occurred constraint violations after sending a request
   *
   * @param constraintViolations - list of constraint violations
   */
  void showConstraintViolations(List<String> constraintViolations);

  /**
   * Resets view's widgets values
   */
  void reset();
}
