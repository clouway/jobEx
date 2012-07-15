package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobRepositoryImpl implements JobRepository {

  private final DatastoreService datastore;

  public JobRepositoryImpl(DatastoreService datastore) {

    this.datastore = datastore;
  }

  public void saveJob(String companyName, Job job) {

    Key companyKey = KeyFactory.createKey("Company", companyName);

    Entity entity = new Entity("Job", companyKey);
    entity.setProperty("company", job.getCompany());
    entity.setProperty("position", job.getPosition());
    entity.setProperty("category", job.getCategory());
    entity.setProperty("expirationDate", job.getExpirationDate());

    datastore.put(entity);
  }
}
