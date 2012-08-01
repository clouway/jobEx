package com.clouway.jobex.client.job.jobannounce;


import com.clouway.jobex.client.navigation.SecuredPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobAnnouncePlace extends SecuredPlace {

  public static class Tokenizer implements PlaceTokenizer<JobAnnouncePlace> {

    public JobAnnouncePlace getPlace(String token) {
      return new JobAnnouncePlace();
    }

    public String getToken(JobAnnouncePlace place) {
      return "jobAnnounce";
    }

  }

}
