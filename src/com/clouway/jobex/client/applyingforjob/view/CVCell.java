package com.clouway.jobex.client.applyingforjob.view;

import com.clouway.jobex.shared.proxies.CVProxy;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CVCell extends AbstractCell<CVProxy> {

  @Override
  public void render(Context context, CVProxy value, SafeHtmlBuilder sb) {

    if (value == null) {
      return;
    }

    sb.appendHtmlConstant("<table>");
    // Add the name and address.
    sb.appendHtmlConstant("<td style='font-size:95%;'>");
//    sb.appendEscaped(value.getFullName());
    sb.appendHtmlConstant("CV");
    sb.appendHtmlConstant("</td></tr><tr><td>");
//    sb.appendEscaped(value.getAddress());
    sb.appendHtmlConstant("</td></tr></table>");
  }

}
