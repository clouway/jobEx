package com.clouway.jobex.server.job.jobannounce;

import com.clouway.jobex.server.job.Job;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceService {

  void announceJob(String companyName, Job job);

}
