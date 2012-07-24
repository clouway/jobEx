package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompanyNavigationMenu extends Composite {

  interface CompanyNavigationMenuUiBinder extends UiBinder<HTMLPanel, CompanyNavigationMenu> {
  }

  private static CompanyNavigationMenuUiBinder ourUiBinder = GWT.create(CompanyNavigationMenuUiBinder.class);

  @Inject
  PlaceController placeController;

  @UiField
  NavLink home;

  @UiField
  NavLink announceJob;

  @UiField
  NavLink jobReview;


  @Inject
  public CompanyNavigationMenu(final PlaceController placeController) {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    this.placeController = placeController;



    home.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new JobSearchPlace());
      }
    });

    announceJob.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new JobAnnouncePlace());
      }
    });

    jobReview.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new ReviewJobsPlace());
      }
    });

    initWidget(rootElement);
  }



}