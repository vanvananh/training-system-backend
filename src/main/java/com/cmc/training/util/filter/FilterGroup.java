package com.cmc.training.util.filter;

import java.util.Date;

/**
 * 
 * @author: VDHao
 * @Date: Mar 19, 2018
 */
public class FilterGroup {
  private Integer numberOfMembersFrom;
  private Integer numberOfMembersTo;
  private Date createDateFrom;
  private Date createDateTo;

  /**
   * Constructure
   */
  public FilterGroup() {

  }

  public Integer getNumberOfMembersFrom() {
    return numberOfMembersFrom;
  }

  public void setNumberOfMembersFrom(Integer numberOfMembersFrom) {
    this.numberOfMembersFrom = numberOfMembersFrom;
  }

  public Integer getNumberOfMembersTo() {
    return numberOfMembersTo;
  }

  public void setNumberOfMembersTo(Integer numberOfMembersTo) {
    this.numberOfMembersTo = numberOfMembersTo;
  }

  public Date getCreateDateFrom() {
    return createDateFrom;
  }

  public void setCreateDateFrom(Date createDateFrom) {
    this.createDateFrom = createDateFrom;
  }

  public Date getCreateDateTo() {
    return createDateTo;
  }

  public void setCreateDateTo(Date createDateTo) {
    this.createDateTo = createDateTo;
  }

}
