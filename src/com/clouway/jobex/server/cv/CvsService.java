package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CvsService {

  /**
   * Fetches All created CV by the specified user;
   *
   * @return A list of CV contains all created Cv by the user;
   */
  List<CV> fetchCreatedCVs();

}
