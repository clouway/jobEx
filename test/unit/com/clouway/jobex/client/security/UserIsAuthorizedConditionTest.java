package com.clouway.jobex.client.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserIsAuthorizedConditionTest {


  @Mock
  public UserCredentialsLocalStorage storage;

  UserIsAuthorizedCondition condition;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    condition = new UserIsAuthorizedCondition(storage);

  }

  @Test
  public void returnsTrueWhenUserIsAuthorized() {
    String sid = "sid";
    String username = "username";

    when(storage.getSID()).thenReturn(sid);

    when(storage.getUsername()).thenReturn(username);

    Boolean isAuthorized = condition.isTrue();

    assertThat(isAuthorized, is(true));

  }


  @Test
  public void returnsFalseWhenUserIsNotAuthorized() {

    Boolean isAuthorized = condition.isTrue();

    assertThat(isAuthorized, is(false));

  }


}
