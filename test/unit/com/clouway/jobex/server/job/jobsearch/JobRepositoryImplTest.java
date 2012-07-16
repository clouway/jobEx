package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
* @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
*/
public class JobRepositoryImplTest {

  DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
  JobRepository jobRepository = new JobRepositoryImpl(datastoreService);

  private final LocalServiceTestHelper helper =
          new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(1));

  private Entity job;
  private Entity job2;
  private Entity job3;

  @Before
  public void setUp() {

    helper.setUp();
    loadEntities();
  }

  private void loadEntities() {
    job = new Entity("Job");
    job.setProperty("category", "cat1");
    job.setProperty("location", "Veliko Tarnovo");
    job2 = new Entity("Job");
    job2.setProperty("category", "cat2");
    job2.setProperty("location", "Varna");
    job3 = new Entity("Job");
    job3.setProperty("category", "cat1");
    job3.setProperty("location", "Varna");
  }

  @After
  public void tearDown() {
    helper.tearDown();
  }



  @Test
  public void getAllJobAdsByLocation() {
    datastoreService.put(job);
    datastoreService.put(job2);
    datastoreService.put(job3);

    String location = "Varna";
    List<Entity> jobsInSelectedLocation = jobRepository.getAllJobsByLocation(location);

    assertThat(jobsInSelectedLocation.size(), is(equalTo(2)));
  }

  @Test
  public void getAllJobsByCategory() {

    datastoreService.put(job);
    datastoreService.put(job2);
    datastoreService.put(job3);

    String category = "cat1";
    List<Entity> jobsInSelectedCategory = jobRepository.getAllJobsByCategory(category);

    assertThat(jobsInSelectedCategory.size(), is(equalTo(2)));

  }

}
