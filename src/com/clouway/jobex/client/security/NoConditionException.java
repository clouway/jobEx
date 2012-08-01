package com.clouway.jobex.client.security;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NoConditionException extends RuntimeException {

  public NoConditionException(String errorMessages) {
    super(errorMessages);
  }

}
