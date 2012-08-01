package com.clouway.jobex.client.navigation;

import com.clouway.jobex.client.cv.CreateCvPlace;
import com.clouway.jobex.client.cv.PreviewCvPlace;
import com.clouway.jobex.client.job.jobannounce.JobAnnouncePlace;
import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.client.jobsreview.ReviewJobsPlace;
import com.google.gwt.place.shared.Place;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MenuItemsProviderImpl implements MenuItemsProvider {


  @Override
  public Map<String, Place> getUserItems() {
    Map<String, Place> placeMap = new HashMap<String, Place>();
    placeMap.put("Home", new JobSearchPlace());
    placeMap.put("Craete new CV", new CreateCvPlace());
    placeMap.put("Preview CV", new PreviewCvPlace());
    return placeMap;
  }

  @Override
  public Map<String, Place> getCompanyItems() {
    Map<String, Place> placeMap = new HashMap<String, Place>();
    placeMap.put("Home", new JobSearchPlace());
    placeMap.put("Announce new JOB", new JobAnnouncePlace());
    placeMap.put("Preview Jobs", new ReviewJobsPlace());
    return placeMap;
  }


}
