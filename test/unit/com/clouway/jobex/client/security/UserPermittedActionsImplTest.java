package com.clouway.jobex.client.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserPermittedActionsImplTest {


  UserPermittedActionsImpl permittedAction;

  @Mock
  SecuredActionMapper mapper;

  List<Class<? extends SecuredAction>> list = new ArrayList<Class<? extends SecuredAction>>();

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    permittedAction = new UserPermittedActionsImpl(mapper);

  }


  @Test(expected = ActionHasBeenAddedTwinceException.class)
  public void throwsActionHasBeenAddedTwiceException() {
    String actionName = "name";
    List<String> strings = new ArrayList<String>();
    strings.add(actionName);
    strings.add(actionName);
    Class aClass = MockAction.class;
    when(mapper.getSecuredAction(actionName)).thenReturn(aClass);
    permittedAction.setPermittedActions(strings);

  }


  @Test
  public void returnsTrueIfActionHasBeenAdded() {
    final String actionName = "name";

    Class aClass = MockAction.class;

    when(mapper.getSecuredAction(actionName)).thenReturn(aClass);

    List<String> permissions = new ArrayList<String>() {{
      add(actionName);
    }};

    permittedAction.setPermittedActions(permissions);

    Boolean aBoolean = permittedAction.isPermitted(MockAction.class);

    verify(mapper).getSecuredAction(actionName);

    assertThat(aBoolean, is(true));


  }

  @Test
  public void returnsFalseIfActionIsNotAdded() {
    Boolean aBoolean = permittedAction.isPermitted(MockAction.class);
    assertThat(aBoolean, is(false));
  }


  @Test
  public void setPermittedPlaces() {

  }

  class MockAction implements SecuredAction {
    @Override
    public void execute() {
    }

    @Override
    public void onConditionsBreak() {
    }
  }
}
