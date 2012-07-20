package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.*;

import java.util.Date;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobRepositoryImpl implements JobRepository {

  private DatastoreService datastoreService;

  public JobRepositoryImpl(DatastoreService datastoreService) {
    this.datastoreService = datastoreService;
  }

  /**
   * Get list of job objects by location from the datastore
   *
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
   *
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
   *
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
   * @param job         - a job
   */
  public void saveJob(String companyName, Job job) {

    Entity entity = new Entity("Job");
    entity.setProperty("company", job.getCompany());
    entity.setProperty("position", job.getPosition());
    entity.setProperty("category", job.getCategory());
    entity.setProperty("expirationDate", job.getExpirationDate());
    entity.setProperty("location", job.getLocation());

    datastoreService.put(entity);
  }

  /**
   * Get announced jobs for given company
   *
   * @param companyName - company name
   * @return - list of announced jobs
   */
  public List<Entity> getAnnouncedJobsForCompany(String companyName) {

    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("company", Query.FilterOperator.EQUAL, "clouway"));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return preparedQuery.asList(FetchOptions.Builder.withLimit(10));
  }


  public Key[] getExpiredJobsKeys() {
    Key[] expiredJobsKeysArray;
    Query query = new Query("Job");
    query.setKeysOnly();
    query.setFilter(new Query.FilterPredicate("expirationDate", Query.FilterOperator.LESS_THAN, new Date()));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    List<Entity> expiredJobsKeys = preparedQuery.asList(FetchOptions.Builder.withDefaults());
    expiredJobsKeysArray = new Key[expiredJobsKeys.size()];
    for (int i = 0; i < expiredJobsKeys.size(); i++) {
      expiredJobsKeysArray[i] = expiredJobsKeys.get(i).getKey();
    }

    return expiredJobsKeysArray;
  }

  /**
   * Remove all job ads that are no longer valid
   */
  public void removeExpiredJobs() {
    datastoreService.delete(getExpiredJobsKeys());
  }

  public Job getJob(Long jobId) {

    Key ancestor = KeyFactory.createKey("Job", jobId);

    Entity entity = null;

    try {
      entity = datastoreService.get(ancestor);
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
    }

    return new Job(
            entity.getKey().getId(),
            entity.getProperty("company").toString(),
            entity.getProperty("position").toString(),
            entity.getProperty("category").toString(),
            entity.getProperty("location").toString(),
            (Date) entity.getProperty("expirationDate")
    );

  }


  /**
   * Delete job with given jobId
   *
   * @param jobId - jobId
   */
  public void deleteJob(Long jobId) {
    datastoreService.delete(KeyFactory.createKey("Job", jobId));
  }
}
