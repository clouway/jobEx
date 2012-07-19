package com.clouway.jobex.client.icons;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface IconsResources extends ClientBundle {


  @Source("home.png")
  ImageResource homeIcon();

  @Source("pencil.png")
  ImageResource editIcon();

  @Source("search.png")
  ImageResource searchIcon();


  @Source("user-admin.png")
  ImageResource employerIcon();

  @Source("user-african.png")
  ImageResource employeeIcon();

  @Source("user-add.png")
  ImageResource addCv();

  @Source("chat.png")
  ImageResource announce();


}
