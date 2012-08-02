package com.clouway.jobex.client.cv;

import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflowViewImpl extends Composite implements CreatingNewCVWorkflowView {


  private CreatingNewCVWorkflow presenter;

  interface CreatingNewCvViewUiBinder extends UiBinder<HTMLPanel, CreatingNewCVWorkflowViewImpl> {
  }

  private static CreatingNewCvViewUiBinder ourUiBinder = GWT.create(CreatingNewCvViewUiBinder.class);

  @UiField
  CVEditor editor;

  @UiField
  Button create;

  @UiField
  AlertBlock alert;



  @Inject
  PlaceController controller;

  interface Driver extends RequestFactoryEditorDriver<CVProxy, CVEditor> {

  }

  private Driver driver = GWT.create(Driver.class);

  public CreatingNewCVWorkflowViewImpl() {

    initWidget(ourUiBinder.createAndBindUi(this));

    driver.initialize(editor);
  }

  @Override
  public void edit(JobExRequestFactory.CVsRequestContext requestContext, CVProxy mutableProxy) {
    driver.edit(mutableProxy, requestContext);
  }

  @Override
  public RequestContext flush() {
    return driver.flush();
  }


  @Override
  public void setWorkFlow(CreatingNewCVWorkflow presenter) {
    this.presenter = presenter;
  }

  @Override
  public void goToSelectCv() {
    controller.goTo(new PreviewCvPlace());
  }

  @UiHandler("create")
  public void onCvCreate(ClickEvent event) {

    if (Window.confirm("Create this CV?")) {
      presenter.createCV();
    }
  }

  public void showConstraintViolations(List<String> constraintViolations) {

    StringBuilder builder = new StringBuilder();

    for (String listOfError : constraintViolations) {
      builder.append(listOfError).append(" ");
    }

    alert.setText(builder.toString());
    alert.setVisible(true);
  }

  public void reset() {
    alert.setVisible(false);
  }
}