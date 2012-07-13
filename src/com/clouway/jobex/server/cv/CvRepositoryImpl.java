package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvRepositoryImpl implements CVRepository {

  private DatastoreService service;

  private final String cvKind = "CV";

  public CvRepositoryImpl(DatastoreService service) {

    this.service = service;
  }

  @Override
  public List<CV> getCreatedCVs(String username) {

    Key key = KeyFactory.createKey("User", username);

    Query query = new Query(cvKind, key);

    Iterable<Entity> entities = service.prepare(query).asIterable();

    List<CV> cvList = new ArrayList<CV>();

    for (Entity entity : entities) {

      CV cv = new CV(entity.getKey().getId(),
              (String) entity.getProperty("name"),
              (String) entity.getProperty("email"),
              (String) entity.getProperty("phoneNumber"),
              (String) entity.getProperty("skills"));
      cvList.add(cv);
    }
    return cvList;
  }

  public void save(String username, CV cv) {
    Key key = KeyFactory.createKey("User", username);
    Entity entity = new Entity(cvKind,key);
    entity.setProperty("name", cv.getName());
    entity.setProperty("email", cv.getEmail());
    entity.setProperty("phoneNumber", cv.getPhoneNumber());
    entity.setProperty("skills", cv.getSkills());
    service.put(entity);
  }
}
