package com.clouway.jobex.server.jobapplication;

import com.clouway.jobex.shared.entities.CV;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CVLocator extends Locator<CV, Long> {

  @Override
  public CV create(Class<? extends CV> clazz) {
    return new CV();

  }

  @Override
  public CV find(Class<? extends CV> clazz, Long id) {
    return new CV();
  }

  @Override
  public Class<CV> getDomainType() {
    return CV.class;
  }


  @Override
  public Long getId(CV domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(CV domainObject) {
    return 1;
  }
}
