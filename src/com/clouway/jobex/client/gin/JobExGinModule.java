package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.*;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenter;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenterImpl;
import com.clouway.jobex.client.cvsreview.SubmittedCVsView;
import com.clouway.jobex.client.cvsreview.SubmittedCVsViewImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceView;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceViewImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.job.jobsearch.JobSearchViewImpl;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.jobsreview.ReviewJobsView;
import com.clouway.jobex.client.jobsreview.ReviewJobsViewImpl;
import com.clouway.jobex.client.navigation.ActivityPlaceMetadata;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.navigation.MenuItemMapper;
import com.clouway.jobex.client.navigation.MenuItemMapperImpl;
import com.clouway.jobex.client.navigation.NavigationMenu;
import com.clouway.jobex.client.navigation.NavigationMenuController;
import com.clouway.jobex.client.navigation.SecuredActivityMapper;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserAuthorizedEventHandler;
import com.clouway.jobex.client.security.UserAuthorizedEventHandlerImpl;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.client.security.UserCredentialsLocalStorageImpl;
import com.clouway.jobex.client.security.UserPermittedActions;
import com.clouway.jobex.client.security.UserPermittedActionsImpl;
import com.clouway.jobex.client.useraccess.login.LoginView;
import com.clouway.jobex.client.useraccess.login.LoginViewImpl;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.clouway.jobex.client.useraccess.logout.LogoutEventHandler;
import com.clouway.jobex.client.useraccess.logout.LogoutEventHandlerImpl;
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
import com.google.inject.TypeLiteral;
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

    bind(MenuItemMapper.class).to(MenuItemMapperImpl.class);

    bind(UserPermittedActions.class).to(UserPermittedActionsImpl.class).in(Singleton.class  );

    bind(new TypeLiteral<Map<Class<? extends Place>, ActivityPlaceMetadata>>() {
    }).toProvider(PlacesMapProvider.class);

    bind(LogoutEventHandler.class).to(LogoutEventHandlerImpl.class);

  }

  @Provides
  @Singleton
  PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
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

    return placeMap;
  }


  @Provides
  @Singleton
  JobExRequestFactory getJobExRequestFactory(EventBus eventBus) {
    JobExRequestFactory jobExRequestFactory = GWT.create(JobExRequestFactory.class);
    jobExRequestFactory.initialize(eventBus);
    return jobExRequestFactory;
  }
}
