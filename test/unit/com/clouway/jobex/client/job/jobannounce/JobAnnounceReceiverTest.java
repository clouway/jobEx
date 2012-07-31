package com.clouway.jobex.client.job.jobannounce;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceReceiverTest {

  private JobAnnounceReceiver receiver;

  @Mock
  private JobAnnounceView view;

  @Captor
  ArgumentCaptor<List<String>> listWithViolations;

  @Before
  public void setUp() {
    initMocks(this);
    receiver = new JobAnnounceReceiver(view);
  }

  @Test
  public void showReviewJobsPlaceOnSuccess() {

    receiver.onSuccess(null);

    verify(view).reset();
    verify(view).goToReviewJobsPlace();
  }

  @Test
  public void showConstraintViolations() {

    receiver.onConstraintViolation(new HashSet<ConstraintViolation<?>>());

    verify(view).showConstraintViolations(listWithViolations.capture());
  }
}
