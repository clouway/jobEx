package com.clouway.jobex.client.useraccess.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class LoginPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

    public LoginPlace getPlace(String token) {
      return new LoginPlace();
    }

    public String getToken(LoginPlace place) {
      return "login";
    }
  }
}
