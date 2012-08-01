package com.clouway.jobex.client.security;

import com.clouway.jobex.client.navigation.NavigationMenuController;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompanyRegisteredEventHandlerImpl implements CompanyRegisteredEventHandler {

  private NavigationMenuController controller;

  private UserCredentialsLocalStorage securityProvider;

  @Inject
  public CompanyRegisteredEventHandlerImpl(NavigationMenuController controller, UserCredentialsLocalStorage securityProvider) {

    this.controller = controller;

    this.securityProvider = securityProvider;

  }

  @Override
  public void onCompanyRegistered(CompanyRegisteredEvent event) {
    securityProvider.setCompanyCredentials(event.getEmail(), event.getSID());
  }

}
