package com.clouway.jobex.client.security;

import java.util.List;

/**
 * Stores All Permission, represented by strings, that the user can use.
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserPermissions {

  /**
   * Sets a list of user permissions
   *
   * @param permissions:user permissions.
   */
  public void setPermissions(List<String> permissions);

  /**
   * checks if permission is previously been set
   *
   * @param permission: the permission to check
   * @return :true if permission is previously been set,false otherwise;
   */
  public boolean isPermitted(String permission);



}
