package com.clouway.jobex.client.security;

import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.navigation.MenuItemMapper;
import com.clouway.jobex.client.navigation.NavigationMenuController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthorizedEventHandlerImplTest {

  UserAuthorizedEventHandlerImpl handler;


  @Mock
  private NavigationMenuController navigationMenuController;

  @Mock
  UserCredentialsLocalStorage userCredentialsLocalStorage;

  @Mock
  UserPermittedActions userPermittedActions;

  @Mock
  SecuredActionMapper securedActionMapper;

  @Mock
  MenuItemMapper menuItemMapper;

  @Mock
  JobSearchView view;

  String sid = "sid";

  String email = "email";

  List<String> permission = new ArrayList<String>();

  UserAuthorizedEvent event = new UserAuthorizedEvent(sid, email, permission);

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new UserAuthorizedEventHandlerImpl(navigationMenuController, userCredentialsLocalStorage, userPermittedActions,view);

  }

  @Test
  public void setsUsersPermittedAction() {

    handler.onUserAuthorized(event);

    verify(userPermittedActions).setPermittedActions(permission);
  }


  @Test
  public void setUsersPermittedMenuItemsAndUsernameLabel() {

    permission.add("SomePermissionName");

    handler.onUserAuthorized(event);

    verify(navigationMenuController).setPermittedPlaces(permission);

    verify(navigationMenuController).refresh();

    verify(navigationMenuController).setUsernameLabel(email);

  }


  @Test
  public void setUserCredentials() {
    handler.onUserAuthorized(event);
    verify(userCredentialsLocalStorage).setUserCredentials(email, sid);
  }


  class TestAction implements SecuredAction {

    @Override
    public void execute() {

    }

    @Override
    public void onConditionsBreak() {

    }
  }


}
