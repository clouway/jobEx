package com.clouway.jobex.client.creatingnewcv;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.CVProxy;
import com.google.web.bindery.requestfactory.shared.RequestContext;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CreatingNewCVWorkflowView {


  void notifyUserOfSuccessfulCVCreation();

  void initializeEditorWithRequestFactory(JobExRequestFactory requestFactory);

  void edit(JobExRequestFactory.CVsRequestContext a, CVProxy mutableProxy);

  RequestContext flush();

  void notifyUserOfConnectionError();

  void setWorkFlow(CreatingNewCVWorkflow presenter);
}
