package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.client.applyingforjob.ErrorMessages;
import com.clouway.jobex.server.cv.CVRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationServiceImplTest {


  @Mock
  JobApplicationRepository jobApplicationRepository;

  @Mock
  CVRepository cvRepository;

  @Mock
  ErrorMessages errorMessages;

  JobApplicationServiceImpl service;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    service = new JobApplicationServiceImpl(jobApplicationRepository, errorMessages);

  }

  @Test
  public void applyingForJobSavesJobApplicationInRepository() {

    Long cvId = 12l;

    Long jobId = 34l;

    JobApplication jobApplication = new JobApplication(cvId, jobId);

    service.applyForJob(jobApplication);

    verify(jobApplicationRepository).saveJobApplication(jobApplication);

  }


  @Test
  public void sameJobApplicationIsNotSavedTwiceWhenApplying() {

    Long cvId = 12l;

    Long jobId = 34l;

    String username = "user";

    String error = "some error !! ";

    JobApplication jobApplication = new JobApplication(cvId, jobId,username);

    when(errorMessages.jobApplicationIsPreviouslySubmitted()).thenReturn(error);

    when(jobApplicationRepository.getJobApplication(cvId, jobId,username)).thenReturn(jobApplication);

    List<String> errors = service.applyForJob(jobApplication);

    verify(jobApplicationRepository).getJobApplication(cvId, jobId,username);

    verify(jobApplicationRepository, never()).saveJobApplication(jobApplication);

    verify(errorMessages).jobApplicationIsPreviouslySubmitted();

    assertThat(errors, is(notNullValue()));

    assertThat(errors.size(), is(equalTo(1)));

  }

}
