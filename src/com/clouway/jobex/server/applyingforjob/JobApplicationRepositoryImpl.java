package com.clouway.jobex.server.applyingforjob;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationRepositoryImpl implements JobApplicationRepository {

  private final String jobApplicationKind = "JobApplication";

  private final String cvIdProperty = "cvId";

  private final String jobIdProperty = "jobId";

  private final String username = "username";

  private final DatastoreService service;

  public JobApplicationRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public void saveJobApplication(JobApplication jobApplication) {

    Entity entity = new Entity(jobApplicationKind);

    entity.setProperty(jobIdProperty, jobApplication.getJobId());

    entity.setProperty(cvIdProperty, jobApplication.getCvId());

    entity.setProperty(username, jobApplication.getUser());

    service.put(entity);

  }

  @Override
  public JobApplication getJobApplication(Long cvId, Long jobId) {

    JobApplication jobApplication = null;

    Query query = new Query(jobApplicationKind);

    query.setFilter(Query.CompositeFilterOperator.and(new Query.FilterPredicate(cvIdProperty, Query.FilterOperator.EQUAL, cvId)
            , new Query.FilterPredicate(jobIdProperty, Query.FilterOperator.EQUAL, jobId)));

    Entity entity = service.prepare(query).asSingleEntity();

    if (entity != null) {
      jobApplication = new JobApplication((Long) entity.getProperty(cvIdProperty), (Long) entity.getProperty(jobIdProperty));
    }
    return jobApplication;
  }

}
