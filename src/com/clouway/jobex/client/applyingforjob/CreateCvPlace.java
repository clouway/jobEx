package com.clouway.jobex.client.applyingforjob;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class CreateCvPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<CreateCvPlace> {

    public CreateCvPlace getPlace(String token) {
      return new CreateCvPlace();
    }

    public String getToken(CreateCvPlace place) {
      return "createCv";
    }
  }
}
