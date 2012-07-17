package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobEditor extends Composite implements Editor<JobProxy> {

  interface JobAnnounceEditorUiBinder extends UiBinder<Widget, JobEditor> {}

  private static JobAnnounceEditorUiBinder uiBinder = GWT.create(JobAnnounceEditorUiBinder.class);

  @UiField
  TextBox company;

  @UiField
  TextBox position;

  @Ignore
  @UiField
  ListBox selectCategory;

  @Ignore
  @UiField
  ListBox selectLocation;

  @UiField
  @Ignore
  DateBox date;

  LeafValueEditor<Date> expirationDate = new LeafValueEditor<Date>() {
    public void setValue(Date value) {
      date.setValue(value);
    }

    public Date getValue() {
      return date.getValue();
    }
  };

  LeafValueEditor<String> category = new LeafValueEditor<String>() {
    public void setValue(String value) {
      selectCategory.setValue(selectCategory.getSelectedIndex(), value);
    }

    public String getValue() {
      return selectCategory.getValue(selectCategory.getSelectedIndex());
    }
  };

  LeafValueEditor<String> location = new LeafValueEditor<String>() {
    public void setValue(String value) {
      selectLocation.setValue(selectLocation.getSelectedIndex(), value);
    }

    public String getValue() {
      return selectLocation.getValue(selectLocation.getSelectedIndex());
    }
  };

  public JobEditor() {
    initWidget(uiBinder.createAndBindUi(this));

    selectCategory.addItem("Banking");
    selectCategory.addItem("Engineering");
    selectCategory.addItem("IT");
    selectCategory.addItem("Franchise");
    selectCategory.addItem("Marketing");

    selectLocation.addItem("Burgas");
    selectLocation.addItem("Plovdiv");
    selectLocation.addItem("Sofia");
    selectLocation.addItem("Varna");
    selectLocation.addItem("Veliko Tarnovo");
  }
}