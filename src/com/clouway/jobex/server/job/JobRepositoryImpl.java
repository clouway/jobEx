package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobRepositoryImpl implements JobRepository{

//  @Inject
  private DatastoreService datastoreService;

  @Inject
  public JobRepositoryImpl(DatastoreService datastoreService) {
    this.datastoreService = datastoreService;
  }

  /**
   * Get list of job objects by location from the datastore
   * @param location a job location
   * @return list with all jobs that have the current location parameter
   */
  public List<Job> getAllJobsByLocation(String location) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  public List<Job> convertToListOfJobObjects(List<Entity> listOfEntities) {
    List<Job> listOfJobs = new ArrayList<Job>();
    for (Entity entity : listOfEntities) {
      Job job = new Job();
      job.setId(entity.getKey().getId());
      job.setLocation((String) entity.getProperty("location"));
      job.setCategory((String) entity.getProperty("position"));
      listOfJobs.add(job);
    }
    return listOfJobs;
  }

  /**
   * Get list of job objects by category from the datastore
   * @param category a job category
   * @return list with all jobs that have the current category parameter
   */
  public List<Job> getAllJobsByCategory(String category) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category));
    
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  /**
   * Get list of job objects by location and category from the datastore
   * @param location a job location
   * @param category a job category
   * @return list with all jobs that have the current location and category parameters
   */
  public List<Job> getAllJobsByLocationAndCategory(String location, String category) {
    Query query = new Query("Job");
    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location),
            new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category)));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  public void saveJob(String companyName, Job job) {
    Key companyKey = KeyFactory.createKey("Company", companyName);

    Entity entity = new Entity("Job", companyKey);
    entity.setProperty("company", job.getCompany());
    entity.setProperty("position", job.getPosition());
    entity.setProperty("category", job.getCategory());
    entity.setProperty("expirationDate", job.getExpirationDate());

    datastoreService.put(entity);
  }
}
