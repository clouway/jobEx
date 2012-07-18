package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.applyingforjob.CreateCvPlace;
import com.clouway.jobex.client.applyingforjob.PreviewCvPlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
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
public class NavigationMenu extends Composite {
  interface NavigationMenuUiBinder extends UiBinder<HTMLPanel, NavigationMenu> {
  }

  private static NavigationMenuUiBinder ourUiBinder = GWT.create(NavigationMenuUiBinder.class);

  @Inject
  PlaceController placeController;

  @UiField
  NavLink home;

  @UiField
  NavLink newCV;

  @UiField
  NavLink cvList;

  @UiField
  NavLink announceJob;

  public NavigationMenu() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    home.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new JobSearchPlace());
      }
    });
    newCV.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new CreateCvPlace());
      }
    });

    cvList.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new PreviewCvPlace());
      }
    });
    announceJob.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(new JobAnnouncePlace());
      }
    });
    initWidget(rootElement);

  }
}