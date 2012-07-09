package com.clouway.jobex.client.jobsreview;

import com.clouway.jobex.shared.entities.Job;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface JobReviewView {

  void showFetchedJobs(ArrayList<Job> returnedJobs);
}
