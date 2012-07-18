package com.clouway.jobex.client.jobsreview;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobsReviewPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<JobsReviewPlace> {

    public JobsReviewPlace getPlace(String token) {
      return new JobsReviewPlace();
    }

    public String getToken(JobsReviewPlace place) {
      return "jobsReview";
    }
  }
}
