package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NotActionBoundException extends RuntimeException {
  public NotActionBoundException(String errorMessage) {
    super(errorMessage);
  }
}
