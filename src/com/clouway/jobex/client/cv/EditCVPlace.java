package com.clouway.jobex.client.cv;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCVPlace extends SecuredPlace {


  public EditCVPlace(String linkText) {
    super(linkText);
  }

  private Long id;

  public EditCVPlace(Long id) {

    this.id = id;
  }

  public EditCVPlace() {
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
