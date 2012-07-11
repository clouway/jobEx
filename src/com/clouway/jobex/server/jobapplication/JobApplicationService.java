package com.clouway.jobex.server.jobapplication;

import com.clouway.jobex.shared.entities.CV;
import com.clouway.jobex.shared.entities.JobApplication;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobApplicationService {

  void applyForJob(JobApplication application);

  List<CV> fetchCreatedCVs();
}
