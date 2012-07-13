package com.clouway.jobex.shared;

import com.clouway.jobex.server.job.Job;
import com.clouway.jobex.server.job.JobLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.Date;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@ProxyFor(value = Job.class, locator = JobLocator.class)
public interface JobProxy extends EntityProxy {

  void setCompany(String company);

  String getCompany();

  void setPosition(String position);

  String getPosition();

  void setCategory(String category);

  String getCategory();

  void setExpirationDate(Date date);

  Date getExpirationDate();
}
