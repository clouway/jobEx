package com.clouway.jobex.client.creatingnewcv.view;

import com.clouway.jobex.client.communication.JobExRequestFactory;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflow;
import com.clouway.jobex.shared.proxies.CVProxy;
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
