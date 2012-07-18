package com.clouway.jobex.client.applyingforjob;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCVPlace extends Place {

  private final Long id;

  public EditCVPlace(Long id) {

    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public static class Tokenizer implements PlaceTokenizer<EditCVPlace> {

    public EditCVPlace getPlace(String token) {
      return new EditCVPlace(1l);
    }

    public String getToken(EditCVPlace place) {
      return "createCv";
    }
  }
}
