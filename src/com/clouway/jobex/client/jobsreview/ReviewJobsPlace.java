package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewJobsPlace extends SecuredPlace {

  public static class Tokenizer implements PlaceTokenizer<ReviewJobsPlace> {

    public ReviewJobsPlace getPlace(String token) {
      return new ReviewJobsPlace();
    }

    public String getToken(ReviewJobsPlace place) {
      return "jobsReview";
    }
  }
}
