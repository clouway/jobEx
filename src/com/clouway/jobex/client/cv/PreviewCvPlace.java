package com.clouway.jobex.client.cv;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PreviewCvPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<PreviewCvPlace> {

    public PreviewCvPlace getPlace(String token) {
      return new PreviewCvPlace();
    }

    public String getToken(PreviewCvPlace place) {
      return "selectCV";
    }
  }

}
