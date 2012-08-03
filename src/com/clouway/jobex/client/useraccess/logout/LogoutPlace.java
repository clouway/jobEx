package com.clouway.jobex.client.useraccess.logout;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LogoutPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<LogoutPlace> {

    public LogoutPlace getPlace(String token) {
      return new LogoutPlace();
    }

    public String getToken(LogoutPlace place) {
      return "logout";
    }
  }
}
