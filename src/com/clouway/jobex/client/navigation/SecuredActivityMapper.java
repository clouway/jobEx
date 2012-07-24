package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.SecurityProvider;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredActivityMapper implements ActivityMapper {

  private Map<Class<? extends Place>, ActivityPlaceMetadata> activityPlaceMap;

  private final SecurityProvider provider;

  @Inject
  public SecuredActivityMapper(Map<Class<? extends Place>, ActivityPlaceMetadata> activityPlaceMap, SecurityProvider provider) {
    this.activityPlaceMap = activityPlaceMap;
    this.provider = provider;
  }

  @Override
  public Activity getActivity(Place place) {

    ActivityPlaceMetadata activity = null;

    if (place instanceof SecuredPlace) {
      if (provider.isAuthorized()) {
        activity = activityPlaceMap.get(place.getClass());
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

}
