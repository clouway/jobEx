package com.clouway.jobex.server.jobsreview;

import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.jobsearch.DomainObjectConverter;
import com.google.appengine.api.datastore.Entity;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class JobsReviewServiceImplTest {

  private Mockery context = new JUnit4Mockery();

  private JobRepository repository = context.mock(JobRepository.class);

  private final String companyName = "cloyway";

  private DomainObjectConverter converter = context.mock(DomainObjectConverter.class);

  private JobsReviewService service;

  @Before
  public void setUp() {
    service = new JobsReviewServiceImpl(repository, converter);
  }

  @Test
  public void getAnnouncedJobsForCompany() {

    final List<Entity> announcedJobs = new ArrayList<Entity>();

    context.checking(new Expectations() {{

      oneOf(repository).getAnnouncedJobsForCompany(companyName);
      will(returnValue(announcedJobs));

      oneOf(converter).convertToDomainsFrom(announcedJobs);
    }});

    service.getAnnouncedJobsForCompany(companyName);
  }
}
