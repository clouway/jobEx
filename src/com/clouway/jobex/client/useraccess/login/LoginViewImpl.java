package com.clouway.jobex.client.useraccess.login;

import com.clouway.jobex.client.job.jobsearch.JobSearchPlace;
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
public class LoginViewImpl extends Composite implements LoginView {
  private LoginPresenter presenter;

  private PlaceController placeController;

  @Override
  public void setPresenter(LoginPresenter presenter) {

    this.presenter = presenter;
  }

  interface LoginViewImplUiBinder extends UiBinder<HTMLPanel, LoginViewImpl> {
  }

  private static LoginViewImplUiBinder ourUiBinder = GWT.create(LoginViewImplUiBinder.class);

  @UiField
  Button loginButton;
  @UiField
  ListBox loginType;
  @UiField
  TextBox email;
  @UiField
  PasswordTextBox password;

  @Inject
  public LoginViewImpl(PlaceController placeController) {
    this.placeController = placeController;
    initWidget(ourUiBinder.createAndBindUi(this));

    loginType.addItem("JobHunter", "User");
    loginType.addItem("Company", "Company");
  }

  @Override
  public void disableLoginButton() {
    loginButton.setEnabled(false);
  }

  @Override
  public void enableLoginButton() {
    loginButton.setEnabled(true);
  }

  @Override
  public void unsuccessfulLoginMessage() {
    Window.alert("Incorrect email or password.");
  }

  @Override
  public String getEmail() {
    return email.getText();
  }

  @Override
  public String getPassword() {
    return password.getText();
  }

  @Override
  public String getLoginType() {
    return loginType.getValue(loginType.getSelectedIndex());
  }

  @Override
  public void goToSearchPlace() {
    placeController.goTo(new JobSearchPlace());
  }


  @UiHandler("loginButton")
  public void onLoginButtonClicked(ClickEvent event) {
    if (presenter != null) {
      presenter.onLoginButtonClicked();
    }
  }
}