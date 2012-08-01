package com.clouway.jobex.client.security;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredActionMapperImplTest {


  SecuredActionMapperImpl mapperImpl;

  String actionName = "create new cv";

  @Before
  public void setUp() throws Exception {

    mapperImpl = new SecuredActionMapperImpl(new HashMap<String, Class<? extends SecuredAction>>() {{
      put(actionName,TestAction.class);
    }});
  }

  @Test
  public void returnsSecuredActionClassAssociatedWithActionName() {
    Class securedAction = mapperImpl.getSecuredAction(actionName);
    assertThat(securedAction, is(notNullValue()));
    assertEquals(securedAction, TestAction.class);
  }

  @Test(expected = NotActionBoundException.class)
  public void throwsNoActionBoundExceptionWhenNoActionIsAssociatedWithProvidedActionName() {
    mapperImpl.getSecuredAction("notAction");
  }

  class TestAction implements SecuredAction {
    @Override
    public void execute() {

    }

    @Override
    public void onConditionsBreak() {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }
}
