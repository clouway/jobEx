package com.clouway.jobex.client.cv;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCVPlace extends Place {

  private Long id;

  public EditCVPlace(Long id) {

    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public static class Tokenizer implements PlaceTokenizer<EditCVPlace> {

    public EditCVPlace getPlace(String token) {
      return new EditCVPlace(Long.valueOf(token));
    }

    public String getToken(EditCVPlace place) {
      return String.valueOf(place.getId());
    }
  }
}
