package com.clouway.jobex.client.reviewjobs;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<ReviewJobsPlace> {

    public ReviewJobsPlace getPlace(String token) {
      return new ReviewJobsPlace();
    }

    public String getToken(ReviewJobsPlace place) {
      return "jobsReview";
    }
  }
}
