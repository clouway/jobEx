package com.clouway.jobex.server.jobsearch;

import com.clouway.jobex.shared.entities.Job;
import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobRepository {
  List<Job> getAllJobsByLocation(String location);
  
  List<Job> convertToListOfJobObjects(List<Entity> listOfEntities);

  List<Job> getAllJobsByCategory(String category);

  List<Job> getAllJobsByLocationAndCategory(String location, String category);
}
