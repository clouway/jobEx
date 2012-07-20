package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.AppEngineTestCase;
import com.clouway.jobex.server.job.Job;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobObjectConverterTest extends AppEngineTestCase {
  DomainObjectConverter<Job> jobConverter = new JobObjectConverter();

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  public JobObjectConverterTest() {
    super(new LocalDatastoreServiceTestConfig());
  }


  @Test
  public void convertAnEntityInJobObject() {
    List<Entity> entityList = new ArrayList<Entity>();
    List<Job> jobList;

    Entity jobEntity = new Entity("Job");
    jobEntity.setProperty("location", "loc1");
    jobEntity.setProperty("category", "cat1");
    jobEntity.setProperty("position", "pos1");
    Entity jobEntity2 = new Entity("Job");
    jobEntity2.setProperty("location", "loc2");
    jobEntity2.setProperty("category", "cat2");
    jobEntity2.setProperty("position", "loc2");
    entityList.add(jobEntity);
    entityList.add(jobEntity2);

    jobList = jobConverter.convertToDomainsFrom(entityList);

    assertThat(jobList.size(), is(equalTo(entityList.size())));
    assertThat(jobList.get(0).getLocation(), is(equalTo("loc1")));
    assertThat(jobList.get(0).getCategory(), is(equalTo("cat1")));
    assertThat(jobList.get(0).getPosition(), is(equalTo("pos1")));
  }
}
