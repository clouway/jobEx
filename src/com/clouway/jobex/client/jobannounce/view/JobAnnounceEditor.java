package com.clouway.jobex.client.jobannounce.view;

import com.clouway.jobex.shared.entities.Job;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class JobAnnounceEditor extends Composite implements Editor<Job> {

  interface JobAnnounceEditorUiBinder extends UiBinder<Widget, JobAnnounceEditor> {}

  private static JobAnnounceEditorUiBinder uiBinder = GWT.create(JobAnnounceEditorUiBinder.class);

  @UiField
  TextBox company;

  @UiField
  TextBox position;

  @UiField
  TextBox category;

  @UiField
  Button cancel;

  @UiField
  Button announce;

  public JobAnnounceEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}