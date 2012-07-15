package com.clouway.jobex.server.cv;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvsServiceImpl implements CvsService {


  private final CVRepository cvRepository;

  /**
   * Creates New CVServiceImpl
   *
   * @param cvRepository: Cv Repository
   */
  public CvsServiceImpl(CVRepository cvRepository) {
    this.cvRepository = cvRepository;
  }

  @Override
  public List<CV> fetchCreatedCVs(String username) {
    return cvRepository.getCreatedCVs(username);
  }

  @Override
  public void create(String username,CV cv) {

  }
}
