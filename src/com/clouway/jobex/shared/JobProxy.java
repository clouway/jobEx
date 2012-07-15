package com.clouway.jobex.shared;

import com.clouway.jobex.server.JobLocator;
import com.clouway.jobex.server.job.Job;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.Date;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Job.class, locator = JobLocator.class)
public interface JobProxy extends EntityProxy{

  String getCategory();

  void setCategory(String category);

  String getLocation();

  void setLocation(String location);

  Long getId();

  void setId(Long id);

  String getCompany();

  void setCompany(String company);

  String getPosition();

  void setPosition(String position);

  void setExpirationDate(Date date);

  Date getExpirationDate();
}
