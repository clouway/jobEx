package com.clouway.jobex.client;


import com.clouway.jobex.client.cv.CreateCvPlace;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCVPlace;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.cv.PreviewCvPlace;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPlace;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenterImpl;
import com.clouway.jobex.client.gin.JobExGinjector;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.navigation.ActivityPlaceMetadata;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.navigation.MenuItemMapper;
import com.clouway.jobex.client.navigation.NavigationMenu;
import com.clouway.jobex.client.navigation.PageNotFoundActivity;
import com.clouway.jobex.client.navigation.PageNotFoundPlace;
import com.clouway.jobex.client.navigation.SecuredActivityMapper;
import com.clouway.jobex.client.security.AuthorizationPlace;
import com.clouway.jobex.client.security.UserAuthorizationEvent;
import com.clouway.jobex.client.security.UserAuthorizationEventHandler;
import com.clouway.jobex.client.security.UserAuthorizedEvent;
import com.clouway.jobex.client.useraccess.login.LoginPlace;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationPlace;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
import com.clouway.jobex.shared.Permissions;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class JobEx implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    SimplePanel display = new SimplePanel();

    Place place = new JobSearchPlace();

    final JobExGinjector injector = GWT.create(JobExGinjector.class);

    EventBus eventBus = injector.injectEventBus();

    eventBus.addHandler(UserAuthorizedEvent.TYPE, injector.userAuthorizedEventhandler());

    final PlaceController placeController = injector.injectPlaceController();

    eventBus.addHandler(UserAuthorizationEvent.TYPE, new UserAuthorizationEventHandler() {
      @Override
      public void onUserAuthorization(UserAuthorizationEvent event) {
        placeController.goTo(new AuthorizationPlace());
      }
    });


    Map<Class<? extends Place>, ActivityPlaceMetadata> map = new HashMap<Class<? extends Place>, ActivityPlaceMetadata>();

    setCompanyPlaces(injector, map);

    setUserPlaces(injector, map);

    setCommonPlaces(injector, map);

    SecuredActivityMapper mapper = new SecuredActivityMapper(map, injector.credentialsStorage());

    ActivityManager activityManager = new ActivityManager(mapper, eventBus);

    activityManager.setDisplay(display);

    JobExPlaceHistoryMapper placeHistoryMapper = GWT.create(JobExPlaceHistoryMapper.class);

    PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);

    placeHistoryHandler.register(placeController, eventBus, place);


    RootPanel.get("container").add(display);

    MenuItemMapper menuItemMapper = injector.menuItemMapper();

    NavigationMenu navigationMenu = new NavigationMenu(placeController, menuItemMapper);

    List<String> userPermissions = new ArrayList<String>();

    userPermissions.add(Permissions.HOME);
    userPermissions.add(Permissions.NEW_REGISTRATION);
    userPermissions.add(Permissions.LOG_IN);

    navigationMenu.setPermittedPlaces(userPermissions);
    navigationMenu.refresh();

    placeHistoryHandler.handleCurrentHistory();

    placeController.goTo(new JobSearchPlace());

  }

  private void setCommonPlaces(final JobExGinjector injector, Map<Class<? extends Place>, ActivityPlaceMetadata> map) {
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
  }

  private void setUserPlaces(final JobExGinjector injector, Map<Class<? extends Place>, ActivityPlaceMetadata> map) {
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

        }
        presenter.setId(previewCvPlace.getId());
        return presenter;
      }
    });
  }

  private void setCompanyPlaces(final JobExGinjector injector, Map<Class<? extends Place>, ActivityPlaceMetadata> map) {
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


  }
}