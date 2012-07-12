package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;
import com.google.appengine.api.datastore.DatastoreService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvRepositoryImpl implements CVRepository {

  private String username;

  private DatastoreService service;

  private final String cvKind = "CV";

  public CvRepositoryImpl(String username, DatastoreService service) {

    this.username = username;

    this.service = service;

  }

  @Override
  public List<CV> getCreatedCVs() {
    return new ArrayList<CV>() {{
      add(new CV());
    }};
  }

  public void save(CV cv) {
  }
}
