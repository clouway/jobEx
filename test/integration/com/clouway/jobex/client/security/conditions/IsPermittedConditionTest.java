package com.clouway.jobex.client.security.conditions;

import com.clouway.jobex.client.security.SecuredAction;
import com.clouway.jobex.client.security.UserPermittedActions;
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
public class IsPermittedConditionTest {

  @Mock
  public UserPermittedActions permittedActions;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }


  @Test
  public void returnsTrueIfActionIsPermitted() {

    when(permittedActions.isPermitted(TestAction.class)).thenReturn(true);

    Boolean isPermitted = new IsPermittedCondition(TestAction.class, permittedActions).isTrue();

    assertThat(isPermitted, is(true));

  }


  @Test
  public void returnsFalseIfActionIsNotPermitted() {
    when(permittedActions.isPermitted(TestAction.class)).thenReturn(false);
    Boolean isPermitted = new IsPermittedCondition(TestAction.class, permittedActions).isTrue();
    assertThat(isPermitted, is(false));
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
