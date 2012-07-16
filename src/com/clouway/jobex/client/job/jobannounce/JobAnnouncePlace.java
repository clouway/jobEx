package com.clouway.jobex.client.job.jobannounce;


import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobAnnouncePlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<JobAnnouncePlace> {

    public JobAnnouncePlace getPlace(String token) {
      return new JobAnnouncePlace();
    }

    public String getToken(JobAnnouncePlace place) {
      return "jobAnnounce";
    }

  }

}
