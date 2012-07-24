package com.clouway.jobex.client.confirmation;

/**
 * Confirmation interface provide method used
 * to confirm the execution of a request by the client
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface Confirmation {

  /**
   * Confirm execution of a request by the client
   *
   * @return - true/false depending on whether the client wants to execute the request
   */
  boolean isConfirmed();
}
