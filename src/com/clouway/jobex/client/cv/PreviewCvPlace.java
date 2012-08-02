package com.clouway.jobex.client.cv;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PreviewCvPlace extends SecuredPlace {

  private Long id;

  public PreviewCvPlace(Long id) {

    this.id = id;
  }

  public PreviewCvPlace() {

  }


  public Long getId() {
    return id;
  }

  public static class Tokenizer implements PlaceTokenizer<PreviewCvPlace> {

    public PreviewCvPlace getPlace(String token) {
      if (token != null && !"".equals(token)) {
        return new PreviewCvPlace(Long.valueOf(token));
      }
      return new PreviewCvPlace();

    }

    public String getToken(PreviewCvPlace place) {
      if (place.getId() == null) {
        return "";
      }
      return String.valueOf(place.getId());
    }
  }
}
