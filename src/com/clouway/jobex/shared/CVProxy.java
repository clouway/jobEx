package com.clouway.jobex.shared;

import com.clouway.jobex.server.cv.CVLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Client side representation of CV Entity;
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@ProxyFor(value = com.clouway.jobex.server.cv.CV.class, locator = CVLocator.class)
public interface CVProxy extends EntityProxy {

  Long getId();

  Long getVersion();

  void setId(Long id);

  void setVersion(Long version);

  void setName(String name);

  void setEmail(String email);

  void setPhoneNumber(String phoneNumber);

  void setSkills(String skills);

  String getName();

  String getEmail();

  String getPhoneNumber();

  String getSkills();


}
