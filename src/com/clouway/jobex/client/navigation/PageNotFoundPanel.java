package com.clouway.jobex.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class PageNotFoundPanel extends Composite {


  interface PageNotFoundViewUiBinder extends UiBinder<HTMLPanel, PageNotFoundPanel> {
  }

  private static PageNotFoundViewUiBinder ourUiBinder = GWT.create(PageNotFoundViewUiBinder.class);

  public PageNotFoundPanel() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(this);
  }

}