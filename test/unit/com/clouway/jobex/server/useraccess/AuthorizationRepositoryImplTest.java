package com.clouway.jobex.server.useraccess;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AuthorizationRepositoryImplTest {


  DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
  AuthorizationRepositoryImpl authorizationRepository = new AuthorizationRepositoryImpl(datastoreService);

  private final LocalServiceTestHelper helper =
          new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(1));

  private String kind = "User";

  private String email = "test@test.com";

  private String password = "password";

  private String id = "abc123";

  @Before
  public void setUp() {
    helper.setUp();
  }

  @Test
  public void willReturnTrueIfEntityWithGivenEmailAndTypeIsNotInTheDataStore() {

    assertThat(authorizationRepository.isNotRegister(kind, email), is(equalTo(true)));

  }

  @Test
  public void willReturnFalseIfEntityWithGivenEmailAndTypeIsInTheDataStore() {
    authorizationRepository.register(kind, email, password);

    assertThat(authorizationRepository.isNotRegister(kind, email), is(equalTo(false)));
  }

  @Test
  public void willReturnFalseIfPasswordGivenPasswordDoeNotMatch() {
    authorizationRepository.register(kind, email, "differentPassword");
    assertThat(authorizationRepository.isAuthorized(kind, email, password), is(equalTo(false)));
  }

  @Test
  public void willReturnTrueIfGivenPasswordMatch() {
    authorizationRepository.register(kind, email, password);
    assertThat(authorizationRepository.isAuthorized(kind, email, password), is(equalTo(true)));
  }

  @Test
  public void willReturnFalseIfUserIsNotListedAsAuthorized() {
    assertThat(authorizationRepository.isUserAuthorized(email, id), is(equalTo(false)));
  }

  @Test
  public void willReturnFalseIfGivenIdDoesNotMatchTheGivenUser() {
    authorizationRepository.saveAsLogged(email, kind, "wrongId123");
    assertThat(authorizationRepository.isUserAuthorized(email, id), is(equalTo(false)));
  }

  @Test
  public void willReturnTrueIfUserIsAuthorized() {
    authorizationRepository.saveAsLogged(email, kind, id);
    assertThat(authorizationRepository.isUserAuthorized(email, id), is(equalTo(true)));
  }


  @Test
  public void returnsTrueIfSIDRegistered() {

    String sid = "sid";

    authorizationRepository.saveAsLogged(email, kind, sid);

    Boolean isRegistered = authorizationRepository.isSIDRegistered(sid);

    assertThat(isRegistered, is(true));

  }


  @Test
  public void returnsFalseIfSIDRegistered() {

    String sid = "sid";

    Boolean isRegistered = authorizationRepository.isSIDRegistered(sid);

    assertThat(isRegistered, is(false));

  }

}
