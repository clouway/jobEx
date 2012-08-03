package com.clouway.jobex.client.cv;

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
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCvReceiverTest {


  @Mock
  CreatingNewCVWorkflowView view;

  CreatingNewCvReceiver receiver;

  @Captor
  ArgumentCaptor<List<String>> violationsCaptor;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    receiver = new CreatingNewCvReceiver(view);
  }

  @Test
  public void afterSuccessfulRequestGoToSelectCVPlace() {

    receiver.onSuccess(null);

    verify(view).reset();
    verify(view).goToSelectCv();
  }

  @Test
  public void showConstraintViolations() {

    receiver.onConstraintViolation(new HashSet<ConstraintViolation<?>>());

    verify(view).showConstraintViolations(violationsCaptor.capture());
  }
}
