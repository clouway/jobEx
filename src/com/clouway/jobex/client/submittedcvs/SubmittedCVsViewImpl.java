package com.clouway.jobex.client.submittedcvs;

import com.clouway.jobex.client.reviewjobs.ReviewJobsPlace;
import com.clouway.jobex.shared.CVProxy;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SubmittedCVsViewImpl extends Composite implements SubmittedCVsView {

  private Long jobId;

  interface ReviewCVViewImplUiBinder extends UiBinder<Widget, SubmittedCVsViewImpl> {}
  private static ReviewCVViewImplUiBinder uiBinder = GWT.create(ReviewCVViewImplUiBinder.class);

  private SubmittedCVsPresenterImpl presenter;

  @UiField
  CellTable cvTable;

  private PlaceController placeController;

  @Inject
  public SubmittedCVsViewImpl(PlaceController placeController) {

    this.placeController = placeController;

    initWidget(uiBinder.createAndBindUi(this));

    TextColumn<CVProxy> cvId = new TextColumn<CVProxy>() {
      public String getValue(CVProxy cvProxy) {
        return cvProxy.getId().toString();
      }
    };
    cvTable.addColumn(cvId, "ID.");

    TextColumn<CVProxy> name = new TextColumn<CVProxy>() {
      public String getValue(CVProxy cvProxy) {
        return cvProxy.getName();
      }
    };
    cvTable.addColumn(name, "Name");

    final TextColumn<CVProxy> email = new TextColumn<CVProxy>() {
      public String getValue(CVProxy cvProxy) {
        return cvProxy.getEmail();
      }
    };
    cvTable.addColumn(email, "Email");

    TextColumn<CVProxy> phoneNumber = new TextColumn<CVProxy>() {
      public String getValue(CVProxy cvProxy) {
        return cvProxy.getPhoneNumber();
      }
    };
    cvTable.addColumn(phoneNumber, "Phone Number");

    TextColumn<CVProxy> skills = new TextColumn<CVProxy>() {
      public String getValue(CVProxy cvProxy) {
        return cvProxy.getSkills();
      }
    };
    cvTable.addColumn(skills, "Skills");

    Column<CVProxy, String> approveCV = new Column<CVProxy, String>(new ButtonCell()) {
      public String getValue(CVProxy cvProxy) {
        return "Approve CV";
      }
    };
    cvTable.addColumn(approveCV);

    approveCV.setFieldUpdater(new FieldUpdater<CVProxy, String>() {
      public void update(int index, CVProxy cvProxy, String value) {

        if (Window.confirm("Approve selected CV?")) {
          presenter.sendEmailApproval(jobId, email.getValue(cvProxy));
        }
      }
    });
  }

  public void showSubmittedCVs(List<CVProxy> submittedCVs) {
    cvTable.setVisibleRangeAndClearData(new Range(0, submittedCVs.size()), true);
    cvTable.setRowData(0, submittedCVs);
  }

  public void showNoSubmittedCVsNotification() {
    Window.alert("No submitted CVs");
  }

  public void goToReviewJobPlace() {
    placeController.goTo(new ReviewJobsPlace());
  }

  /**
   * Show notification that email was sent
   */
  public void showSentEmailNotification() {
    Window.alert("Email was sent!");
  }

  public void setPresenter(SubmittedCVsPresenterImpl reviewCVPresenter) {
    this.presenter = reviewCVPresenter;
  }

  public void setJobId(Long jobId) {
    this.jobId = jobId;
  }
}