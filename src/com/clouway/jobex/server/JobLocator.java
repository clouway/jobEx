package com.clouway.jobex.server;

import com.clouway.jobex.shared.entities.Job;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class JobLocator extends Locator<Job, Long> {
  @Override
  public Job create(Class<? extends Job> clazz) {
    try {
      return Job.class.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Job find(Class<? extends Job> clazz, Long id) {
    return null;
  }

  @Override
  public Class<Job> getDomainType() {
    return Job.class;
  }

  @Override
  public Long getId(Job domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(Job domainObject) {
    return domainObject.getVersion();
  }
}
