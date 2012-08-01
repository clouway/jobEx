package com.clouway.jobex.client.security;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.Place;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserPermissions {

  List<SecuredPlace> getPermittedPlace(String userType);

  public void permitPlace(Place place, String userType);

}
