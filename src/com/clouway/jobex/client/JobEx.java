package com.clouway.jobex.client;


import com.clouway.jobex.client.gin.JobExGinjector;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.navigation.MenuPlacesMapper;
import com.clouway.jobex.client.navigation.NavigationMenu;
import com.clouway.jobex.client.navigation.SecuredActivityMapper;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserAuthorizationEvent;
import com.clouway.jobex.client.security.UserAuthorizationEventHandler;
import com.clouway.jobex.client.security.UserAuthorizedEvent;
import com.clouway.jobex.client.useraccess.logout.LogoutEvent;
import com.clouway.jobex.shared.Permissions;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class JobEx implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    SimplePanel display = new SimplePanel();

    Place place = new JobSearchPlace();

    final JobExGinjector injector = GWT.create(JobExGinjector.class);

    EventBus eventBus = injector.injectEventBus();

    eventBus.addHandler(UserAuthorizedEvent.TYPE, injector.userAuthorizedEventhandler());

    final PlaceController placeController = injector.injectPlaceController();

    eventBus.addHandler(UserAuthorizationEvent.TYPE, new UserAuthorizationEventHandler() {
      @Override
      public void onUserAuthorization(UserAuthorizationEvent event) {
        placeController.goTo(new AuthorizationPlace());
      }
    });

    eventBus.addHandler(LogoutEvent.TYPE, injector.logoutEventHandler());



    SecuredActivityMapper mapper = injector.injectActivityMapper();

    ActivityManager activityManager = new ActivityManager(mapper, eventBus);

    activityManager.setDisplay(display);

    JobExPlaceHistoryMapper placeHistoryMapper = GWT.create(JobExPlaceHistoryMapper.class);

    PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);

    placeHistoryHandler.register(placeController, eventBus, place);


    RootPanel.get("container").add(display);

    MenuPlacesMapper menuPlacesMapper = injector.menuItemMapper();

    NavigationMenu navigationMenu = new NavigationMenu(placeController, menuPlacesMapper);

    List<String> userPermissions = new ArrayList<String>();

    userPermissions.add(Permissions.HOME);

    userPermissions.add(Permissions.NEW_REGISTRATION);

    userPermissions.add(Permissions.LOG_IN);

    navigationMenu.setPermittedPlaces(userPermissions);

    navigationMenu.refresh();

    placeHistoryHandler.handleCurrentHistory();

  }
}