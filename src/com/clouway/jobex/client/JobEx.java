package com.clouway.jobex.client;


import com.clouway.jobex.client.applyingforjob.ApplyForJobEvent;
import com.clouway.jobex.client.applyingforjob.JobApplicationPresenter;
import com.clouway.jobex.client.applyingforjob.JobApplicationView;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflow;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflowView;
import com.clouway.jobex.client.creatingnewcv.CreatingNewCVWorkflowViewImpl;
import com.clouway.jobex.client.gin.JobExGinjector;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchViewImpl;
import com.clouway.jobex.client.navigation.JobExPlaceHistoryMapper;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.IsWidget;
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


    /*EventBus eventBus = new SimpleEventBus();

    JobExRequestFactory requestFactory = GWT.create(JobExRequestFactory.class);

    requestFactory.initialize(eventBus);

    JobExRequestFactory.JobRequestContext requestContext = requestFactory.jobRequestContext();

    CreatingNewCVWorkflowView view = new CreatingNewCVWorkflowViewImpl();

    CreatingNewCVWorkflow creatingNewCVWorkflow = new CreatingNewCVWorkflow(view, requestFactory, new UsernameProvider() {
      @Override
      public String getUsername() {
        return "Username";
      }

      @Override
      public void setUsername(String username) {

      }
    });
    view.setWorkFlow(creatingNewCVWorkflow);
    RootPanel.get().add((IsWidget) view);*/

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
//                RootPanel.get().add(new JobSearchViewImpl(placeController));
    placeHistoryHandler.handleCurrentHistory();

    placeController.goTo(new JobSearchPlace());
  }
}