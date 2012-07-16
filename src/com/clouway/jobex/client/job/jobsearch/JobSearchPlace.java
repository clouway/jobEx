package com.clouway.jobex.client.job.jobsearch;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<JobSearchPlace> {

    public JobSearchPlace getPlace(String token) {
      return new JobSearchPlace();
    }

    public String getToken(JobSearchPlace place) {
      return "jobSearch";
    }
  }
}
