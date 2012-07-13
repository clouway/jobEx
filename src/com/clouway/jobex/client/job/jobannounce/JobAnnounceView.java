package com.clouway.jobex.client.job.jobannounce;

import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.shared.JobProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface JobAnnounceView {

  public interface Presenter {

    /**
     * Get JobRequestContext
     *
     * @return a JobRequestContext
     */
    JobExRequestFactory.JobRequestContext getJobRequestContext();

    /**
     * Get JobProxy
     *
     * @return a JobProxy
     */
    JobProxy getJobProxy();

    /**
     * Create request which will be fired with given JobProxy and Receiver
     *
     * @param jobProxy a jobProxy
     * @param receiver a receiver
     */
    void createRequest(JobProxy jobProxy, Receiver<Void> receiver);

    /**
     * Announce new Job, i.e. fire request
     */
    void announceJob();
  }

  /**
   * Set presenter, used to drive the view
   *
   * @param presenter a presenter
   */
  void setPresenter(Presenter presenter);

  /**
   * Go to MainPlace
   */
  void goToMainPlace();
}
