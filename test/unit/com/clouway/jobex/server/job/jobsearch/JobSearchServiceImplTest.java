package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RunWith(JMock.class)
public class JobSearchServiceImplTest {

  private Mockery context = new JUnit4Mockery();

  private JobRepository jobRepository = context.mock(JobRepository.class);
  private DomainObjectConverter jobConverter = context.mock(DomainObjectConverter.class);
  private JobSearchServiceImpl jobSearchService;
  private Job job;
  private List<Entity> returnedEntities = new ArrayList<Entity>();

  @Before
  public void setUp(){
    jobSearchService = new JobSearchServiceImpl(jobRepository, jobConverter);
    job = new Job();
  }


  @Test
  public void searchForJobsByCategory(){

    job.setLocation("all locations");
    job.setCategory("cat1");

    context.checking(new Expectations(){{
      one(jobRepository).getAllJobsByCategory(job.getCategory());
      will(returnValue(returnedEntities));
      oneOf(jobConverter).convertToDomainsFrom(returnedEntities);
    }
    });

    jobSearchService.search(job);
  }

  @Test
  public void searchForJobsOByLocationAndCategory(){

    job.setLocation("loc1");
    job.setCategory("cat1");

    context.checking(new Expectations(){{
      one(jobRepository).getAllJobsByLocationAndCategory(job.getLocation(), job.getCategory());
      will(returnValue(returnedEntities));
      oneOf(jobConverter).convertToDomainsFrom(returnedEntities);
    }
    });

    jobSearchService.search(job);
  }

}
