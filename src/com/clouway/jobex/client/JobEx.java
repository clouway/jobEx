package com.clouway.jobex.client;


import com.clouway.jobex.client.applyingforjob.ApplyForJobEvent;
import com.clouway.jobex.client.applyingforjob.JobApplicationPresenter;
import com.clouway.jobex.client.gin.JobExGinjector;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class JobEx implements EntryPoint {

  /**
   * This is the entry point metho
   * d.
   */
  public void onModuleLoad() {

    SimplePanel display = new SimplePanel();

    Place place = new JobSearchPlace();

    JobExGinjector injector = GWT.create(JobExGinjector.class);

    EventBus eventBus = injector.injectEventBus();

    JobApplicationPresenter presenter = injector.jobApplicationPresenter();

    eventBus.addHandler(ApplyForJobEvent.TYPE, presenter);

    PlaceController placeController = injector.injectPlaceController();

    ActivityMapper activityMapper = injector.injectActivityMapper();

    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);

    activityManager.setDisplay(display);

    JobExPlaceHistoryMapper placeHistoryMapper = GWT.create(JobExPlaceHistoryMapper.class);

    PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);

    placeHistoryHandler.register(placeController, eventBus, place);

    RootPanel.get().add(display);

    placeHistoryHandler.handleCurrentHistory();

    placeController.goTo(new JobSearchPlace());

//    JobExRequestFactory factory = GWT.create(JobExRequestFactory.class);
//
//    EditCVWorkflowView editCVWorkflowView = new EditCVWorkflowViewImpl();
//
//    EditCvWorkflow workflow = new EditCvWorkflow(factory, editCVWorkflowView, new UsernameProvider() {
//      @Override
//      public String getUsername() {
//        return "username";
//      }
//
//
//      @Override
//      public void setUsername(String username) {
//        //To change body of implemented methods use File | Settings | File Templates.
//      }
//    });
  }
}