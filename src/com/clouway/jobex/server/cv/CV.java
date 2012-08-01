package com.clouway.jobex.server.cv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A Data transfer object that contains CV information created by the user that wants to apply for a job
 *
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CV implements Serializable {


  private Long id;

  private Long version;

  @NotNull(message = "Name cannot be empty!")
  @Size(min = 3, max = 20, message = "Name length must be from 3 to 20 characters!")
  private String name;

  private String email;

  @NotNull(message = "Phone number cannot be empty!")
  @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain only digits. Length 10 digits!")
  private String phoneNumber;

  @NotNull(message = "Enter some skills!")
  @Size(min = 5, max = 50, message = "Enter some more skills")
  private String skills;

  @NotNull(message = "Date of birth is not selected!")
  private Date dateOfBirth;

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getSkills() {
    return skills;
  }

  public CV() {
  }

  public CV(Long id) {
    this.id = id;
  }

  public CV(Long id, String name, String email, String phoneNumber, String skills,Date dateOfBirth) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.skills = skills;
    this.dateOfBirth = dateOfBirth;
  }

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}
