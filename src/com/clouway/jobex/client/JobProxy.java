package com.clouway.jobex.client;

import com.clouway.jobex.server.JobLocator;
import com.clouway.jobex.shared.entities.Job;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Job.class, locator = JobLocator.class)
public interface JobProxy extends EntityProxy{

  public String getCategory();

  public void setCategory(String category);

  public String getLocation();

  public void setLocation(String location);

  public Long getId();

  public void setId(Long id);

}
