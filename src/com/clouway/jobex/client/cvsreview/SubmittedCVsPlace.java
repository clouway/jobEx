package com.clouway.jobex.client.cvsreview;

import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class SubmittedCVsPlace extends SecuredPlace {

  private final Long jobId;

  public SubmittedCVsPlace(Long jobId) {
    this.jobId = jobId;
  }

  public Long getJobId() {
    return jobId;
  }

  public static class Tokenizer implements PlaceTokenizer<SubmittedCVsPlace> {

    public SubmittedCVsPlace getPlace(String token) {
      return new SubmittedCVsPlace(Long.valueOf(token));
    }

    public String getToken(SubmittedCVsPlace place) {
      return "reviewCV";
    }
  }
}
