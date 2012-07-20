package com.clouway.jobex.server.cv;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CVRepository {

  List<CV> getCreatedCVs(String username);

  void save(String username, CV cv);

  CV getCv(long cvId);

  void delete(long cvId);
}
