package com.clouway.jobex.server.cv;

import com.clouway.jobex.server.AppEngineTestCase;
import com.clouway.jobex.shared.entities.CV;
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


  private String  email;

  private CvRepositoryImpl repository = new CvRepositoryImpl(email, service);

  @Test
  public void returnsAllSavedCVs() {

    CV cv = new CV();

    repository.save(cv);

    List<CV> cvs = repository.getCreatedCVs();

    assertThat(cvs, is(notNullValue()));

    assertThat(cvs.size(), is(equalTo(1)));

  }




}
