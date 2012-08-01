package com.clouway.jobex.client.navigation;

import com.google.gwt.place.shared.Place;

import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface NavigationMenuController {

  public void createLinkItems(Map<String, Place> linkPlaceMap);

  public void setUsernameLabel(String username);

  public void refresh();

  void setPermittedPlaces(List<String> permission);
}
