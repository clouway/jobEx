package com.clouway.jobex.client.creatingnewcv;

import com.clouway.jobex.client.creatingnewcv.view.CreatingNewCVWorkflowView;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCvReceiver extends Receiver<Void> {


  private final CreatingNewCVWorkflowView view;

  public CreatingNewCvReceiver(CreatingNewCVWorkflowView view) {

    this.view = view;
  }

  @Override
  public void onFailure(ServerFailure error) {
    view.notifyUserOfConnectionError();
  }

  @Override
  public void onSuccess(Void response) {
    view.notifyUserOfSuccessfulCVCreation();
  }

}
