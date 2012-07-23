package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.cv.UserCVsPresenter;
import com.clouway.jobex.client.cv.UserCVsView;
import com.clouway.jobex.client.cvsreview.ReviewCVPresenterImpl;
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
}
