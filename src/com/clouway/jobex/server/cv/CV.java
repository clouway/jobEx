package com.clouway.jobex.server.cv;

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

  private String name;

  private String email;

  private String phoneNumber;

  private String skills;

  private Date dateOfBirth;

  private String gender;



  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


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

  public CV(Long id, String name, String email, String phoneNumber, String skills,Date dateOfBirth,String gender) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.skills = skills;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
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
