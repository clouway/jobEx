package com.clouway.jobex.server.jobsearch;

import com.clouway.jobex.shared.entities.Job;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface JobSearchService {

  List<Job> search(Job job);
}
