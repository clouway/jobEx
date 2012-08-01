package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.security.AuthorizationActivity;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecuredActivityMapperTest {


  private class TestPlace extends Place {

  }


  @Mock
  public UserCredentialsLocalStorage provider;

  @Mock
  Map<Class<? extends Place>, ActivityPlaceMetadata> activityPlaceMap;


  private SecuredActivityMapper mapper;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    activityPlaceMap = new HashMap<Class<? extends Place>, ActivityPlaceMetadata>();

    activityPlaceMap.put(TestPlace.class, new ActivityPlaceMetadata<TestPlace, TestActivity>() {
      @Override
      public TestActivity getActivity(TestPlace testPlace) {
        return new TestActivity();
      }
    });
    activityPlaceMap.put(AnotherPlace.class, new ActivityPlaceMetadata<AnotherPlace, AnotherActivity>() {
      @Override
      public AnotherActivity getActivity(AnotherPlace anotherPlace) {
        return new AnotherActivity();
      }
    });
    activityPlaceMap.put(MySecuredPlace.class, new ActivityPlaceMetadata<MySecuredPlace, MySecuredActivity>() {
      @Override
      public MySecuredActivity getActivity(MySecuredPlace mySecuredPlace) {
        return new MySecuredActivity();
      }
    });
    activityPlaceMap.put(AuthorizationPlace.class, new ActivityPlaceMetadata<AuthorizationPlace, AuthorizationActivity>() {
      @Override
      public AuthorizationActivity getActivity(AuthorizationPlace authorizationPlace) {
        return new AuthorizationActivity();
      }
    });

    mapper = new SecuredActivityMapper(activityPlaceMap, provider);
  }

  @Test
  public void returnsActivity() {
    TestActivity activity = (TestActivity) mapper.getActivity(new TestPlace());
    assertThat(activity, is(notNullValue()));
    assertEquals(activity.getClass(), TestActivity.class);
  }


  @Test
  public void tryWithAnotherActivity() {
    Activity activity = mapper.getActivity(new AnotherPlace());
    assertThat(activity, is(notNullValue()));
    assertEquals(activity.getClass(), AnotherActivity.class);
  }


  @Test
  public void returnsPageNotFoundPlaceWhenNoActivityForProvidedPlace() throws Exception {
    Activity pageNotFoundActivity = mapper.getActivity(new UnknownPlace());
    assertThat(pageNotFoundActivity, is(notNullValue()));
    assertEquals(pageNotFoundActivity.getClass(), PageNotFoundActivity.class);
  }

  @Test
  public void returnsAuthorizationActivityIfPlaceIsSecuredAndUserIsNotAuthorized() {
    when(provider.isAuthorized()).thenReturn(false);
    Activity activity = mapper.getActivity(new SecuredPlace());
    assertThat(activity, is(notNullValue()));
    assertEquals(activity.getClass(), AuthorizationActivity.class);
  }


  @Test
  public void returnsActivityPlaceWhenPlaceIsSecuredAndUserIsAuthorized() {
    when(provider.isAuthorized()).thenReturn(true);
    Activity activity = mapper.getActivity(new MySecuredPlace());
    assertThat(activity, is(notNullValue()));
    assertEquals(MySecuredActivity.class, activity.getClass());
  }

  class MySecuredPlace extends SecuredPlace {
  }

  class MySecuredActivity extends AbstractActivity {

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
    }
  }


  private class AnotherActivity extends AbstractActivity {
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {

    }
  }

  private class AnotherPlace extends Place {
  }

  private class UnknownPlace extends Place {
  }

  public class TestActivity extends AbstractActivity {
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {

    }

  }

}
