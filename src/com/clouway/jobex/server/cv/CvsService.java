package com.clouway.jobex.server.cv;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CvsService {

  /**
   * Fetches All created CV by the specified user;
   * @return A list of CV contains all created Cv by the user;
   */
  List<CV> fetchCreatedCVs(String username);

  void create(String username,CV cv);



}
