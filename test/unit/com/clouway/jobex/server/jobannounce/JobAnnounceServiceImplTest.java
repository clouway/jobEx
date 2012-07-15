package com.clouway.jobex.server.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceService;
import com.clouway.jobex.server.job.jobannounce.JobAnnounceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceServiceImplTest {

  private Job job = new Job("Company", "Position", "Category", new Date());

  private JobAnnounceService service;

  @Mock
  private JobRepository repository;

  @Before
  public void setUp() {
    initMocks(this);

    service = new JobAnnounceServiceImpl(repository);
  }

  @Test
  public void announcedJobsAreSavedInRepository() {

    String companyName = "company";

    service.announceJob(companyName, job);

    verify(repository).saveJob(companyName, job);
  }
}
