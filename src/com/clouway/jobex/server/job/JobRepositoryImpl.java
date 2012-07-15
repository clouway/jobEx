package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
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

  @Override
  public List<Job> getAllJobsByLocation(String location) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  @Override
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

  @Override
  public List<Job> getAllJobsByCategory(String category) {
    Query query = new Query("Job");
    query.setFilter(new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category));
    
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  @Override
  public List<Job> getAllJobsByLocationAndCategory(String location, String category) {
    Query query = new Query("Job");
    query.setFilter(Query.CompositeFilterOperator.and(new Query.FilterPredicate("location", Query.FilterOperator.EQUAL, location), new Query.FilterPredicate("category", Query.FilterOperator.EQUAL, category)));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToListOfJobObjects(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }
}
