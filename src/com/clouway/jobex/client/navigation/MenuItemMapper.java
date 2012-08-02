package com.clouway.jobex.client.navigation;

import com.google.gwt.place.shared.Place;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface MenuItemMapper {

  public Place getPlace(String placeName);

  public void addPlace(String placeName, Place place);
}
