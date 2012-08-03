package com.clouway.jobex.client.useraccess.register;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
import com.clouway.jobex.shared.AccountType;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RegistrationViewImpl extends Composite implements RegistrationView {

  private RegistrationPresenter presenter;
  private final PlaceController controller;

  interface RegistrationViewImplUiBinder extends UiBinder<HTMLPanel, RegistrationViewImpl> {
  }

  private static RegistrationViewImplUiBinder ourUiBinder = GWT.create(RegistrationViewImplUiBinder.class);

  @UiField
  Button registerButton;

  @UiField
  TextBox email;

  @UiField
  PasswordTextBox password;

  @UiField
  ListBox typesList;

  @Inject
  public RegistrationViewImpl(PlaceController controller) {
    this.controller = controller;

    initWidget(ourUiBinder.createAndBindUi(this));

    typesList.addItem("JobHunter", AccountType.USER);

    typesList.addItem("Company", AccountType.COMPANY);

  }

  public void setPresenter(RegistrationPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void successfulRegistrationMessage() {
    Window.alert("You have successfully registered!");
  }

  @Override
  public void registrationErrorMessage() {
    Window.alert("Registration failed. You have entered invalid or already existing email.");
  }


  @Override
  public void disableRegisterButton() {
    registerButton.setEnabled(false);
  }

  @Override
  public void enableRegisterButton() {
    registerButton.setEnabled(true);
  }

  @Override
  public void goToSearch() {
    controller.goTo((new JobSearchPlace()));
  }

  @UiHandler("registerButton")
  public void onRegisterButtonClicked(ClickEvent event) {
    presenter.register(typesList.getValue(typesList.getSelectedIndex()), email.getText(), password.getText());
  }
}