/**
 * training-system-backend - com.cmc.training.dto
 */
package com.cmc.training.dto;

import java.util.Date;

import com.cmc.training.entity.Account;
import com.cmc.training.entity.Group;
import com.cmc.training.entity.GroupAccount;

/**
 * @author: nvangoc
 * @Date: 20 Mar 2018
 */
public class GroupAccountDTO {
  private Integer accountId;
  private Integer groupId;
  private Date joinDate;
  private boolean existInGroup;

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

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

  public GroupAccountDTO() {
    super();
  }

  public GroupAccount toEntity() {
    GroupAccount groupAccount = new GroupAccount();
    groupAccount.setAccountId(new Account(this.accountId));
    groupAccount.setGroupId(new Group(this.groupId));
    groupAccount.setJoinDate(this.joinDate);
    return groupAccount;
  }
}
