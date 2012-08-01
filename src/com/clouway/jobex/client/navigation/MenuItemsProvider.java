package com.clouway.jobex.client.navigation;

import com.google.gwt.place.shared.Place;

import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface MenuItemsProvider {

  public Map<String, Place> getUserItems();

  public Map<String, Place> getCompanyItems();

}
