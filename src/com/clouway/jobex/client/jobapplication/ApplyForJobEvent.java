package com.clouway.jobex.client.jobapplication;

import com.google.gwt.event.shared.GwtEvent;

/**
 * An event tha is fired when users selects a job and candidate for it.
 * the event must contain the Job id which the user selected.
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ApplyForJobEvent extends GwtEvent<ApplyForJobEventHandler> {
  public static Type<ApplyForJobEventHandler> TYPE = new Type<ApplyForJobEventHandler>();
  private final Long jobId;

  public ApplyForJobEvent(Long jobId) {
    this.jobId = jobId;
  }

  public Long getJobId() {
    return jobId;
  }

  public Type<ApplyForJobEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ApplyForJobEventHandler handler) {
    handler.onApplyForJob(this);
  }
}
