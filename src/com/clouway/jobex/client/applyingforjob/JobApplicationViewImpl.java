package com.clouway.jobex.client.applyingforjob;


import com.clouway.jobex.shared.CVProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationViewImpl extends Composite implements JobApplicationView {


  interface JobApplicationViewImplUiBinder extends UiBinder<HTMLPanel, JobApplicationViewImpl> {

  }

  private static JobApplicationViewImplUiBinder ourUiBinder = GWT.create(JobApplicationViewImplUiBinder.class);

  @UiField(provided = true)
  CellTable<CVProxy> cVCellTable;

  @UiField
  Label errors;

  @UiField
  Label messages;

  @Inject
  PlaceController controller;

  private JobApplicationPresenter presenter;

  private Long jobId;

  private SingleSelectionModel<CVProxy> selectionModel = new SingleSelectionModel<CVProxy>();


  public JobApplicationViewImpl() {

    cVCellTable = new CellTable<CVProxy>();

    cVCellTable.addColumn(new TextColumn<CVProxy>() {
      @Override
      public String getValue(CVProxy object) {
        return String.valueOf(object.getId());
      }
    }, "Id");

    cVCellTable.addColumn(new TextColumn<CVProxy>() {
      @Override
      public String getValue(CVProxy object) {
        return object.getName();
      }
    }, "name");

    cVCellTable.addColumn(new TextColumn<CVProxy>() {
      @Override
      public String getValue(CVProxy object) {
        return object.getEmail();
      }
    }, "email");

    cVCellTable.addColumn(new TextColumn<CVProxy>() {
      @Override
      public String getValue(CVProxy object) {
        return object.getPhoneNumber();
      }
    }, "phone number");

    cVCellTable.addColumn(new TextColumn<CVProxy>() {
      @Override
      public String getValue(CVProxy object) {
        return object.getSkills();
      }
    }, "skills:");




    Column<CVProxy, String> editButton = new Column<CVProxy, String>(new ButtonCell()) {
      @Override
      public String getValue(CVProxy object) {
        return "edit";
      }
    };

    editButton.setFieldUpdater(new FieldUpdater<CVProxy, String>() {
      @Override
      public void update(int index, CVProxy object, String value) {
        controller.goTo(new EditCVPlace(object.getId()));
      }
    });

    cVCellTable.addColumn(editButton);

    cVCellTable.setSelectionModel(selectionModel);

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);
  }


  @Override
  public void notifyUserOfSuccessfulAppliance() {
    Window.alert("You have successfully applied for A Job. ");
  }


  @Override
  public void showErrors(List<String> errorsList) {
    StringBuilder builder = new StringBuilder();
    for (String string : errorsList) {
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
    controller.goTo(new CreateCvPlace());
  }

  @Override
  public void setJobId(Long id) {
    jobId = id;

    Column<CVProxy, String> selectButton = new Column<CVProxy, String>(new ButtonCell()) {
      @Override
      public String getValue(final CVProxy object) {
        return "select";
      }
    };

    selectButton.setFieldUpdater(new FieldUpdater<CVProxy, String>() {
      @Override
      public void update(int index, CVProxy cvProxy, String value) {
        presenter.applyForJob(jobId, cvProxy.getId(), cvProxy.getEmail());
      }
    });

    cVCellTable.addColumn(selectButton);
  }
}