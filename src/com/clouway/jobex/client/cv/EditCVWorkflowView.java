package com.clouway.jobex.client.cv;

import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface EditCVWorkflowView extends IsWidget{

  void edit(CVProxy cvProxy, JobExRequestFactory.CVsRequestContext context);

  void goToCvPreview();

  void setPresenter(EditCvWorkflow presenter);

}
