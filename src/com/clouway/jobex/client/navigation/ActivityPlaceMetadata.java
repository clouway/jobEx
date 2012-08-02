package com.clouway.jobex.client.navigation;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ActivityPlaceMetadata<P extends Place, A extends Activity> {

   public A getActivity(P p);
}
