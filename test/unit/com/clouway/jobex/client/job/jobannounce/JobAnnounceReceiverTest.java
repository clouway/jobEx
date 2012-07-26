package com.clouway.jobex.client.job.jobannounce;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
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
  public void loadSpecificPageIfThereAreNoErrors() {

    receiver.onSuccess(new ArrayList<String>());

    verify(view).goToReviewJobsPlace();
  }

  @Test
  public void showOccurredErrors() {

    List<String> listOfErrors = new ArrayList<String>();
    listOfErrors.add("Position can't be empty");

    receiver.onSuccess(listOfErrors);

    verify(view).showOccurredErrors(listOfErrors);
    verify(view, never()).goToReviewJobsPlace();
  }
}
