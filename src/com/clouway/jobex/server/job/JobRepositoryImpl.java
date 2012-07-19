package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobRepositoryImpl implements JobRepository{

  private DatastoreService datastoreService;

  public JobRepositoryImpl(DatastoreService datastoreService) {
    this.datastoreService = datastoreService;
  }

  /**
   * Get list of job objects by location from the datastore
   * @param location a job location
   * @return list with all jobs that have the current location parameter
   */
  public List<Entity> getAllJobsByLocation(String location) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return preparedQuery.asList(FetchOptions.Builder.withDefaults());
  }



  /**
   * Get list of job objects by category from the datastore
   * @param category a job category
   * @return list with all jobs that have the current category parameter
   */
  public List<Entity> getAllJobsByCategory(String category) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category));
    
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return preparedQuery.asList(FetchOptions.Builder.withDefaults());
  }

  /**
   * Get list of job objects by location and category from the datastore
   * @param location a job location
   * @param category a job category
   * @return list with all jobs that have the current location and category parameters
   */
  public List<Entity> getAllJobsByLocationAndCategory(String location, String category) {
    Query query = new Query("Job");
    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location),
            new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category)));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return preparedQuery.asList(FetchOptions.Builder.withDefaults());
  }

  /**
   * Save announced job for given company
   *
   * @param companyName - company announcing the job
   * @param job - a job
   */
  public void saveJob(String companyName, Job job) {
    Key companyKey = KeyFactory.createKey("Company", companyName);

    Entity entity = new Entity("Job", companyKey);
    entity.setProperty("company", job.getCompany());
    entity.setProperty("position", job.getPosition());
    entity.setProperty("category", job.getCategory());
    entity.setProperty("expirationDate", job.getExpirationDate());
    entity.setProperty("location",job.getLocation());

    datastoreService.put(entity);
  }

  /**
   * Get announced jobs for given company
   *
   * @param companyName - company name
   * @return - list of announced jobs
   */
  public List<Entity> getAnnouncedJobsForCompany(String companyName) {

    Key companyKey = KeyFactory.createKey("Company", companyName);

    Query query = new Query("Job");
    query.setAncestor(companyKey);
    query.setFilter(new Query.FilterPredicate("company", Query.FilterOperator.EQUAL, "clouway"));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return preparedQuery.asList(FetchOptions.Builder.withLimit(10));
  }
}
