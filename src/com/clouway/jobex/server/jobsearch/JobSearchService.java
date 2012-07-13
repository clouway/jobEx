package com.clouway.jobex.server.jobsearch;



import com.clouway.jobex.server.job.Job;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchService {

  List<Job> search(Job job);
}
