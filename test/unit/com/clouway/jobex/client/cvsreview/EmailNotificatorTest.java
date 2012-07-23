package com.clouway.jobex.client.cvsreview;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class EmailNotificatorTest {

  private Mockery context = new JUnit4Mockery();

  private ReviewCVView view = context.mock(ReviewCVView.class);

  private EmailNotificator emailNotificator;

  @Before
  public void setUp() {
    emailNotificator = new EmailNotificator(view);
  }

  @Test
  public void showSentEmailNotification() {

    context.checking(new Expectations(){{
      oneOf(view).showSentEmailNotification();
    }});

    emailNotificator.onSuccess(null);
  }
}
