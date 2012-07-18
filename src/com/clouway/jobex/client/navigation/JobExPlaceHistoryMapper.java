package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.applyingforjob.CreateCvPlace;
import com.clouway.jobex.client.applyingforjob.EditCVPlace;
import com.clouway.jobex.client.applyingforjob.SelectCvPlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@WithTokenizers({JobSearchPlace.Tokenizer.class, JobAnnouncePlace.Tokenizer.class,
        SelectCvPlace.Tokenizer.class, CreateCvPlace.Tokenizer.class, EditCVPlace.Tokenizer.class})
public interface JobExPlaceHistoryMapper extends PlaceHistoryMapper {
}
