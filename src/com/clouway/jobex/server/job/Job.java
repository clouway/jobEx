package com.clouway.jobex.server.job;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Job {

  private Long id;
  private Long version;

  private String company;
  private String position;
  private String category;
  private Date expirationDate;

  public Job() {
  }

  public Job(String company, String position, String category, Date expirationDate) {
    this.company = company;
    this.position = position;
    this.category = category;
    this.expirationDate = expirationDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getVersion() {
    return version;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCompany() {
    return company;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPosition() {
    return position;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }
}