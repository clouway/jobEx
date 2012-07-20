package com.clouway.jobex.client.useraccess.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<RegistrationPlace> {

    public RegistrationPlace getPlace(String token) {
      return new RegistrationPlace();
    }

    public String getToken(RegistrationPlace place) {
      return "register";
    }
  }
}
