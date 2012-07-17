package com.clouway.jobex.client.job.jobannounce;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceReceiverTest {

  private JobAnnounceReceiver receiver;

  @Mock
  private JobAnnounceView view;

  @Before
  public void setUp() {
    initMocks(this);

    receiver = new JobAnnounceReceiver(view);
  }

  @Test
  public void onSuccessGoToMainPlace() {

    receiver.onSuccess(null);

    verify(view).goToSearchPlace();
  }
}
