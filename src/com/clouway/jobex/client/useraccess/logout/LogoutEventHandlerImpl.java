package com.clouway.jobex.client.useraccess.logout;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.navigation.NavigationMenuController;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.Permissions;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LogoutEventHandlerImpl extends AbstractActivity implements LogoutEventHandler {

  private JobExRequestFactory factory;
  private UserCredentialsLocalStorage userCredentials;
  private PlaceController placeController;
  private final NavigationMenuController menu;

  @Inject
  public LogoutEventHandlerImpl(JobExRequestFactory factory, UserCredentialsLocalStorage userCredentials, PlaceController placeController, NavigationMenuController menu){

    this.factory = factory;
    this.userCredentials = userCredentials;
    this.placeController = placeController;
    this.menu = menu;
  }

  @Override
  public void onLogout(LogoutEvent event) {
    JobExRequestFactory.AuthorizationRequestContext logoutRequestContext = factory.authorizationContext();

    logoutRequestContext.logout(event.getLoggedEmail()).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {

        userCredentials.deleteCookies();
        List<String> stringList = new ArrayList<String>();
        stringList.add(Permissions.HOME);
        stringList.add(Permissions.LOG_IN);
        stringList.add(Permissions.NEW_REGISTRATION);


        menu.setPermittedPlaces(stringList);
        menu.refresh();
        placeController.goTo(new JobSearchPlace());
      }
    }).fire();
  }

  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    eventBus.fireEvent(new LogoutEvent(userCredentials.getUsername()));
  }
}
