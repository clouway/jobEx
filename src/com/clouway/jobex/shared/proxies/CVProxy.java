package com.clouway.jobex.shared.proxies;

import com.clouway.jobex.server.jobapplication.CVLocator;
import com.clouway.jobex.shared.entities.CV;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@ProxyFor(value = CV.class, locator = CVLocator.class)
public interface CVProxy extends EntityProxy {

  public Long getId();

  public Long getVersion();

  public void setId(Long id);

  public void setVersion(Long version);


}
