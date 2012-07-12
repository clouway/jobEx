package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;

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
  public List<CV> fetchCreatedCVs() {
    return cvRepository.getCreatedCVs();
  }

  @Override
  public void create(CV cv) {

  }
}
