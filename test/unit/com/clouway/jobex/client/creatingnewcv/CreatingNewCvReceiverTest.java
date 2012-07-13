package com.clouway.jobex.client.creatingnewcv;

import com.clouway.jobex.client.creatingnewcv.view.CreatingNewCVWorkflowView;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCvReceiverTest {


  @Mock
  CreatingNewCVWorkflowView view;

  CreatingNewCvReceiver receiver;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    receiver = new CreatingNewCvReceiver(view);
  }


  @Test
  public void notifiesUserWhenCvIsSuccessfullyCreated() {


    receiver.onSuccess(null);

    verify(view).notifyUserOfSuccessfulCVCreation();

  }


  @Test
  public void notifiesUserOfConnectionError() {

    receiver.onFailure(new ServerFailure());

    verify(view).notifyUserOfConnectionError();
  }


}
