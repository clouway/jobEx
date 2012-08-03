package com.clouway.jobex.client.navigation;

import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Navbar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NavigationMenu extends Composite implements IsWidget, NavigationMenuController {


  interface NavigationMenuUiBinder extends UiBinder<HTMLPanel, NavigationMenu> {

  }

  private static NavigationMenuUiBinder ourUiBinder = GWT.create(NavigationMenuUiBinder.class);

  @Inject
  PlaceController placeController;


  private final MenuPlacesMapper menuPlacesMapper;

  @UiField
  HTMLPanel container;

  @UiField
  Navbar navBar;

  @UiField
  Brand usernameLabel;

  @UiField
  Nav nav;

  @Inject
  public NavigationMenu(final PlaceController placeController, MenuPlacesMapper menuPlacesMapper) {

    this.placeController = placeController;

    this.menuPlacesMapper = menuPlacesMapper;

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

  }


  public void createLinkItems(Map<String, Place> linkPlaceMap) {

  }

  private NavLink createNavLink(String placeName, final Place place) {

    NavLink navLink = new NavLink(placeName);

    navLink.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        placeController.goTo(place);
      }
    });
    return navLink;
  }

  @Override
  public void setUsernameLabel(String username) {
    usernameLabel.setText("user: " + username);
  }

  @Override
  public void refresh() {
    RootPanel.get("navigation").clear();
    RootPanel.get("navigation").add(this);
  }

  @Override
  public void setPermittedPlaces(List<String> permissions) {

    clearNav();
    for (String permission : permissions) {
      Place permittedPlace = menuPlacesMapper.getPlace(permission);
      if (permittedPlace != null) {
        nav.add(createNavLink(permission, permittedPlace));
      }
    }

    navBar.add(nav);

    container.add(navBar);

  }

  private void clearNav() {
    int index = nav.getWidgetCount();
    if (index > 0) {
      for (int i = index-1; i >= 0; i--) {
        nav.remove(i);
      }
    }
  }


}