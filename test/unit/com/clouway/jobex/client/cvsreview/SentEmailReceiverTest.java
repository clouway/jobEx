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
public class SentEmailReceiverTest {

  private Mockery context = new JUnit4Mockery();

  private ReviewCVView view = context.mock(ReviewCVView.class);

  private SentEmailReceiver sentEmailReceiver;

  @Before
  public void setUp() {
    sentEmailReceiver = new SentEmailReceiver(view);
  }

  @Test
  public void showSentEmailNotification() {

    context.checking(new Expectations(){{
      oneOf(view).showSentEmailNotification();
    }});

    sentEmailReceiver.onSuccess(null);
  }
}
