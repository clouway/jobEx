package com.clouway.jobex.client.security;

import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.navigation.NavigationMenuController;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthorizedEventHandlerImpl implements UserAuthorizedEventHandler {

  private final NavigationMenuController navigationMenuController;

  private final UserCredentialsLocalStorage securityProvider;

  private final UserPermittedActions permittedActions;

  private final JobSearchView view;


  @Inject
  public UserAuthorizedEventHandlerImpl(NavigationMenuController navigationMenuController,
                                        UserCredentialsLocalStorage securityProvider,
                                        UserPermittedActions permittedActions,JobSearchView view) {

    this.navigationMenuController = navigationMenuController;

    this.securityProvider = securityProvider;

    this.permittedActions = permittedActions;
    this.view = view;
  }

  @Override
  public void onUserAuthorized(UserAuthorizedEvent event) {

    List<String> actions = event.getPermittedActions();

    navigationMenuController.setPermittedPlaces(event.getPermittedActions());

    navigationMenuController.setUsernameLabel(event.getEmail());

    navigationMenuController.refresh();

    permittedActions.setPermittedActions(actions);

    view.constructApplyButton();

    securityProvider.setUserCredentials(event.getEmail(), event.getSID());
  }
}
