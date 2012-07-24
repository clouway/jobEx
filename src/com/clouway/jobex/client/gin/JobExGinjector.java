package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.cv.EditCvWorkflow;
import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.cvsreview.ReviewCVPresenterImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePresenterImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.useraccess.login.LoginPresenter;
import com.clouway.jobex.client.useraccess.register.RegistrationPresenter;
import com.google.gwt.activity.shared.ActivityMapper;
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


  ActivityMapper injectActivityMapper();


  UserCVsPresenter jobApplicationPresenter();


  UserCVsView jobApplicationView();


  ReviewCVPresenterImpl reviewCVPresenter();


  JobSearchPresenter jobSearchPresenter();


  JobAnnouncePresenterImpl jobAnnouncePresenter();


  UserCVsPresenter userCVsPresenter();


  EditCvWorkflow editCvWorkflow();


  CreatingNewCVWorkflow creatingNewCVWorkflow();


  ReviewJobsPresenterImpl jobsReviewPresenter();


  RegistrationPresenter registrationPresenter();


  LoginPresenter loginPresenter();


}
