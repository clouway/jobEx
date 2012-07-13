package com.clouway.jobex.client.jobsearch;

import com.clouway.jobex.client.JobProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchViewImpl extends Composite implements JobSearchView{
  private Presenter presenter;

  interface JobSearchViewImplUiBinder extends UiBinder<HTMLPanel, JobSearchViewImpl> {
  }

  private static JobSearchViewImplUiBinder ourUiBinder = GWT.create(JobSearchViewImplUiBinder.class);

  private ListDataProvider<JobProxy> dataProvider;
  @UiField
  CellTable<JobProxy> jobsCellTable;
  @UiField
  TextBox locationValue;
  @UiField
  TextBox positionValue;
  @UiField
  Button searchButton;
  Column<JobProxy, String> id;
  Column<JobProxy, String> location;
  Column<JobProxy, String> category;
  Column<JobProxy, String> apply;

  public JobSearchViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    dataProvider = new ListDataProvider<JobProxy>();

    dataProvider.addDataDisplay(jobsCellTable);

    id = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getId());
      }
    };

     location = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return object.getLocation();
      }
    };

    category = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getCategory());
      }
    };

    apply = new Column<JobProxy, String>(new ButtonCell()) {
      @Override
      public String getValue(JobProxy object) {
        return "APPLY";
      }
    };


    apply.setFieldUpdater(new FieldUpdater<JobProxy, String>() {
      public void update(int index, JobProxy object, String value) {
        // Value is the button value.  Object is the row object.
  //       new SimpleEventBus().fireEvent();

//        EventBus eventBus;
//        eventBus.fireEvent(new );
      }
    });

    jobsCellTable.addColumn(id, "JobId");
    jobsCellTable.addColumn(location, "Location");
    jobsCellTable.addColumn(category, "Category");
    jobsCellTable.addColumn(apply, "Apply");

//    productCellTable.setColumnWidth(name, 200, Style.Unit.PCT);

  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void fillTableWithJobAds(List<JobProxy> listOfJobObjects) {
//    List<JobProxy> list = dataProvider.getList();
//    for (JobProxy job : listOfJobObjects) {
//      list.add(job);
//    }
    jobsCellTable.setVisibleRange(0, listOfJobObjects.size());
    jobsCellTable.setRowData(0, listOfJobObjects);
//    jobsCellTable.addColumn();
  }

  @Override
  public String getCategoryValue() {
    return positionValue.getText();  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getLocationValue() {
    return locationValue.getText();  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void disableSearchButton() {
    searchButton.setEnabled(false);
  }

  @Override
  public void enableSearchButton() {
    searchButton.setEnabled(true);
  }

//  @Override
//  public void clearTable(){
//    for(int i=jobsCellTable.getRowCount()-1; i>=0; i--){
//      jobsCellTable.getRowElement(i).deleteCell(2);
//      jobsCellTable.getRowElement(i).deleteCell(1);
//      jobsCellTable.getRowElement(i).deleteCell(0);
//    }
//
//  }


  @UiHandler("searchButton")
  public void onSearchButtonClicked(ClickEvent event){
    presenter.onSearchButtonClicked();
  }
}