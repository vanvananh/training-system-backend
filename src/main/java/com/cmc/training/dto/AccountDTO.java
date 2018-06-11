/**
 * training-system-backend - com.cmc.training.dto
 */
package com.cmc.training.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cmc.training.entity.Account;
import com.cmc.training.entity.GroupAccount;

/**
 * @author: nvangoc
 * @Date: 14 Mar 2018
 */
public class AccountDTO {
  private Integer accountId;
  private String fullname;
  private String username;
  private String departmentName;
  private String positionName;
  private List<Integer> groupIds;
  private boolean existInGroup;
  private Date joinedDate;

  /**
   * @return the joinedDate
   */
  public Date getJoinedDate() {
    return joinedDate;
  }

  /**
   * @param joinedDate
   *          the joinedDate to set
   */
  public void setJoinedDate(Date joinedDate) {
    this.joinedDate = joinedDate;
  }

  /**
   * @return the existInGroup
   */
  public boolean isExistInGroup() {
    return existInGroup;
  }

  /**
   * @param existInGroup
   *          the existInGroup to set
   */
  public void setExistInGroup(boolean existInGroup) {
    this.existInGroup = existInGroup;
  }

  /**
   * @return the accountId
   */
  public Integer getAccountId() {
    return accountId;
  }

  /**
   * @param accountId
   *          the accountId to set
   */
  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  /**
   * @return the fullname
   */
  public String getFullname() {
    return fullname;
  }

  /**
   * @param fullname
   *          the fullname to set
   */
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * @param departmentName
   *          the departmentName to set
   */
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  /**
   * @return the positionName
   */
  public String getPositionName() {
    return positionName;
  }

  /**
   * @param positionName
   *          the positionName to set
   */
  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  /**
   * @return the groupIds
   */
  public List<Integer> getGroupIds() {
    return groupIds;
  }

  /**
   * @param groupIds
   *          the groupIds to set
   */
  public void setGroupIds(List<Integer> groupIds) {
    this.groupIds = groupIds;
  }

  /**
   * Constructure
   */
  public AccountDTO() {
    super();
    groupIds = new ArrayList<>();
  }

  /**
   * Constructure
   */
  public AccountDTO(Account account) {
    this.accountId = account.getAccountId();
    this.fullname = account.getFullname();
    this.username = account.getUsername();
    this.departmentName = account.getDepartmentId().getDepartmentName();
    this.positionName = account.getPositionId().getPositionName();
    this.groupIds = new ArrayList<>();
    for (GroupAccount groupAccount : account.getGroupAccountCollection()) {
      this.groupIds.add(groupAccount.getGroupId().getGroupId());
    }
  }

  /**
   * Constructure
   */
  public AccountDTO(GroupAccount groupAccount) {
    this.accountId = groupAccount.getAccountId().getAccountId();
    this.fullname = groupAccount.getAccountId().getFullname();
    this.username = groupAccount.getAccountId().getUsername();
    this.joinedDate = groupAccount.getJoinDate();
  }

}
