package com.clouway.jobex.client.job.jobannounce;

import com.google.gwt.editor.client.LeafValueEditor;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class LeafValueEditorImplTest {

  private LeafValueEditor<String> editor;

  private Mockery context = new JUnit4Mockery();

  private ListBoxWrapper listBoxWrapper = context.mock(ListBoxWrapper.class);

  @Before
  public void setUp() {
    editor = new LeafValueEditorImpl(listBoxWrapper);
  }

  @Test
  public void setIndexAndValue() {

    final int index = 0;
    final String value = "Sofia";

    context.checking(new Expectations() {{
      oneOf(listBoxWrapper).getIndex();
      oneOf(listBoxWrapper).setValue(index, value);
    }});

    editor.setValue(value);
  }

  @Test
  public void checkForNullParameter() {

    context.checking(new Expectations() {{
      oneOf(listBoxWrapper).getIndex();
      will(returnValue(0));

      oneOf(listBoxWrapper).getValue(0);
      will(returnValue("0"));

      oneOf(listBoxWrapper).setValue(0, "0");
    }});

    editor.setValue(null);
  }

  @Test
  public void getValue() {

    final int index = 0;

    context.checking(new Expectations() {{
      oneOf(listBoxWrapper).getIndex();
      will(returnValue(index));

      oneOf(listBoxWrapper).getValue(index);
    }});

    editor.getValue();
  }
}
