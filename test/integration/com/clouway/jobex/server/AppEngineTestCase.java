package com.clouway.jobex.server;

import com.google.appengine.tools.development.testing.LocalServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AppEngineTestCase {

  private LocalServiceTestHelper helper;

  public AppEngineTestCase(LocalServiceTestConfig config) {
    helper = new LocalServiceTestHelper(config);
  }

  @Before
  public void setUp() throws Exception {
    helper.setUp();
  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

}
