package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.cv.CreateCvPlace;
import com.clouway.jobex.client.cv.EditCVPlace;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cv.PreviewCvPlace;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.useraccess.login.LoginPlace;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
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
  UserCVsPresenter userCVsPresenter;

  @Inject
  EditCvWorkflow editCvWorkflow;

  @Inject
  CreatingNewCVWorkflow creatingNewCVWorkflow;

  @Inject
  ReviewJobsPresenterImpl jobsReviewPresenter;

  @Inject
  RegistrationPresenter registrationPresenter;

  @Inject
  LoginPresenter loginPresenter;

  public Activity getActivity(Place place) {

    if (place instanceof JobSearchPlace) {
      return jobSearchPresenter;
    }

    if (place instanceof JobAnnouncePlace) {
      return jobAnnouncePresenter;
    }

    if (place instanceof PreviewCvPlace) {
      return userCVsPresenter;
    }
    if (place instanceof CreateCvPlace) {
      return creatingNewCVWorkflow;
    }

    if (place instanceof EditCVPlace) {
      editCvWorkflow.editCv(((EditCVPlace) place).getId());
      return editCvWorkflow;
    }

    if (place instanceof ReviewJobsPlace) {
      return jobsReviewPresenter;
    }

    if (place instanceof RegistrationPlace) {
      return registrationPresenter;
    }

    if (place instanceof LoginPlace) {
      return loginPresenter;
    }

    return null;
  }
}
