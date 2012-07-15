package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceEditor extends Composite implements Editor<JobProxy> {

  interface JobAnnounceEditorUiBinder extends UiBinder<Widget, JobAnnounceEditor> {}
  private static JobAnnounceEditorUiBinder uiBinder = GWT.create(JobAnnounceEditorUiBinder.class);

  @UiField
  TextBox company;

  @UiField
  TextBox position;

  @UiField
  TextBox category;

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

  public JobAnnounceEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}