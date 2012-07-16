package com.clouway.jobex.server.job.jobsearch;

import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface DomainObjectConverter<T> {
  
  List<T> convertToDomainsFrom(List<Entity> listOfEntities);
}
