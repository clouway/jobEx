package com.clouway.jobex.server.job;

import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobLocator extends Locator<Job, Long> {

  public Job create(Class<? extends Job> clazz) {

    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    return null;
  }

  public Job find(Class<? extends Job> clazz, Long id) {
    return null;
  }

  public Class<Job> getDomainType() {
    return Job.class;
  }

  public Long getId(Job domainObject) {
    return domainObject.getId();
  }

  public Class<Long> getIdType() {
    return Long.class;
  }

  public Object getVersion(Job domainObject) {
    return domainObject.getVersion();
  }
}
