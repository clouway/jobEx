package com.clouway.jobex.client.job.jobsearch;

import com.clouway.jobex.client.cv.PreviewCvPlace;
import com.clouway.jobex.client.jobsreview.ButtonCellFactory;
import com.clouway.jobex.shared.JobProxy;
import com.github.gwtbootstrap.client.ui.Button;
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchViewImpl extends Composite implements JobSearchView {

  private JobSearchPresenter presenter;

  private final PlaceController placeController;

  private final ButtonCellFactory factory;

  interface JobSearchViewImplUiBinder extends UiBinder<HTMLPanel, JobSearchViewImpl> {
  }

  private static JobSearchViewImplUiBinder ourUiBinder = GWT.create(JobSearchViewImplUiBinder.class);

  private ListDataProvider<JobProxy> dataProvider;

  @UiField
  CellTable<JobProxy> jobsCellTable;

  @UiField
  ListBox locationValue;

  @UiField
  ListBox categoryValue;

  @UiField
  Button searchButton;


  @Inject
  EventBus eventBus;

  boolean alreadyConstructed = false;


  @Inject
  public JobSearchViewImpl(final PlaceController placeController, ButtonCellFactory factory) {
    this.placeController = placeController;

    this.factory = factory;

    initWidget(ourUiBinder.createAndBindUi(this));

    locationValue.addItem("all locations");

    locationValue.addItem("Burgas");

    locationValue.addItem("Plovdiv");

    locationValue.addItem("Sofia");

    locationValue.addItem("Varna");

    locationValue.addItem("Veliko Tarnovo");

    categoryValue.addItem("Banking");

    categoryValue.addItem("Engineering");

    categoryValue.addItem("IT");

    categoryValue.addItem("Franchise");

    categoryValue.addItem("Marketing");

    dataProvider = new ListDataProvider<JobProxy>();

    dataProvider.addDataDisplay(jobsCellTable);

    Column<JobProxy, String> id = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getId());
      }
    };

    jobsCellTable.setPageSize(2);

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


    Column<JobProxy, String> position = new Column<JobProxy, String>(new TextCell()) {
      @Override
      public String getValue(JobProxy object) {
        return String.valueOf(object.getPosition());
      }
    };


    jobsCellTable.addColumn(id, "JobId");
    jobsCellTable.addColumn(location, "Location");
    jobsCellTable.addColumn(category, "Category");
    jobsCellTable.addColumn(position, "Position");


    jobsCellTable.setColumnWidth(id, "150");
    jobsCellTable.setColumnWidth(location, "200");
    jobsCellTable.setColumnWidth(category, "200");
    jobsCellTable.setColumnWidth(position, "200");

  }

  @Override
  public void setPresenter(JobSearchPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void constructApplyButton() {

    if (!alreadyConstructed) {
      ButtonCellFactory.MyButtonCell myButtonCell = factory.createButtonCell();

      Column<JobProxy, String> apply = new Column<JobProxy, String>(myButtonCell) {
        @Override
        public String getValue(JobProxy object) {
          return "APPLY";
        }
      };

      apply.setFieldUpdater(new FieldUpdater<JobProxy, String>() {
        public void update(int index, final JobProxy object, String value) {
          placeController.goTo(new PreviewCvPlace(object.getId()));
        }
      });
      jobsCellTable.addColumn(apply, "Apply");
      jobsCellTable.setColumnWidth(apply, "100");

    }
    jobsCellTable.redraw();

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
    return categoryValue.getValue(categoryValue.getSelectedIndex());
  }

  /**
   * @return the location of a job from a textBox
   */
  @Override
  public String getLocationValue() {
    return locationValue.getValue(locationValue.getSelectedIndex());
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

  @UiHandler("searchButton")
  public void onSearchButtonClicked(ClickEvent event) {
    presenter.onSearchButtonClicked();
  }

}