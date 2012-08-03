package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.CreateCvPlace;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCVPlace;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.cv.PreviewCvPlace;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.navigation.ActivityPlaceMetadata;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPlace;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class PlacesMapProvider implements Provider<Map<Class<? extends Place>, ActivityPlaceMetadata>> {


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


  @Override
  public Map<Class<? extends Place>, ActivityPlaceMetadata> get() {

    Map<Class<? extends Place>, ActivityPlaceMetadata> map = new HashMap<Class<? extends Place>, ActivityPlaceMetadata>();
    map.put(JobSearchPlace.class, new ActivityPlaceMetadata<JobSearchPlace, JobSearchPresenter>() {
      @Override
      public JobSearchPresenter getActivity(JobSearchPlace jobSearchPlace) {
        return jobSearchPresenter;
      }
    });
    map.put(JobAnnouncePlace.class, new ActivityPlaceMetadata<JobAnnouncePlace, JobAnnouncePresenterImpl>() {
      @Override
      public JobAnnouncePresenterImpl getActivity(JobAnnouncePlace jobAnnouncePlace) {
        return jobAnnouncePresenter;
      }
    });

    map.put(PreviewCvPlace.class, new ActivityPlaceMetadata<PreviewCvPlace, UserCVsPresenter>() {
      @Override
      public UserCVsPresenter getActivity(PreviewCvPlace previewCvPlace) {
        return userCVsPresenter;
      }
    });

    map.put(CreateCvPlace.class, new ActivityPlaceMetadata<CreateCvPlace, CreatingNewCVWorkflow>() {
      @Override
      public CreatingNewCVWorkflow getActivity(CreateCvPlace createCvPlace) {
        return creatingNewCVWorkflow;
      }
    });

    map.put(EditCVPlace.class, new ActivityPlaceMetadata<EditCVPlace, EditCvWorkflow>() {
      @Override
      public EditCvWorkflow getActivity(EditCVPlace editCVPlace) {
        editCvWorkflow.editCv((editCVPlace.getId()));
        return editCvWorkflow;
      }
    });
    map.put(ReviewJobsPlace.class, new ActivityPlaceMetadata<ReviewJobsPlace, ReviewJobsPresenterImpl>() {
      @Override
      public ReviewJobsPresenterImpl getActivity(ReviewJobsPlace reviewJobsPlace) {
        return jobsReviewPresenter;
      }
    });

    map.put(RegistrationPlace.class, new ActivityPlaceMetadata<RegistrationPlace, RegistrationPresenter>() {
      @Override
      public RegistrationPresenter getActivity(RegistrationPlace registrationPlace) {
        return registrationPresenter;
      }
    });

    map.put(AuthorizationPlace.class, new ActivityPlaceMetadata<AuthorizationPlace, LoginPresenter>() {
      @Override
      public LoginPresenter getActivity(AuthorizationPlace loginPlace) {
        return loginPresenter;
      }
    });




    return map;

  }
}
