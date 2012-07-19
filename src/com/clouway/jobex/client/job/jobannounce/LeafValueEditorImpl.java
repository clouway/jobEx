package com.clouway.jobex.client.job.jobannounce;

import com.google.gwt.editor.client.LeafValueEditor;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LeafValueEditorImpl implements LeafValueEditor<String> {

  private ListBoxWrapper listBoxWrapper;

  public LeafValueEditorImpl(ListBoxWrapper listBoxWrapper) {
    this.listBoxWrapper = listBoxWrapper;
  }

  public void setValue(String value) {

    int index = listBoxWrapper.getIndex();

    if (value == null) {
      value = listBoxWrapper.getValue(index);
    }

    listBoxWrapper.setValue(index, value);
  }

  public String getValue() {
    return listBoxWrapper.getValue(listBoxWrapper.getIndex());
  }
}
