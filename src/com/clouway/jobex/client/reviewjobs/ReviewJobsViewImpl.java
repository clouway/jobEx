package com.clouway.jobex.client.reviewjobs;

import com.clouway.jobex.client.submittedcvs.SubmittedCVsPlace;
import com.clouway.jobex.client.navigation.NavigationMenu;
import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import java.util.Date;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsViewImpl extends Composite implements ReviewJobsView {

  interface JobsReviewViewImplUiBinder extends UiBinder<Widget, ReviewJobsViewImpl> {
  }

  private static JobsReviewViewImplUiBinder uiBinder = GWT.create(JobsReviewViewImplUiBinder.class);

  private ReviewJobsPresenter reviewJobsPresenter;

  @UiField
  CellTable<JobProxy> announcedJobsTable;

  @UiField(provided = true)
    NavigationMenu menu;
  //private CompanyNameProvider companyNameProvider;

  @Inject
  private EventBus eventBus;

  private PlaceController placeController;

  @Inject
  public ReviewJobsViewImpl(NavigationMenu menu, final PlaceController placeController) {

    this.menu = menu;

    //this.companyNameProvider = companyNameProvider;

    initWidget(uiBinder.createAndBindUi(this));

    announcedJobsTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

    TextColumn<JobProxy> jobIdColumn = new TextColumn<JobProxy>() {
      public String getValue(JobProxy jobProxy) {
        return jobProxy.getId().toString();
      }
    };
    announcedJobsTable.addColumn(jobIdColumn, "ID.");

    TextColumn<JobProxy> companyColumn = new TextColumn<JobProxy>() {
      public String getValue(JobProxy jobProxy) {
        return jobProxy.getCompany();
      }
    };
    announcedJobsTable.addColumn(companyColumn, "Company");

    TextColumn<JobProxy> positionColumn = new TextColumn<JobProxy>() {
      public String getValue(JobProxy jobProxy) {
        return jobProxy.getPosition();
      }
    };
    announcedJobsTable.addColumn(positionColumn, "Position");

    TextColumn<JobProxy> categoryColumn = new TextColumn<JobProxy>() {
      public String getValue(JobProxy jobProxy) {
        return jobProxy.getCategory();
      }
    };
    announcedJobsTable.addColumn(categoryColumn, "Category");

    TextColumn<JobProxy> locationColumn = new TextColumn<JobProxy>() {
      public String getValue(JobProxy jobProxy) {
        return jobProxy.getLocation();
      }
    };
    announcedJobsTable.addColumn(locationColumn, "Location");

    DateCell dateCell = new DateCell();
    Column<JobProxy, Date> expirationDateColumn = new Column<JobProxy, Date>(dateCell) {
      public Date getValue(JobProxy jobProxy) {
        return jobProxy.getExpirationDate();
      }
    };
    announcedJobsTable.addColumn(expirationDateColumn, "Expiration Date");

    Column<JobProxy, String> deleteAnnouncedJob = new Column<JobProxy, String>(new ButtonCell()) {
      public String getValue(JobProxy object) {
        return "Delete";
      }
    };
    announcedJobsTable.addColumn(deleteAnnouncedJob);

    Column<JobProxy, String> previewCVs = new Column<JobProxy, String>(new ButtonCell()) {
      public String getValue(JobProxy object) {
        return "Preview CVs";
      }
    };
    announcedJobsTable.addColumn(previewCVs);

    previewCVs.setFieldUpdater(new FieldUpdater<JobProxy, String>(){
      public void update(int index, JobProxy jobProxy, String value) {

        placeController.goTo(new SubmittedCVsPlace(jobProxy.getId()));
      }
    });

    deleteAnnouncedJob.setFieldUpdater(new FieldUpdater<JobProxy, String>() {
      public void update(int index, JobProxy jobProxy, String value) {
        //reviewJobsPresenter.deleteAnnouncedJob(jobProxy.getId(), companyNameProvider.getCompanyName());
      }
    });
  }

  /**
   * Show announced jobs made be a company
   *
   * @param announcedJobs - returned list of announced jobs
   */
  public void showAnnouncedJobs(List<JobProxy> announcedJobs) {

    announcedJobsTable.setVisibleRange(0, announcedJobs.size());
    announcedJobsTable.setRowData(0, announcedJobs);
  }


  public void updateAnnounceJobs(List<JobProxy> jobProxyList) {

    announcedJobsTable.setVisibleRange(0, jobProxyList.size());
    announcedJobsTable.setRowData(0, jobProxyList);
  }

  /**
   * Notifies that the company has not announced any jobs
   */
  public void showNoAnnouncedJobsNotification() {
    Window.alert("You have not announced any job!");
  }

  /**
   * Set presenter which will drive the view
   *
   * @param reviewJobsPresenter - a reviewJobsPresenter
   */
  public void setPresenter(ReviewJobsPresenter reviewJobsPresenter) {
    this.reviewJobsPresenter = reviewJobsPresenter;
  }

  public boolean isConfirmed() {
    return Window.confirm("Delete announced job");
  }
}