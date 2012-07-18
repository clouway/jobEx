package com.clouway.jobex.client.job.jobannounce;

import com.google.gwt.user.client.ui.ListBox;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ListBoxWrapperImpl implements ListBoxWrapper {

  private ListBox listBox;

  public ListBoxWrapperImpl(ListBox listBox) {
    this.listBox = listBox;
  }

  public void setIndex(int index) {
    listBox.setSelectedIndex(index);
  }

  public int getIndex() {
    return listBox.getSelectedIndex();
  }

  public void setValue(int index, String value) {
    listBox.setValue(index, value);
  }

  public String getValue(int index) {
    return listBox.getValue(index);
  }
}
