package com.clouway.jobex.server.cv;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * Locates CV Entities.
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CVLocator extends Locator<CV, Long> {

  private CVRepository cvRepository = new CvRepositoryImpl(DatastoreServiceFactory.getDatastoreService());


  @Override
  public CV create(Class<? extends CV> clazz) {
    return new CV();

  }

  @Override
  public CV find(Class<? extends CV> clazz, Long id) {
    return cvRepository.getCv(id);
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
