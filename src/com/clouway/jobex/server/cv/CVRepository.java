package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CVRepository {

  List<CV> getCreatedCVs();

  void save(CV cv);

}
