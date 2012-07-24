package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.navigation.CompanyNavigationMenu;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
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

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceViewImpl extends Composite implements JobAnnounceView {

  interface JobAnnounceViewImplUiBinder extends UiBinder<Widget, JobAnnounceViewImpl> {
  }

  private static JobAnnounceViewImplUiBinder uiBinder = GWT.create(JobAnnounceViewImplUiBinder.class);

  interface Driver extends RequestFactoryEditorDriver<JobProxy, JobEditor> {
  }

  private final Driver driver = GWT.create(Driver.class);

  private JobAnnouncePresenter presenter;

  @UiField
  JobEditor jobEditor;

  @UiField
  Button cancel;

  @UiField
  Button announce;

  @UiField(provided = true)
  CompanyNavigationMenu navigation;

  private PlaceController placeController;

  @Inject
  public JobAnnounceViewImpl(PlaceController placeController,CompanyNavigationMenu navigation) {

    this.placeController = placeController;

    this.navigation = navigation;

    initWidget(uiBinder.createAndBindUi(this));

    driver.initialize(jobEditor);
  }

  public void setPresenter(JobAnnouncePresenter presenter) {

    this.presenter = presenter;
  }

  @UiHandler("announce")
  public void onButtonAnnounceClick(ClickEvent event) {

    if (presenter != null) {
      driver.flush();
      presenter.announceJob();
    }
  }

  @UiHandler("cancel")
  public void onButtonCancelClick(ClickEvent event) {
    placeController.goTo(new JobSearchPlace());
  }

  /**
   * Go to SearchPlace after announcing new job
   */
  public void goToSearchPlace() {
    Window.alert("Job was announced!");
    placeController.goTo(new JobSearchPlace());
  }

  @Override
  public void edit(JobExRequestFactory.JobRequestContext context, JobProxy proxy) {
    driver.edit(proxy, context);

  }
}