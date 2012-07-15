package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.google.appengine.api.datastore.FetchOptions.Builder.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobRepositoryImplTest {

  private final LocalServiceTestHelper service = new LocalServiceTestHelper(
          new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(1));

  private DatastoreService datastore;

  private JobRepository repository;

  @Before
  public void setUp() {
    service.setUp();
    datastore = DatastoreServiceFactory.getDatastoreService();
    repository = new JobRepositoryImpl(datastore);
  }

  @After
  public void tearDown() {
    service.tearDown();
  }

  @Test
  public void saveJobInRepository() {

    Job job = new Job("Company", "Position", "Category", new Date());
    repository.saveJob(job.getCompany(), job);

    Query query = new Query("Job");

    PreparedQuery preparedQuery = datastore.prepare(query);

    int numberOfSavedJobs = preparedQuery.countEntities(withDefaults());
    Entity savedJob = preparedQuery.asSingleEntity();

    assertThat(numberOfSavedJobs, is(equalTo(1)));

    assertThat((String) savedJob.getProperty("company"), is(equalTo(job.getCompany())));
    assertThat((String) savedJob.getProperty("position"), is(equalTo(job.getPosition())));
    assertThat((String) savedJob.getProperty("category"), is(equalTo(job.getCategory())));
    assertThat((Date) savedJob.getProperty("expirationDate"), is(equalTo(job.getExpirationDate())));
  }
}
