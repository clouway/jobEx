package com.clouway.jobex.client.security;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.server.useraccess.AuthorizationService;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityTest {


  @Mock
  UserCredentialsLocalStorage localStorage;

  @Mock
  private EventBus eventBus;

  JobExRequestFactory factory = RequestFactoryHelper.create(JobExRequestFactory.class);

  JobExRequestFactory.AuthorizationRequestContext requestContext = factory.authorizationContext();

  AuthorizationService service = RequestFactoryHelper.getService(AuthorizationService.class);

  ConditionalActionDispatcherImpl dispatcherImpl;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    dispatcherImpl = new ConditionalActionDispatcherImpl(new HashMap<Class<? extends SecuredAction>, List<Condition>>(),factory, localStorage, eventBus);
  }


  @Test
  public void executesAction() throws Exception {

    String sid = "SID";

    when(service.isValid(sid)).thenReturn(true);

    when(localStorage.getSID()).thenReturn(sid);

    TestAction securedAction = new TestAction();

    dispatcherImpl.dispatch(securedAction, new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    });

    securedAction.assertThatActionIsExecuted();

  }


  @Test
  public void executesActionConditionBeforeActionIsExecuted() {
    String sid = "SID";

    when(service.isValid(sid)).thenReturn(true);

    when(localStorage.getSID()).thenReturn(sid);

    TestAction testAction = new TestAction();

    Condition condition = new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    };

    dispatcherImpl.dispatch(testAction, condition);

    testAction.assertThatActionIsExecuted();
  }

  @Test
  public void actionIsNotExecutedIfConditionIsFalse() {
    TestAction testAction = new TestAction();

    Condition condition = new Condition() {
      @Override
      public boolean isTrue() {
        return false;
      }
    };

    dispatcherImpl.dispatch(testAction, condition);

    testAction.assertThatActionIsNotExecuted();
  }


  @Test
  public void actionIsExecutedIfAllConditionsAreTrue() {

    String sid = "SID";

    when(service.isValid(sid)).thenReturn(true);

    when(localStorage.getSID()).thenReturn(sid);

    TestAction testAction = new TestAction();

    Condition condition1 = new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    };


    Condition condition2 = new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    };

    dispatcherImpl.dispatch(testAction, condition1, condition2);

    testAction.assertThatActionIsExecuted();

  }


  @Test
  public void actionIsNotExecutedIfAnyConditionIsFalse() {

    TestAction testAction = new TestAction();

    Condition condition1 = new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    };


    Condition condition2 = new Condition() {
      @Override
      public boolean isTrue() {
        return false;
      }
    };

    dispatcherImpl.dispatch(testAction, condition1, condition2);

    testAction.assertThatActionIsNotExecuted();

  }


  @Test
  public void shouldCheckConditionAssociatedWithActionOnly() {
    String sid = "SID";

    when(service.isValid(sid)).thenReturn(true);

    when(localStorage.getSID()).thenReturn(sid);

    TestAction testAction = new TestAction();

    dispatcherImpl.addConditions(TestAction.class, new Condition() {
      @Override
      public boolean isTrue() {
        return true;
      }
    });

    dispatcherImpl.dispatch(testAction);

    testAction.assertThatActionIsExecuted();
  }

  @Test
  public void executesOnConditionsBreakWhenAnyConditionIsFalse() {
    TestAction testAction = new TestAction();
    dispatcherImpl.dispatch(testAction, new Condition() {
      @Override
      public boolean isTrue() {
        return false;
      }
    });

    testAction.assertThatOnConditionBreakIsExecuted();
  }




  @Test
  public void authorisesAction() {

    String sid = "SID";

    when(service.isValid(sid)).thenReturn(true);

    when(localStorage.getSID()).thenReturn(sid);

    TestAction securityAction = new TestAction();

    dispatcherImpl.dispatch(securityAction);

    securityAction.assertThatActionIsExecuted();

  }

  @Test
  public void promptsUserAuthorizationWhenSIDIsEmpty() {

    when(localStorage.getSID()).thenReturn("");

    TestAction securityAction = new  TestAction();

    dispatcherImpl.dispatch(securityAction);

    securityAction.assertThatActionIsNotExecuted();

    verify(eventBus).fireEvent(isA(UserAuthorizationEvent.class));

  }


  @Test
  public void checkSIDValidityBeforeExecutingAction() {

    String sid = "sid";

    TestAction securityAction = new TestAction();

    when(localStorage.getSID()).thenReturn(sid);

    when(service.isValid(sid)).thenReturn(true);

    dispatcherImpl.dispatch(securityAction);

    verify(service).isValid(sid);

    verify(eventBus, never()).fireEvent(isA(UserAuthorizationEvent.class));

    securityAction.assertThatActionIsExecuted();

  }


  @Test
  public void promptsUserAuthorizationWhenSIDIsNotValid() {
    String sid = "sid";

    TestAction securityAction = new TestAction();

    when(localStorage.getSID()).thenReturn(sid);

    when(service.isValid(sid)).thenReturn(false);

    dispatcherImpl.dispatch(securityAction);

    verify(service).isValid(sid);

    verify(eventBus).fireEvent(isA(UserAuthorizationEvent.class));

    securityAction.assertThatActionIsNotExecuted();
  }




  class TestAction implements SecuredAction {

    private boolean isExecuted = false;

    private boolean onConditionBreak = false;

    @Override
    public void execute() {
      isExecuted = true;
    }

    @Override
    public void onConditionsBreak() {
      onConditionBreak = true;
    }

    public void assertThatActionIsExecuted() {
      assertTrue(isExecuted);
    }

    public void assertThatActionIsNotExecuted() {
      assertFalse(isExecuted);
    }

    public void assertThatOnConditionBreakIsExecuted() {
      assertTrue(onConditionBreak);
    }
  }


}
