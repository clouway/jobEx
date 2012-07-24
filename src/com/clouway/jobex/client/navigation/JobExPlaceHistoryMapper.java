package com.clouway.jobex.client.navigation;


import com.clouway.jobex.client.cv.CreateCvPlace;
import com.clouway.jobex.client.cv.EditCVPlace;
import com.clouway.jobex.client.cv.PreviewCvPlace;


import com.clouway.jobex.client.cvsreview.SubmittedCVsPlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.clouway.jobex.client.useraccess.login.LoginPlace;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@WithTokenizers({JobSearchPlace.Tokenizer.class, JobAnnouncePlace.Tokenizer.class,
        PreviewCvPlace.Tokenizer.class, CreateCvPlace.Tokenizer.class, EditCVPlace.Tokenizer.class,
        ReviewJobsPlace.Tokenizer.class, RegistrationPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
        SubmittedCVsPlace.Tokenizer.class})
public interface JobExPlaceHistoryMapper extends PlaceHistoryMapper {
}
