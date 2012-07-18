package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.server.AppEngineTestCase;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationRepositoryImplTest extends AppEngineTestCase {


  public JobApplicationRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  JobApplicationRepositoryImpl repository = new JobApplicationRepositoryImpl(service);

  @Test
  public void saveJobApplicationInJobApplicationRepository() {

    Long cvId = 12l;

    Long jobId = 32l;

    String username = "username";

    JobApplication application = new JobApplication(cvId, jobId, username);

    repository.saveJobApplication(application);

    JobApplication savedJobApplication = repository.getJobApplication(cvId, jobId, username);

    assertThat(savedJobApplication, is(notNullValue()));

    assertThat(savedJobApplication.getJobId(), is(equalTo(jobId)));

    assertThat(savedJobApplication.getCvId(), is(equalTo(cvId)));

    assertThat(savedJobApplication.getUser(), is(equalTo(username)));

  }



  @Test
  public void returnsNullIfJobApplicationWithTheSpecifiedCvAndJobIdDoesNotExist() {

    JobApplication application = repository.getJobApplication(12l, 123l, "username");

    assertThat(application, is(nullValue()));

  }
}
