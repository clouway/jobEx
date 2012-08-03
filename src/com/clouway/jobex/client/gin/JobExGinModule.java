package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.*;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.cv.UserCVsViewImpl;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflowView;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflowViewImpl;
import com.clouway.jobex.client.cv.EditCVWorkflowView;
import com.clouway.jobex.client.cv.EditCVWorkflowViewImpl;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPlace;
import com.clouway.jobex.client.submittedcvs.SubmittedCVsPlace;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPresenter;
import com.clouway.jobex.client.reviewjobs.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.reviewjobs.ReviewJobsView;
import com.clouway.jobex.client.reviewjobs.ReviewJobsViewImpl;
import com.clouway.jobex.client.submittedcvs.SubmittedCVsPresenter;
import com.clouway.jobex.client.submittedcvs.SubmittedCVsPresenterImpl;
import com.clouway.jobex.client.submittedcvs.SubmittedCVsView;
import com.clouway.jobex.client.submittedcvs.SubmittedCVsViewImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceView;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceViewImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.job.jobsearch.JobSearchViewImpl;
import com.clouway.jobex.client.navigation.*;
import com.clouway.jobex.client.navigation.ActivityPlaceMetadata;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.navigation.NavigationMenu;
import com.clouway.jobex.client.navigation.NavigationMenuController;
import com.clouway.jobex.client.navigation.SecuredActivityMapper;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserAuthorizedEventHandler;
import com.clouway.jobex.client.security.UserAuthorizedEventHandlerImpl;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.client.security.UserCredentialsLocalStorageImpl;
import com.clouway.jobex.client.security.UserPermissions;
import com.clouway.jobex.client.security.UserPermissionsImpl;
import com.clouway.jobex.client.useraccess.login.LoginPlace;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.login.LoginView;
import com.clouway.jobex.client.useraccess.login.LoginViewImpl;
import com.clouway.jobex.client.useraccess.logout.LogoutPlace;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.clouway.jobex.client.useraccess.logout.LogoutEventHandler;
import com.clouway.jobex.client.useraccess.logout.LogoutEventHandlerImpl;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationView;
import com.clouway.jobex.client.useraccess.register.RegistrationViewImpl;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.Permissions;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobExGinModule extends AbstractGinModule {

  protected void configure() {

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    bind(JobSearchView.class).to(JobSearchViewImpl.class).in(Singleton.class);

    bind(JobAnnounceView.class).to(JobAnnounceViewImpl.class).in(Singleton.class);

    bind(PlaceHistoryMapper.class).to(JobExPlaceHistoryMapper.class).in(Singleton.class);

    bind(ActivityMapper.class).to(SecuredActivityMapper.class);

    bind(UserCVsView.class).to(UserCVsViewImpl.class).in(Singleton.class);

    bind(CreatingNewCVWorkflowView.class).to(CreatingNewCVWorkflowViewImpl.class).in(Singleton.class);

    bind(EditCVWorkflowView.class).to(EditCVWorkflowViewImpl.class).in(Singleton.class);

    bind(ReviewJobsPresenter.class).to(ReviewJobsPresenterImpl.class);

    bind(ReviewJobsView.class).to(ReviewJobsViewImpl.class).in(Singleton.class);

    bind(RegistrationView.class).to(RegistrationViewImpl.class).in(Singleton.class);

    bind(LoginView.class).to(LoginViewImpl.class).in(Singleton.class);

    bind(SubmittedCVsView.class).to(SubmittedCVsViewImpl.class).in(Singleton.class);

    bind(SubmittedCVsPresenter.class).to(SubmittedCVsPresenterImpl.class);

    bind(UserCredentialsLocalStorage.class).to(UserCredentialsLocalStorageImpl.class).in(Singleton.class);

    bind(NavigationMenuController.class).to(NavigationMenu.class);

    bind(UserAuthorizedEventHandler.class).to(UserAuthorizedEventHandlerImpl.class);

    bind(MenuPlacesMapper.class).to(MenuPlacesMapperImpl.class);

    bind(UserPermissions.class).to(UserPermissionsImpl.class).in(Singleton.class);

    bind(SecuredActivityMapper.class).in(Singleton.class);

    bind(LogoutEventHandler.class).to(LogoutEventHandlerImpl.class);

  }

  @Provides
  @Singleton
  PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  @Provides
  @Named("allPlacesMap")
  Map<String, Class<? extends Place>> allPlacesMap() {
    Map<String, Class<? extends Place>> map = new HashMap<String, Class<? extends Place>>();

    map.put(Permissions.HOME, JobSearchPlace.class);
    map.put(Permissions.CREATE_CV, CreateCvPlace.class);
    map.put(Permissions.PREVIEW_CV, PreviewCvPlace.class);
    map.put(Permissions.ANNOUNCE_JOB, JobAnnouncePlace.class);
    map.put(Permissions.PREVIEW_JOBS, ReviewJobsPlace.class);
    map.put(Permissions.PREVIEW_APPLIED_CVS, SubmittedCVsPlace.class);
//    map.put(Permissions.HOME, JobSearchPlace.class);
    return map;
  }

  @Provides
  public Map<String, Place> placeMap() {

    Map<String, Place> placeMap = new HashMap<String, Place>();

    placeMap.put(Permissions.CREATE_CV, new CreateCvPlace());

    placeMap.put(Permissions.PREVIEW_CV, new PreviewCvPlace());

    placeMap.put(Permissions.ANNOUNCE_JOB, new JobAnnouncePlace());

    placeMap.put(Permissions.PREVIEW_JOBS, new ReviewJobsPlace());

    placeMap.put(Permissions.HOME, new JobSearchPlace());

    placeMap.put(Permissions.LOG_IN, new AuthorizationPlace());

    placeMap.put(Permissions.NEW_REGISTRATION, new RegistrationPlace());

    placeMap.put(Permissions.LOG_OUT, new LogoutPlace());

    return placeMap;
  }

  @Provides
  Map<Class<? extends Place>, ActivityPlaceMetadata> map(final JobExGinjector injector) {

    Map<Class<? extends Place>, ActivityPlaceMetadata> map = new HashMap<Class<? extends Place>, ActivityPlaceMetadata>();
    map.put(RegistrationPlace.class, new ActivityPlaceMetadata<RegistrationPlace, RegistrationPresenter>() {
      @Override
      public RegistrationPresenter getActivity(RegistrationPlace registrationPlace) {
        return injector.registrationPresenter();
      }
    });

    map.put(AuthorizationPlace.class, new ActivityPlaceMetadata<AuthorizationPlace, LoginPresenter>() {
      @Override
      public LoginPresenter getActivity(AuthorizationPlace loginPlace) {
        return injector.loginPresenter();
      }
    });


    map.put(JobSearchPlace.class, new ActivityPlaceMetadata<JobSearchPlace, JobSearchPresenter>() {
      @Override
      public JobSearchPresenter getActivity(JobSearchPlace jobSearchPlace) {
        return injector.jobSearchPresenter();
      }
    });

    map.put(PageNotFoundPlace.class, new ActivityPlaceMetadata<PageNotFoundPlace, PageNotFoundActivity>() {
      @Override
      public PageNotFoundActivity getActivity(PageNotFoundPlace pageNotFoundPlace) {
        return injector.pageNotFoundPlace();
      }
    });

    map.put(CreateCvPlace.class, new ActivityPlaceMetadata<CreateCvPlace, CreatingNewCVWorkflow>() {
      @Override
      public CreatingNewCVWorkflow getActivity(CreateCvPlace createCvPlace) {
        return injector.creatingNewCVWorkflow();
      }
    });

    map.put(EditCVPlace.class, new ActivityPlaceMetadata<EditCVPlace, EditCvWorkflow>() {
      @Override
      public EditCvWorkflow getActivity(EditCVPlace editCVPlace) {
        EditCvWorkflow editCvWorkflow = injector.editCvWorkflow();
        editCvWorkflow.editCv((editCVPlace.getId()));
        return editCvWorkflow;
      }
    });

    map.put(PreviewCvPlace.class, new ActivityPlaceMetadata<PreviewCvPlace, UserCVsPresenter>() {
      @Override
      public UserCVsPresenter getActivity(PreviewCvPlace previewCvPlace) {
        UserCVsPresenter presenter = injector.userCVsPresenter();
        if (previewCvPlace.getId() != null) {
          presenter.setId(previewCvPlace.getId());
        } else {
          presenter.unsetId();
        }
        return presenter;
      }
    });

    map.put(JobAnnouncePlace.class, new ActivityPlaceMetadata<JobAnnouncePlace, JobAnnouncePresenterImpl>() {
      @Override
      public JobAnnouncePresenterImpl getActivity(JobAnnouncePlace jobAnnouncePlace) {
        return injector.jobAnnouncePresenter();
      }
    });

    map.put(ReviewJobsPlace.class, new ActivityPlaceMetadata<ReviewJobsPlace, ReviewJobsPresenterImpl>() {
      @Override
      public ReviewJobsPresenterImpl getActivity(ReviewJobsPlace reviewJobsPlace) {
        return injector.jobsReviewPresenter();
      }
    });

    map.put(SubmittedCVsPlace.class, new ActivityPlaceMetadata<SubmittedCVsPlace, SubmittedCVsPresenterImpl>() {
      @Override
      public SubmittedCVsPresenterImpl getActivity(SubmittedCVsPlace submittedCVsPlace) {
        SubmittedCVsPresenterImpl presenter = injector.submittedCvsPresenter();
        presenter.reviewSubmittedCVs(submittedCVsPlace.getJobId());
        return presenter;
      }
    });

    map.put(LoginPlace.class, new ActivityPlaceMetadata<LoginPlace, LoginPresenter>() {
      @Override
      public LoginPresenter getActivity(LoginPlace loginPlace) {
        return injector.loginPresenter();
      }
    });


    map.put(LogoutPlace.class, new ActivityPlaceMetadata<LogoutPlace, LogoutEventHandlerImpl>() {
      @Override
      public LogoutEventHandlerImpl getActivity(LogoutPlace logoutPlace) {
        return injector.logoutEventHandler();
      }
    });


    return map;
  }


  @Provides
  @Singleton
  JobExRequestFactory getJobExRequestFactory(EventBus eventBus) {
    JobExRequestFactory jobExRequestFactory = GWT.create(JobExRequestFactory.class);
    jobExRequestFactory.initialize(eventBus);
    return jobExRequestFactory;
  }
}
