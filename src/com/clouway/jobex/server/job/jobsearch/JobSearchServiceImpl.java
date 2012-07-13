package com.clouway.jobex.server.job.jobsearch;

import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.shared.entities.Job;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobSearchServiceImpl implements JobSearchService {

//  @Inject(optional = true)
  private JobRepository jobRepository;
//
//  public JobSearchServiceImpl(){
//
//  }
  @Inject
  public JobSearchServiceImpl(JobRepository jobRepository){
    this.jobRepository = jobRepository;
  }

  public List<Job> search(Job job) {
    
    List<Job> jobList = null;
    jobList = new ArrayList<Job>();

    if(job.getLocation().equals("loc1")){
    Job job1 = new Job();
    job1.setId(1l);
    job1.setLocation("varna");
    job1.setCategory("cat1");
    jobList.add(job1);
    }


    Job job2 = new Job();
    job2.setId(2l);
    job2.setLocation("vadsana");
    job2.setCategory("cat2");
    jobList.add(job2);



    String location = job.getLocation();
    String category = job.getCategory();

    if(("").equals(location) && !("").equals(category)){
      jobList = jobRepository.getAllJobsByCategory(category);
    }
    if(!("").equals(location) && ("").equals(category)) {
      jobList = jobRepository.getAllJobsByLocation(location);
    }
    if(!("").equals(location) && !("").equals(category)){
      jobList = jobRepository.getAllJobsByLocationAndCategory(location, category);
    }

    return jobList;
  }
}