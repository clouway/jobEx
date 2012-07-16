package com.clouway.jobex.client.applyingforjob;


import com.clouway.jobex.shared.CVProxy;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobApplicationView extends IsWidget {

  /**
   * notifies user of Successful job appliance when the user applies for a job
   */
  void notifyUserOfSuccessfulAppliance();


  /**
   * Show errors on the user View ( Display)
   *
   * @param errors a list of strings represents errors;
   */
  void showErrors(List<String> errors);

  /**
   * Notifies a user of an error while communicatoin with the server.
   */
  void notifyUserOfCommunicationError();


  /**
   * Sets view presenter;
   *
   * @param presenter
   */
  void setPresenter(JobApplicationPresenter presenter);

  /**
   * Lists users created CVs to allow the user to select one to apply for a job .
   *
   * @param cvs a list of CV to be
   */
  void showCreatedCVs(List<CVProxy> cvs);

  /**
   * Redirects the view to Creating new CV Form;
   */
  void goToCreateNewCVForm();

  void setJobId(Long id);


}
