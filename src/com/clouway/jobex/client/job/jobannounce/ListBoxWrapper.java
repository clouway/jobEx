package com.clouway.jobex.client.job.jobannounce;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface ListBoxWrapper {

  void setIndex(int index);

  int getIndex();

  void setValue(int index, String value);

  String getValue(int index);
}
