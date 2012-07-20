package com.clouway.jobex.client.cvsreview;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ReviewCVPlace extends Place {

  private final Long jobId;

  public ReviewCVPlace(Long jobId) {

    this.jobId = jobId;
  }

  public Long getJobId() {
    return jobId;
  }

  public static class Tokenizer implements PlaceTokenizer<ReviewCVPlace> {

    public ReviewCVPlace getPlace(String token) {
      return new ReviewCVPlace(Long.valueOf(token));
    }

    public String getToken(ReviewCVPlace place) {
      return "reviewCV";
    }
  }
}
