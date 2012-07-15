package com.clouway.jobex.client.applyingforjob;


import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationViewImpl extends Composite implements JobApplicationView {


  interface JobApplicationViewImplUiBinder extends UiBinder<HTMLPanel, JobApplicationViewImpl> {
  }

  private static JobApplicationViewImplUiBinder ourUiBinder = GWT.create(JobApplicationViewImplUiBinder.class);

  @UiField(provided = true)
  CellList<CVProxy> cVCellTable;

  @UiField
  Label errors;

  @UiField
  Label messages;


  private JobApplicationPresenter presenter;

  private Long jobId;

  private SingleSelectionModel<CVProxy> selectionModel = new SingleSelectionModel<CVProxy>();


  public JobApplicationViewImpl() {

    JobExRequestFactory factory = GWT.create(JobExRequestFactory.class);


    CVCell cvCell = new CVCell();

    cVCellTable = new CellList<CVProxy>(cvCell);

    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Long cvId = selectionModel.getSelectedObject().getId();
        presenter.applyForJob(jobId,cvId);
        Window.alert("Selected ... !");
      }
    });
    cVCellTable.setSelectionModel(selectionModel);
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }


  @Override
  public void notifyUserOfSuccessfulAppliance() {
    errors.setText("You have successfully applied for a job !");
  }


  @Override
  public void showErrors(List<String> errorstringList) {
    StringBuilder builder = new StringBuilder();
    for (String string : errorstringList) {
      builder.append(string);
    }
    errors.setText(builder.toString());
  }

  @Override
  public void notifyUserOfCommunicationError() {
    errors.setText("System error occurred while trying to apply job !");
  }


  public void setPresenter(JobApplicationPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void showCreatedCVs(List<CVProxy> cvs) {
    cVCellTable.setVisibleRange(0, cvs.size());
    cVCellTable.setRowData(cvs);
  }



  @Override
  public void goToCreateNewCVForm() {
    Window.alert("got to new CV creation ... !");
  }
}