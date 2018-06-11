/**
 * 
 */
package com.cmc.training.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cmc.training.entity.Account;
import com.cmc.training.entity.GroupAccount;

/**
 * @author: User
 * @Date: Mar 11, 2018
 */
public class UserProfileDTO {
  private Integer accountId;
  private Date createDate;
  private String email;
  private String fullname;
  private String username;
  private String departmentName;
  private String positionName;
  private List<String> groupName;

  /**
   * Constructure
   */
  public UserProfileDTO(Account account) {
    this.accountId = account.getAccountId();
    this.createDate = account.getCreateDate();
    this.email = account.getEmail();
    this.fullname = account.getFullname();
    this.username = account.getUsername();
    this.departmentName = account.getDepartmentId().getDepartmentName();
    this.positionName = account.getPositionId().getPositionName();
    this.groupName = new ArrayList<>();
    for (GroupAccount item : account.getGroupAccountCollection()) {
      this.groupName.add(item.getGroupId().getGroupName());
    }
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getGroupName() {
    return groupName;
  }

  public void setGroupName(List<String> groupName) {
    this.groupName = groupName;
  }
}
