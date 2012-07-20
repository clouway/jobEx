package com.clouway.jobex.server.job;

import com.google.appengine.api.datastore.DatastoreServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class ExpiredJobsRemover extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    JobRepository jobRepository = new JobRepositoryImpl(DatastoreServiceFactory.getDatastoreService());
    jobRepository.removeExpiredJobs();
  }


}
