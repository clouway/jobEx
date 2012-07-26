package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceServiceImplTest {

  private Job job = new Job(1l, "Company", "Position", "Category", "Location", new Date());

  private JobAnnounceService service;

  @Mock
  private JobRepository repository;

  private ValidatorFactory validatorFactory;

  private Validator validator;

  @Before
  public void setUp() {

    initMocks(this);

    validatorFactory = Validation.buildDefaultValidatorFactory();

    validator = validatorFactory.getValidator();

    service = new JobAnnounceServiceImpl(repository, validator);
  }

  @Test
  public void announcedJobsAreSavedInRepository() {

    String companyName = "company";

    //service.announceJob(companyName, job);

    verify(repository).saveJob(companyName, job);
  }

  @Test
  public void jobWithoutPositionCannotBeAnnounced() {

  }
}
