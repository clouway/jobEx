package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.server.cv.CVRepository;
<<<<<<< HEAD
=======
>>>>>>> commit
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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

  JobApplicationServiceImpl service;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    service = new JobApplicationServiceImpl(jobApplicationRepository);

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

    JobApplication jobApplication = new JobApplication(cvId, jobId);

    when(jobApplicationRepository.getJobApplication(cvId, jobId)).thenReturn(jobApplication);

    service.applyForJob(jobApplication);

    verify(jobApplicationRepository).getJobApplication(cvId, jobId);

    verify(jobApplicationRepository, never()).saveJobApplication(jobApplication);

  }




}
