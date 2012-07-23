package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.google.appengine.api.datastore.*;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static com.google.appengine.api.datastore.FetchOptions.Builder.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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

    Job job = new Job(1l, "Company", "Position", "Category", "Location", new Date());

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




  @Test
  public void getAnnouncedJobsForCompany() {

    String companyName = "clouway";

    repository.saveJob(companyName, new Job(1l, companyName, "position1", "category1", "location1", new Date()));
    repository.saveJob(companyName, new Job(2l, companyName, "position2", "category2", "location2", new Date()));

    assertThat(repository.getAnnouncedJobsForCompany(companyName).size(), is(equalTo(2)));
  }

  @Test
  public void getJobByGivenJobId() {

    String companyName = "clouway";
    String location = "Veliko Tarnovo";
    String category = "IT";
    String position = "Developer";
    Date expirationDate = new Date();

    Entity entity = new Entity("Job", 1l);
    entity.setProperty("company", companyName);
    entity.setProperty("location", location);
    entity.setProperty("category", category);
    entity.setProperty("position", position);
    entity.setProperty("expirationDate", expirationDate);

    datastore.put(entity);

    Job savedJob = repository.getJob(1l);

    assertThat("clouway", is(equalTo(savedJob.getCompany())));
    assertThat("Veliko Tarnovo", is(equalTo(savedJob.getLocation())));
  }

  @Test
  public void deleteJobWithGivenJobId() {

    Long jobId = 1l;
    String company = "clouway";
    String location = "Veliko Tarnovo";

    Entity entity = new Entity("Job", jobId);
    entity.setProperty("company", company);
    entity.setProperty("location", location);
    datastore.put(entity);

    List<Entity> announcedJobs = repository.getAnnouncedJobsForCompany(company);
    assertThat(announcedJobs.size(), is(equalTo(1)));

    repository.deleteJob(jobId);
    List<Entity> returnedJobs = repository.getAnnouncedJobsForCompany(company);
    assertThat(returnedJobs.size(), is(equalTo(0)));
  }
}
