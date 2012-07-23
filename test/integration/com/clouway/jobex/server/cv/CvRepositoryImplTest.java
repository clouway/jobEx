package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.AppEngineTestCase;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.clouway.jobex.server.applyingforjob.JobApplication;
import com.clouway.jobex.server.applyingforjob.JobApplicationRepository;
import com.clouway.jobex.server.applyingforjob.JobApplicationRepositoryImpl;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvRepositoryImplTest extends AppEngineTestCase {

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  public CvRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }


  private String username = "adio";

  private CvRepositoryImpl repository = new CvRepositoryImpl(service);

  private JobApplicationRepository applicationRepository = new JobApplicationRepositoryImpl(service);

  @Test
  public void returnsAllSavedCVs() {

    CV cv = new CV();

    repository.save(username, cv);

    List<CV> cvs = repository.getCreatedCVs(username);

    assertThat(cvs, is(notNullValue()));

    assertThat(cvs.size(), is(equalTo(1)));

  }


  @Test
  public void tryWithAnotherCV() {

    CV cv = new CV();

    String email = "mail@mail.com";

    cv.setEmail(email);

    repository.save(email, cv);

    List<CV> cvs = repository.getCreatedCVs(email);

    assertThat(cvs, is(notNullValue()));

    assertThat(cvs.size(), is(equalTo(1)));

    assertThat(cvs.get(0).getEmail(), is(equalTo("mail@mail.com")));

  }

  @Test
  public void fetchCvByUsernameAndCvId() {


    String email = "mail@mail.com";

    CV cv = new CV(1l, "name", email, "12345678", "skills !", new Date(), "male");

    repository.save(email, cv);

    CV returnedCv = repository.getCv(1l);

    assertThat(returnedCv, is(notNullValue()));

    assertThat(returnedCv.getId(), is(equalTo(1l)));

    assertThat(returnedCv.getEmail(), is(equalTo("mail@mail.com")));

    assertThat(returnedCv.getName(), is(equalTo("name")));

    assertThat(returnedCv.getPhoneNumber(), is(equalTo("12345678")));

    assertThat(returnedCv.getSkills(), is(equalTo("skills !")));

  }


  @Test
  public void cvIsUpdatedWhenSavingCvWithPersistedId() {

    String mail = "mail@mail.com";

    CV cv = new CV(1l, "name", "mail@mail.com", "1234567", "skills_1", new Date(), "female");

    repository.save(mail, cv);

    CV returnedCv = repository.getCv(1l);

    returnedCv.setName("anotherName");
    returnedCv.setPhoneNumber("7654321");
    returnedCv.setSkills("skill_2");

    repository.save(mail, returnedCv);

    CV updatedCv = repository.getCv(1l);

    assertThat(updatedCv, is(notNullValue()));

    assertThat(updatedCv.getName(), is(equalTo("anotherName")));

    assertThat(updatedCv.getPhoneNumber(), is(equalTo("7654321")));

    assertThat(updatedCv.getSkills(), is(equalTo("skill_2")));

  }


  @Test
  public void deletesCVFromRepository() {

    repository.save("username", new CV());

    List<CV> cvs = repository.getCreatedCVs("username");

    repository.delete(cvs.get(0).getId());

    List<CV> returnedCv = repository.getCreatedCVs("username");

    assertThat(returnedCv, is(notNullValue()));

    assertThat(returnedCv.size(), is(equalTo(0)));

  }


  @Test
  public void getAllJobApplicationsByJobId() {

    Long jobId = 1l;
    Long cvId = 1l;

    JobApplication firstJobApplication = new JobApplication(cvId, jobId);

    applicationRepository.saveJobApplication(firstJobApplication);

    List<JobApplication> jobApplicationList = repository.getJobApplications(jobId);

    assertThat(jobApplicationList, is(notNullValue()));
    assertThat(jobApplicationList.size(), is(equalTo(1)));
    assertThat(jobApplicationList.get(0).getJobId(), is(equalTo(firstJobApplication.getJobId())));
  }

  @Test
  public void getSubmittedCVsForGivenJob() {

    Long jobId = 1l;

    // Save new CV
    CV cv = new CV(1l, "Ivan", "ivan@mail.com", "123456", "skills", new Date(), "gender");
    repository.save(cv.getEmail(), cv);

    // Create new JobApplication
    JobApplication jobApplication = new JobApplication(cv.getId(), jobId);
    applicationRepository.saveJobApplication(jobApplication);

    // Get all JobApplications
    List<JobApplication> jobApplications = repository.getJobApplications(jobId);

    List<CV> submittedCVs = repository.getSubmittedCVs(jobId, jobApplications);
    assertThat(submittedCVs.size(), is(equalTo(1)));

    assertThat(submittedCVs.get(0).getId(), is(equalTo(cv.getId())));
  }
}
