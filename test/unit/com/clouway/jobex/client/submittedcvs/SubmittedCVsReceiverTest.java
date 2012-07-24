package com.clouway.jobex.client.submittedcvs;

import com.clouway.jobex.shared.CVProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RunWith(JMock.class)
public class SubmittedCVsReceiverTest {

  private Receiver<List<CVProxy>> receiver;

  private Mockery context = new JUnit4Mockery();

  private SubmittedCVsView view = context.mock(SubmittedCVsView.class);

  private CVProxy cvProxy = context.mock(CVProxy.class);

  @Before
  public void setUp() {
    receiver = new SubmittedCVsReceiver(view);
  }

  @Test
  public void showAllSubmittedCVs() {

    final List<CVProxy> submittedCVs = new ArrayList<CVProxy>();
    submittedCVs.add(cvProxy);

    context.checking(new Expectations(){{
      oneOf(view).showSubmittedCVs(submittedCVs);
    }});

    receiver.onSuccess(submittedCVs);
  }

  @Test
  public void showNoSubmittedCVsNotification() {

    final List<CVProxy> submittedCVs = new ArrayList<CVProxy>();

    context.checking(new Expectations() {{
      oneOf(view).showNoSubmittedCVsNotification();
      oneOf(view).goToReviewJobPlace();
    }});

    receiver.onSuccess(submittedCVs);
  }
}
