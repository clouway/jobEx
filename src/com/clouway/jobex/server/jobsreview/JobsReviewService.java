package com.clouway.jobex.server.jobsreview;

import com.clouway.jobex.server.job.Job;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobsReviewService {

  List<Job> getAnnouncedJobsForCompany(String companyName);
}
