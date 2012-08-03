package com.clouway.jobex.client.cv;

import com.google.web.bindery.requestfactory.shared.Receiver;
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
public class EditCVWorkflowReceiverTest {

  private Receiver<Void> receiver;

  @Mock
  private EditCVWorkflowView view;

  @Captor
  ArgumentCaptor<List<String>> constraintViolations;

  @Before
  public void setUp() {

    initMocks(this);

    receiver = new EditCVWorkflowReceiver(view);
  }

  @Test
  public void afterEditOfCvGoToPreviewCVPlace() {

    receiver.onSuccess(null);

    verify(view).reset();
    verify(view).goToCvPreview();
  }

  @Test
  public void showConstraintViolations() {

    receiver.onConstraintViolation(new HashSet<ConstraintViolation<?>>());

    verify(view).showConstraintViolations(constraintViolations.capture());
  }
}
