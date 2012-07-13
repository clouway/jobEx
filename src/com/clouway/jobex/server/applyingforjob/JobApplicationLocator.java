package com.clouway.jobex.server.applyingforjob;

import com.clouway.jobex.shared.entities.JobApplication;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Locates JobApplication Entities;
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationLocator extends Locator<JobApplication, Long> {

  @Override
  public JobApplication create(Class<? extends JobApplication> clazz) {
    return new JobApplication();
  }

  @Override
  public JobApplication find(Class<? extends JobApplication> clazz, Long id) {
    return new JobApplication();
  }

  @Override
  public Class<JobApplication> getDomainType() {
    return JobApplication.class;
  }

  @Override
  public Long getId(JobApplication domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }


  @Override
  public Object getVersion(JobApplication domainObject) {
    return domainObject.getVersion();
  }
}
