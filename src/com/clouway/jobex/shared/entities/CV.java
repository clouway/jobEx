package com.clouway.jobex.shared.entities;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CV implements Serializable {



  private Long id;

  private Long version;



  public CV() {
  }

  public CV(Long id) {
    this.id = id;
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
