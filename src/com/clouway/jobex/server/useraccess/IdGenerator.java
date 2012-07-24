package com.clouway.jobex.server.useraccess;

import java.util.UUID;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class IdGenerator implements Generator{
  @Override
  public String generateId() {
    return String.valueOf(UUID.randomUUID());
  }
}
