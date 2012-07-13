package com.clouway.jobex.shared.entities;

/**
 * A Data Transfer Object contains the CVId with which a user applies for a job and the
 * job id to which the user applies
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class JobApplication {


  private Long id;

  private Long version;

  private Long cvId;

  private Long jobId;



  public JobApplication(Long cvId, Long jobId) {
    this.cvId = cvId;
    this.jobId = jobId;
  }

  public JobApplication() {
  }

  public Long getCvId() {
    return cvId;
  }

  public Long getJobId() {
    return jobId;
  }

  public void setCvId(Long cvId) {
    this.cvId = cvId;
  }

  public void setJobId(Long jobId) {
    this.jobId = jobId;
  }

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }
}
