package com.clouway.jobex.server.jobapplication;

import com.clouway.jobex.shared.entities.CV;
import com.clouway.jobex.shared.entities.JobApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplicationServiceImpl implements JobApplicationService {
  @Override
  public void applyForJob(JobApplication application) {

    System.out.println("so far so good .... !");

  }

  @Override
  public List<CV> fetchCreatedCVs() {

    ArrayList<CV> cvs = new ArrayList<CV>();

    cvs.add(new CV(1l));

    cvs.add(new CV(3l));

    return cvs;

  }
}
