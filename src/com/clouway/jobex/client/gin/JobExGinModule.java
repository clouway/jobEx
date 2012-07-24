package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.CreatingNewCVWorkflowView;
import com.clouway.jobex.client.cv.CreatingNewCVWorkflowViewImpl;
import com.clouway.jobex.client.cv.EditCVWorkflowView;
import com.clouway.jobex.client.cv.EditCVWorkflowViewImpl;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenter;
import com.clouway.jobex.client.cvsreview.SubmittedCVsPresenterImpl;
import com.clouway.jobex.client.cvsreview.SubmittedCVsView;
import com.clouway.jobex.client.cvsreview.SubmittedCVsViewImpl;
import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.cv.UserCVsViewImpl;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceView;
import com.clouway.jobex.client.job.jobannounce.JobAnnounceViewImpl;
import com.clouway.jobex.client.job.jobsearch.JobSearchView;
import com.clouway.jobex.client.job.jobsearch.JobSearchViewImpl;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenter;
import com.clouway.jobex.client.jobsreview.ReviewJobsPresenterImpl;
import com.clouway.jobex.client.jobsreview.ReviewJobsView;
import com.clouway.jobex.client.jobsreview.ReviewJobsViewImpl;
import com.clouway.jobex.client.navigation.ActivityPlaceMetadata;
import com.clouway.jobex.client.navigation.ApplicationActivityMapper;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.security.CompanyNameProvider;
import com.clouway.jobex.client.security.CompanyNameProviderImpl;
import com.clouway.jobex.client.security.SecurityProvider;
import com.clouway.jobex.client.security.SecurityProviderImpl;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.client.security.UsernameProviderImpl;
import com.clouway.jobex.client.useraccess.login.LoginView;
import com.clouway.jobex.client.useraccess.login.LoginViewImpl;
import com.clouway.jobex.client.useraccess.register.RegistrationView;
import com.clouway.jobex.client.useraccess.register.RegistrationViewImpl;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

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

    bind(ActivityMapper.class).to(ApplicationActivityMapper.class).in(Singleton.class);

    bind(CompanyNameProvider.class).to(CompanyNameProviderImpl.class);

    bind(UsernameProvider.class).to(UsernameProviderImpl.class);

    bind(UserCVsView.class).to(UserCVsViewImpl.class).in(Singleton.class);

    bind(CreatingNewCVWorkflowView.class).to(CreatingNewCVWorkflowViewImpl.class).in(Singleton.class);

    bind(EditCVWorkflowView.class).to(EditCVWorkflowViewImpl.class).in(Singleton.class);

    bind(ReviewJobsPresenter.class).to(ReviewJobsPresenterImpl.class);

    bind(ReviewJobsView.class).to(ReviewJobsViewImpl.class).in(Singleton.class);

    bind(RegistrationView.class).to(RegistrationViewImpl.class).in(Singleton.class);

    bind(LoginView.class).to(LoginViewImpl.class).in(Singleton.class);

    bind(SubmittedCVsView.class).to(SubmittedCVsViewImpl.class).in(Singleton.class);

    bind(SubmittedCVsPresenter.class).to(SubmittedCVsPresenterImpl.class);

    bind(SecurityProvider.class).to(SecurityProviderImpl.class);

    bind(new TypeLiteral<Map<Class<? extends Place>, ActivityPlaceMetadata>>() {
    }).annotatedWith(Names.named("PlaceActivityMap")).toProvider(PlaceActivityMapProvider.class);
  }

  @Provides
  @Singleton
  PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  @Provides
  @Singleton
  JobExRequestFactory getJobExRequestFactory(EventBus eventBus) {
    JobExRequestFactory jobExRequestFactory = GWT.create(JobExRequestFactory.class);
    jobExRequestFactory.initialize(eventBus);
    return jobExRequestFactory;
  }


}
