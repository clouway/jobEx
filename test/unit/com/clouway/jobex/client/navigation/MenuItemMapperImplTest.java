package com.clouway.jobex.client.navigation;

import com.google.gwt.place.shared.Place;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MenuItemMapperImplTest {

  MenuItemMapperImpl menuItemMapperImpl = new MenuItemMapperImpl(new HashMap<String, Place>());

  @Test
  public void shouldReturnPlaceAssociatedWithPlaceName() {

    String placeName = "name";

    Place place = menuItemMapperImpl.getPlace(placeName);

    assertThat(place, is(notNullValue()));

  }


  @Test
  public void whatIsSavedIsWhatReturned() {

    String placeName = "name";

    TestPlace place = new TestPlace();

    menuItemMapperImpl.addPlace(placeName, place);

    TestPlace returnedPlace = (TestPlace) menuItemMapperImpl.getPlace(placeName);

    assertThat(returnedPlace, is(notNullValue()));

    assertEquals(returnedPlace.getClass(), TestPlace.class);
  }


  class TestPlace extends Place {

  }
}
