package com.clouway.jobex.server.cv;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvsServiceImpl implements CvsService {


  private final CVRepository repository;

  /**
   * Creates New CVServiceImpl
   *
   * @param repository: Cv Repository
   */
  public CvsServiceImpl(CVRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<CV> fetchCreatedCVs(String username) {
    return repository.getCreatedCVs(username);
  }

  @Override
  public void add(String username, CV cv) {
    repository.save(username, cv);
  }

  @Override
  public CV fetchCreatedCv(String username, Long cvId) {
    List<CV> cvs = repository.getCreatedCVs(username);
    for (CV cv : cvs) {
      if (cv.getId().equals(cvId)) {
        return cv;
      }
    }
    return null;
  }


}
