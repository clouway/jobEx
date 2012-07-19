package com.clouway.jobex.server.cv;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Date;
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

    Query query = new Query(cvKind);

    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, username));

    Iterable<Entity> entities = service.prepare(query).asIterable();

    List<CV> cvList = new ArrayList<CV>();

    for (Entity entity : entities) {
      cvList.add(createCv(entity));
    }
    return cvList;
  }

  private CV createCv(Entity entity) {
    return new CV(entity.getKey().getId(),
            (String) entity.getProperty("name"),
            (String) entity.getProperty("email"),
            (String) entity.getProperty("phoneNumber"),
            (String) entity.getProperty("skills"),
            (Date)entity.getProperty("dateOfBirth"),
            (String)entity.getProperty("gender"));
  }


  public void save(String username, CV cv) {

    Entity entity = null;

    if (cv.getId() != null) {
      Key cvKey = KeyFactory.createKey(cvKind, cv.getId());
      try {
        entity = service.get(cvKey);
      } catch (EntityNotFoundException e) {
        entity = new Entity(cvKind);
      }
    } else {
      entity = new Entity(cvKind);
    }

    entity.setProperty("name", cv.getName());
    entity.setProperty("email", username);
    entity.setProperty("phoneNumber", cv.getPhoneNumber());
    entity.setProperty("skills", cv.getSkills());
    entity.setProperty("username", username);
    entity.setProperty("dateOfBirth", cv.getDateOfBirth());
    entity.setProperty("gender", cv.getGender());
    service.put(entity);
  }

  @Override
  public CV getCv(long cvId) {

    Key cvKey = KeyFactory.createKey(cvKind, cvId);

    try {
      Entity entity = service.get(cvKey);
      return createCv(entity);
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return null;
    }

  }
}
