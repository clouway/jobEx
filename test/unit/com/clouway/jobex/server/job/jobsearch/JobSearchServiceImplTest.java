package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RunWith(JMock.class)
public class JobSearchServiceImplTest {

  private Mockery context = new JUnit4Mockery();

  private JobRepository jobRepository = context.mock(JobRepository.class);
  private JobSearchServiceImpl jobSearchService;
  private Job job;

  @Before
  public void setUp(){
    jobSearchService = new JobSearchServiceImpl(jobRepository);
    job = new Job();
  }

  @Test
  public void searchForJobsOnlyByLocationWhenCategoryIsEmptyString(){
    job.setLocation("loc1");
    job.setCategory("");
    context.checking(new Expectations(){{
      one(jobRepository).getAllJobsByLocation(job.getLocation());
    }
    });

    jobSearchService.search(job);
  }

  @Test
  public void searchForJobsOnlyByCategoryWhenLocationIsEmptyString(){

    job.setLocation("");
    job.setCategory("cat1");

    context.checking(new Expectations(){{
      one(jobRepository).getAllJobsByCategory(job.getCategory());
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
    }
    });

    jobSearchService.search(job);
  }

}
