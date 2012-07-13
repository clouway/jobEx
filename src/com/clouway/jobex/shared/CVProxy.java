package com.clouway.jobex.shared;

import com.clouway.jobex.server.cv.CVLocator;
import com.clouway.jobex.shared.entities.CV;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Client side representation of CV Entity;
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@ProxyFor(value = CV.class, locator = CVLocator.class)
public interface CVProxy extends EntityProxy {

  public Long getId();

  public Long getVersion();

  public void setId(Long id);

  public void setVersion(Long version);

  public void setName(String name);

  public void setEmail(String email);

  public void setPhoneNumber(String phoneNumber);

  public void setSkills(String skills);

  public String getName();

  public String getEmail();

  public String getPhoneNumber();

  public String getSkills();


}
