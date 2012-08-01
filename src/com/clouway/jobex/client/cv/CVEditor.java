package com.clouway.jobex.client.cv;

import com.clouway.jobex.shared.CVProxy;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import java.util.Date;

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
  TextBox phoneNumber;

  @UiField
  TextBox skills;

  @UiField
  @Ignore
  DateBox datePicker;


  LeafValueEditor<Date> dateOfBirth = new LeafValueEditor<Date>() {

    DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy/MM/dd");

    @Override
    public void setValue(Date value) {
      datePicker.setValue(value);
    }

    @Override
    public Date getValue() {
      return datePicker.getValue();
    }
  };

  public CVEditor() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}