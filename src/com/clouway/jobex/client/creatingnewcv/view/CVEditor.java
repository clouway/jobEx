package com.clouway.jobex.client.creatingnewcv.view;

import com.clouway.jobex.shared.CVProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CVEditor extends Composite implements Editor<CVProxy> {

  interface CVEditorUiBinder extends UiBinder<HTMLPanel, CVEditor> {
  }

  private static CVEditorUiBinder ourUiBinder = GWT.create(CVEditorUiBinder.class);

  @UiField
  TextBox name;

  @UiField
  TextBox email;

  @UiField
  TextBox phoneNumber;

  @UiField
  TextBox skills;

  public CVEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }
}