package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobObjectConverter implements DomainObjectConverter<Job> {

  /**
   * Convert datastore entities into job objects
   * @param listOfEntities
   * @return
   */
  public List<Job> convertToDomainsFrom(List<Entity> listOfEntities) {
    List<Job> listOfJobs = new ArrayList<Job>();
    for (Entity entity : listOfEntities) {
      Job job = new Job();
      job.setId(entity.getKey().getId());
      job.setLocation((String) entity.getProperty("location"));
      job.setCategory((String) entity.getProperty("category"));
      job.setPosition((String) entity.getProperty("position"));
      listOfJobs.add(job);
    }
    return listOfJobs;
  }
}
