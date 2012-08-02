package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.client.security.UserPermissions;
import com.clouway.jobex.shared.Permissions;
import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ButtonCellFactory {

  UserPermissions actions;

  @Inject
  public ButtonCellFactory(UserPermissions actions) {
    this.actions = actions;
  }

  @Inject
  public MyButtonCell createButtonCell() {
    MyButtonCell myButtonCell = new MyButtonCell();
    if (!actions.isPermitted(Permissions.APPLY_FOR_JOB)) {
      myButtonCell.setEnabled(false);
    }
    return myButtonCell;
  }


  public class MyButtonCell extends AbstractSafeHtmlCell<String> {

    private String isDisabled = "";

    public MyButtonCell() {
      this(SimpleSafeHtmlRenderer.getInstance());
    }

    public MyButtonCell(SafeHtmlRenderer<String> stringSafeHtmlRenderer) {
      super(stringSafeHtmlRenderer, "click", "keydown");
    }


    @Override
    protected void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
      sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\"" + isDisabled + ">");
      if (data != null) {
        sb.append(data);
      }
      sb.appendHtmlConstant("</button>");
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
      super.onBrowserEvent(context, parent, value, event, valueUpdater);
      if ("click".equals(event.getType())) {
        EventTarget eventTarget = event.getEventTarget();
        if (!Element.is(eventTarget)) {
          return;
        }
        if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {

          onEnterKeyDown(context, parent, value, event, valueUpdater);
        }
      }
    }

    @Override
    protected void onEnterKeyDown(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
      if (valueUpdater != null) {
        valueUpdater.update(value);
      }
    }

    public void setEnabled(boolean disabled) {

      if (disabled) {
        isDisabled = "";
      } else {
        isDisabled = "disabled=\"disabled\"";
      }
    }
  }
}
