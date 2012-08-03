package com.clouway.jobex.server.job;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class Job {

  private Long id;
  
  private Long version;

  @NotNull
  private String company;

  @NotNull(message = "Position cannot be empty!")
  @Size(min = 5, max = 20, message = "Position length must be 5-20 characters!")
  private String position;

  @Size(min = 1, message = "Category is not selected!")
  private String category;

  @NotNull(message = "Expiration date is not selected!")
  private Date expirationDate;

  @Size(min = 1, message = "Location is not selected!")
  private String location;

  public Job() {
  }

  public Job(Long id, String company, String position, String category, String location, Date expirationDate) {
    this.id = id;
    this.company = company;
    this.position = position;
    this.category = category;
    this.location = location;
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
