package com.clouway.jobex.server.job;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobRepository {

  void saveJob(String companyName, Job job);
}
