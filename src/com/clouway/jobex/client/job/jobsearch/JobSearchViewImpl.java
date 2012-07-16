package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.client.applyingforjob.ApplyForJobEvent;
import com.clouway.jobex.client.applyingforjob.SelectCvPlace;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchViewImpl extends Composite implements JobSearchView {
  private JobSearchPresenter presenter;

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


  @Inject
  EventBus eventBus;


  @Inject
  public JobSearchViewImpl(final PlaceController placeController) {
    initWidget(ourUiBinder.createAndBindUi(this));
    dataProvider = new ListDataProvider<JobProxy>();

    dataProvider.addDataDisplay(jobsCellTable);

    Column<JobProxy, String> id = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getId());
      }
    };

    Column<JobProxy, String> location = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return object.getLocation();
      }
    };

    Column<JobProxy, String> category = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getCategory());
      }
    };

    Column<JobProxy, String> apply = new Column<JobProxy, String>(new ButtonCell()) {
      @Override
      public String getValue(JobProxy object) {
        return "APPLY";
      }
    };

    Column<JobProxy, String> position = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getPosition());
      }
    };



    apply.setFieldUpdater(new FieldUpdater<JobProxy, String>() {
      public void update(int index, JobProxy object, String value) {

        placeController.goTo(new SelectCvPlace());
        eventBus.fireEvent(new ApplyForJobEvent(object.getId()));

      }
    });

    jobsCellTable.addColumn(id, "JobId");
    jobsCellTable.addColumn(location, "Location");
    jobsCellTable.addColumn(category, "Category");
    jobsCellTable.addColumn(position, "Position");
    jobsCellTable.addColumn(apply, "Apply");


  }

  @Override
  public void setPresenter(JobSearchPresenter presenter) {
    this.presenter = presenter;
  }


  /**
   * Show list of job ads in a cell table
   *
   * @param listOfJobObjects the list that is going to be shown
   */
  @Override
  public void showJobAds(List<JobProxy> listOfJobObjects) {
    jobsCellTable.setVisibleRange(0, listOfJobObjects.size());
    jobsCellTable.setRowData(0, listOfJobObjects);
  }

  /**
   * @return the category of a job from a textBox
   */
  @Override
  public String getCategoryValue() {
    return positionValue.getText();
  }

  /**
   * @return the location of a job from a textBox
   */
  @Override
  public String getLocationValue() {
    return locationValue.getText();
  }

  /**
   * Disable the search button
   */
  @Override
  public void disableSearch() {
    searchButton.setEnabled(false);
  }

  /**
   * Enable the search button
   */
  @Override
  public void enableSearch() {
    searchButton.setEnabled(true);
  }

  @Override
  public void noSearchCriteriaMessage() {
    Window.alert("Please enter search criteria (location/category)");
  }

  @UiHandler("searchButton")
  public void onSearchButtonClicked(ClickEvent event) {
    presenter.onSearchButtonClicked();
  }
}