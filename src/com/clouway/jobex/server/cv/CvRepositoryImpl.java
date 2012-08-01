package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.applyingforjob.JobApplication;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
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
            (Date) entity.getProperty("dateOfBirth"));
  }


  public void save(String username, CV cv) {

    Entity entity;

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

  public void delete(long cvId) {
    Key key = KeyFactory.createKey(cvKind, cvId);
    service.delete(key);
  }


  /**
   * Get submitted CVs for given jobId from JobApplications
   *
   * @param jobId - a jobId
   * @param jobApplications - list of jobApplications
   * @return - list of submitted CVs
   */
  public List<CV> getSubmittedCVs(Long jobId, List<JobApplication> jobApplications) {

    List<CV> submittedCVs = new ArrayList<CV>();

    List<Key> cvKeys = getCVKeys(jobApplications);

    for (Key key : cvKeys) {
      try {
        submittedCVs.add(createCv(service.get(key)));
      } catch (EntityNotFoundException e) {
        e.printStackTrace();
      }
    }

    return submittedCVs;
  }

  private List<Key> getCVKeys(List<JobApplication> jobApplications) {

    List<Key> cvKeys = new ArrayList<Key>();

    for (JobApplication jobApplication : jobApplications) {
      cvKeys.add(KeyFactory.createKey("CV", jobApplication.getCvId()));
    }

    return cvKeys;
  }

  /**
   * Get all JobApplications by given jobId
   *
   * @param jobId - a jobId
   * @return - list of JobApplications
   */
  public List<JobApplication> getJobApplications(Long jobId) {

    Query query = new Query("JobApplication");
    query.setFilter(new Query.FilterPredicate("jobId", Query.FilterOperator.EQUAL, jobId));

    List<Entity> entityList = service.prepare(query).asList(FetchOptions.Builder.withDefaults());

    List<JobApplication> jobApplications = new ArrayList<JobApplication>();

    for (Entity anEntityList : entityList) {
      jobApplications.add(createJobApplication(anEntityList));
    }

    return jobApplications;
  }

  private JobApplication createJobApplication(Entity entity) {

    JobApplication jobApplication = new JobApplication();
    jobApplication.setJobId((Long) entity.getProperty("jobId"));
    jobApplication.setCvId((Long) entity.getProperty("cvId"));

    return jobApplication;
  }

  /**
   * Prepare new CV with empty properties and auto-generated id.
   *
   * @return - a CV
   */
  public CV prepareNewCV() {

    Entity entity = new Entity("CV");

    entity.setProperty("name", "");
    entity.setProperty("email", "");
    entity.setProperty("phoneNumber", "");
    entity.setProperty("skills", "");
    entity.setProperty("dateOfBirth", new Date());

    service.put(entity);

    return getCv(entity.getKey().getId());
  }
}
