/**
 * training-system-backend - com.cmc.training.util.filter
 */
package com.cmc.training.util.filter;

import java.util.Date;

/**
 * @author: NNDuy
 * @Date: Mar 14, 2018
 */
public class FilterAccount {
  private Integer groupId;
  private Integer departmentId;
  private Integer departmentName;
  private Integer positionId;
  private Integer positionName;
  private Date joinDateFrom;
  private Date joinDateTo;
  /**
   * Constructure
   */
  public FilterAccount() {

  }
  
  /**
   * Constructure filter follow department and position
   */
  public FilterAccount(Integer departmentId, Integer positionId) {
    this.departmentId = departmentId;
    this.positionId = positionId;
  }
  
  /**
   * Constructure
   */
  public FilterAccount(Integer departmentId, Integer positionId, Date joinDateFrom,
      Date joinDateTo) {
    this.departmentId = departmentId;
    this.positionId = positionId;
    this.joinDateFrom = joinDateFrom;
    this.joinDateTo = joinDateTo;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Integer getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(Integer departmentName) {
    this.departmentName = departmentName;
  }

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId(Integer positionId) {
    this.positionId = positionId;
  }

  public Integer getPositionName() {
    return positionName;
  }

  public void setPositionName(Integer positionName) {
    this.positionName = positionName;
  }

  public Date getJoinDateFrom() {
    return joinDateFrom;
  }

  public void setJoinDateFrom(Date joinDateFrom) {
    this.joinDateFrom = joinDateFrom;
  }

  public Date getJoinDateTo() {
    return joinDateTo;
  }

  public void setJoinDateTo(Date joinDateTo) {
    this.joinDateTo = joinDateTo;
  }
  
  
  
}
