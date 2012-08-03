package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPlace;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceViewImpl extends Composite implements JobAnnounceView {

  interface JobAnnounceViewImplUiBinder extends UiBinder<Widget, JobAnnounceViewImpl> {}
  private static JobAnnounceViewImplUiBinder uiBinder = GWT.create(JobAnnounceViewImplUiBinder.class);

  interface Driver extends RequestFactoryEditorDriver<JobProxy, JobEditor> {}
  private final Driver driver = GWT.create(Driver.class);

  private JobAnnouncePresenter presenter;

  @UiField
  JobEditor jobEditor;

  @UiField
  Button cancel;

  @UiField
  Button announce;

  @UiField
  AlertBlock alert;
  private PlaceController placeController;

  @Inject
  public JobAnnounceViewImpl(PlaceController placeController) {

    this.placeController = placeController;

    initWidget(uiBinder.createAndBindUi(this));

    driver.initialize(jobEditor);
  }

  /**
   * Set presenter that will drive the view
   *
   * @param presenter a presenter
   */
  public void setPresenter(JobAnnouncePresenter presenter) {

    this.presenter = presenter;
  }

  @UiHandler("announce")
  public void onButtonAnnounceClick(ClickEvent event) {

    if (Window.confirm("Announce this job?")) {
      driver.flush();
      presenter.announceJob();
    }
  }

  @UiHandler("cancel")
  public void onButtonCancelClick(ClickEvent event) {
    placeController.goTo(new JobSearchPlace());
  }

  /**
   * Go to JobsReviewPlace
   */
  public void goToReviewJobsPlace() {
    placeController.goTo(new ReviewJobsPlace());
  }

  /**
   * Edit (load the view's Driver) with given RequestContext and JobProxy
   *
   * @param context - RequestContext through which the proxy object will be sent to the server
   * @param proxy - the proxy that will be sent
   */
  public void edit(JobExRequestFactory.JobRequestContext context, JobProxy proxy) {
    driver.edit(proxy, context);
  }

  /**
   * Show occurred constraint violations when announcing new Job
   *
   * @param listOfConstraintViolations - list of constraint violations
   */
  public void showConstraintViolations(List<String> listOfConstraintViolations) {

    StringBuilder builder = new StringBuilder();

    for (String listOfError : listOfConstraintViolations) {
      builder.append(listOfError).append(" ");
    }

    alert.setText(builder.toString());
    alert.setVisible(true);
  }

  /**
   * Reset the view's widgets
   */
  public void reset() {

    alert.setVisible(false);

    jobEditor.selectCategory.setSelectedIndex(0);
    jobEditor.selectLocation.setSelectedIndex(0);
  }
}