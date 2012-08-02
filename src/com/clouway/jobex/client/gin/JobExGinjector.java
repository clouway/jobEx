package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenterImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.navigation.MenuPlacesMapper;
import com.clouway.jobex.client.navigation.PageNotFoundActivity;
import com.clouway.jobex.client.navigation.SecuredActivityMapper;
import com.clouway.jobex.client.security.UserAuthorizedEventHandler;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
import com.clouway.jobex.client.useraccess.logout.LogoutEventHandler;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@GinModules(JobExGinModule.class)
public interface JobExGinjector extends Ginjector {


  PlaceController injectPlaceController();

  EventBus injectEventBus();

  SecuredActivityMapper injectActivityMapper();

  JobSearchPresenter jobSearchPresenter();

  JobAnnouncePresenterImpl jobAnnouncePresenter();

  UserCVsPresenter userCVsPresenter();

  EditCvWorkflow editCvWorkflow();

  CreatingNewCVWorkflow creatingNewCVWorkflow();

  ReviewJobsPresenterImpl jobsReviewPresenter();

  RegistrationPresenter registrationPresenter();

  LoginPresenter loginPresenter();

  SubmittedCVsPresenterImpl submittedCvsPresenter();

  UserAuthorizedEventHandler userAuthorizedEventhandler();

  MenuPlacesMapper menuItemMapper();

  PageNotFoundActivity pageNotFoundPlace();

  SubmittedCVsPresenterImpl reviewCVPresenter();

  LogoutEventHandler injectLogoutEventHandler();
}
