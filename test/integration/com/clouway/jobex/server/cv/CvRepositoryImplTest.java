package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.AppEngineTestCase;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvRepositoryImplTest extends AppEngineTestCase {

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  public CvRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }


  private String username ="adio";

  private CvRepositoryImpl repository = new CvRepositoryImpl(service);

  @Test
  public void returnsAllSavedCVs() {

    CV cv = new CV();

    repository.save(username,cv);

    List<CV> cvs = repository.getCreatedCVs(username);

    assertThat(cvs, is(notNullValue()));

    assertThat(cvs.size(), is(equalTo(1)));

  }


  @Test
  public void tryWithAnotherCV() {

    CV cv = new CV();

    cv.setEmail("mail@mail.com");


    repository.save(username, cv);

    List<CV> cvs = repository.getCreatedCVs(username);


    assertThat(cvs, is(notNullValue()));

    assertThat(cvs.size(), is(equalTo(1)));

    assertThat(cvs.get(0).getEmail(), is(equalTo("mail@mail.com")));

  }



}
