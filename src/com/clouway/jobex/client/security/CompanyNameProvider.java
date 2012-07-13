package com.clouway.jobex.client.security;

/**
 * CompanyNameProvider class is used to set/get the company's name
 * which announces new jobs
 *
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public interface CompanyNameProvider {

  /**
   * Set company's name
   *
   * @param companyName a company name
   */
  void setCompanyName(String companyName);

  /**
   * Return the company's name
   *
   * @return a company's name
   */
  String getCompanyName();
}
