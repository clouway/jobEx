package com.clouway.jobex.client.applyingforjob;

import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SelectCvPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<SelectCvPlace> {

    public SelectCvPlace getPlace(String token) {
      return new SelectCvPlace();
    }

    public String getToken(SelectCvPlace place) {
      return "selectCV";
    }
  }

}
