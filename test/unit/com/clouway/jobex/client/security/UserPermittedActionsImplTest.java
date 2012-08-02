package com.clouway.jobex.client.security;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserPermittedActionsImplTest {


  private UserPermittedActionsImpl actions = new UserPermittedActionsImpl();

  @Test
  public void returnsTrueIfPermissionPresents() {
    actions.setPermittedActions(new ArrayList<String>() {{
      add("action");
    }});

    Boolean isPermitted = actions.isPermitted("action");

    assertThat(isPermitted, is(true));
  }


  @Test
  public void returnsFalseWhenPermissionDoesNotPresent() {

    Boolean isPermitted = actions.isPermitted("action");

    assertThat(isPermitted, is(false));

  }
}
