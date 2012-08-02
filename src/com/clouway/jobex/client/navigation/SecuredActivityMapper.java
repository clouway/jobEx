package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.name.Named;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredActivityMapper implements ActivityMapper {

  private Map<Class<? extends Place>, ActivityPlaceMetadata> activityPlaceMap;

  private final UserCredentialsLocalStorage credentialsLocalStorage;

  private final Map<String, Class<? extends Place>> allPlacesMap;

  private List<Class<? extends Place>> classes = new ArrayList<Class<? extends Place>>();

  @Inject

  public SecuredActivityMapper(Map<Class<? extends Place>, ActivityPlaceMetadata> activityPlaceMap,
                               UserCredentialsLocalStorage credentialsLocalStorage,
                               @Named("allPlacesMap") Map<String, Class<? extends Place>> allPlacesMap) {
    this.activityPlaceMap = activityPlaceMap;
    this.credentialsLocalStorage = credentialsLocalStorage;
    this.allPlacesMap = allPlacesMap;
  }

  @Override
  public Activity getActivity(Place place) {

    ActivityPlaceMetadata activity;
    if (place instanceof SecuredPlace) {
      if (credentialsLocalStorage.isAuthorized()) {
        if (isPermitted(place.getClass())) {
          activity = activityPlaceMap.get(place.getClass());
        } else {
          return new PageNotFoundActivity();
        }
      } else {
        return activityPlaceMap.get(AuthorizationPlace.class).getActivity(new AuthorizationPlace());
      }
    } else {
      activity = activityPlaceMap.get(place.getClass());
    }
    if (activity == null) {
      return new PageNotFoundActivity();
    }
    return activity.getActivity(place);
  }

  public void setPermissions(List<String> permissions) {
    for (String permission : permissions) {
      classes.add(allPlacesMap.get(permission));
    }
  }


  private boolean isPermitted(Class<? extends Place> aClass) {
    for (Class<? extends Place> place : classes) {
      if (aClass.equals(place)) {
        return true;
      }
    }
    return false;
  }
}
