package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.applyingforjob.CreateCvPlace;
import com.clouway.jobex.client.applyingforjob.EditCVPlace;
import com.clouway.jobex.client.applyingforjob.JobApplicationPresenter;
import com.clouway.jobex.client.applyingforjob.SelectCvPlace;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobExActivityMapper implements ActivityMapper {

  @Inject
  JobSearchPresenter jobSearchPresenter;

  @Inject
  JobAnnouncePresenterImpl jobAnnouncePresenter;

  @Inject
  JobApplicationPresenter jobApplicationPresenter;

  @Inject
  EditCvWorkflow editCvWorkflow;

  @Inject
  CreatingNewCVWorkflow creatingNewCVWorkflow;

  public Activity getActivity(Place place) {

    if (place instanceof JobSearchPlace) {
      return jobSearchPresenter;
    }

    if (place instanceof JobAnnouncePlace) {
      return jobAnnouncePresenter;
    }

    if (place instanceof SelectCvPlace) {
      return jobApplicationPresenter;
    }
    if (place instanceof CreateCvPlace) {
      return creatingNewCVWorkflow;
    }

    if (place instanceof EditCVPlace) {
      editCvWorkflow.editCv(((EditCVPlace) place).getId());
      return editCvWorkflow;
    }

    return null;
  }
}
