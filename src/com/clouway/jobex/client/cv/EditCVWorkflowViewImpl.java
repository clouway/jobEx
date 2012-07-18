package com.clouway.jobex.client.cv;

import com.clouway.jobex.client.applyingforjob.SelectCvPlace;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCVWorkflowViewImpl extends Composite implements EditCVWorkflowView {


  private final PlaceController controller;

  interface EditCVWorkflowViewImplUiBinder extends UiBinder<HTMLPanel, EditCVWorkflowViewImpl> {
  }

  interface Driver extends RequestFactoryEditorDriver<CVProxy, CVEditor> {
  }

  private EditCvWorkflow workflow;

  private Driver driver = GWT.create(Driver.class);

  private JobExRequestFactory.CVsRequestContext requestContext;

  private CVProxy proxy;

  private static EditCVWorkflowViewImplUiBinder ourUiBinder = GWT.create(EditCVWorkflowViewImplUiBinder.class);

  @UiField
  Button save;

  @UiField
  CVEditor editor;

  @Inject
  public EditCVWorkflowViewImpl(JobExRequestFactory factory, PlaceController controller) {
    this.controller = controller;
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
    driver.initialize(factory, editor);
  }

  @Override
  public void edit(CVProxy cvProxy, JobExRequestFactory.CVsRequestContext context) {

    requestContext = context;

    proxy = requestContext.edit(cvProxy);

    driver.edit(proxy, context);

  }

  @Override
  public void goToCvPreview() {
    controller.goTo(new SelectCvPlace());
  }

  @Override
  public void setPresenter(EditCvWorkflow workflow) {
    this.workflow = workflow;
  }


  @UiHandler("save")
  public void onSave(ClickEvent event) {
    driver.flush();
    workflow.update(proxy, requestContext);
  }
}