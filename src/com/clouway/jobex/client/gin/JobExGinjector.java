package com.clouway.jobex.client.gin;

import com.clouway.jobex.client.applyingforjob.JobApplicationPresenter;
import com.clouway.jobex.client.applyingforjob.JobApplicationView;
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

  JobApplicationPresenter jobApplicationPresenter();

  JobApplicationView jobApplicationView();
}
