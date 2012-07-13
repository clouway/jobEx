package com.clouway.jobex.client.jobsearch;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class InstanceMatcher<T> extends BaseMatcher<T> {

  private List<T> instanceHistory = new LinkedList<T>();
  private T instance;

  public T getInstance() {
    return instance;
  }

  public List<T> getInstanceHistory() {
    return instanceHistory;
  }

  public boolean matches(Object o) {
    try {
      instance = (T) o;

      instanceHistory.add(instance);
      return true;
    } catch (ClassCastException ex) {
      return false;
    }
  }

  @Override
  public void describeTo(Description description) {

  }
}
