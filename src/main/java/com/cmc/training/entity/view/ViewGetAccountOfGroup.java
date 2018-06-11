/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "view_get_account_of_group")
public class ViewGetAccountOfGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Basic(optional = false)
  @Column(name = "account_id")
  private int accountId;

  @Column(name = "create_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "username")
  private String username;

  @Basic(optional = false)
  @Column(name = "fullname")
  private String fullname;

  @Basic(optional = false)
  @Column(name = "fullname_search")
  private String fullnameSearch;

  @Basic(optional = false)
  @Column(name = "email")
  private String email;

  @Basic(optional = false)
  @Column(name = "department_name")
  private String departmentName;

  @Basic(optional = false)
  @Column(name = "department_id")
  private String departmentId;

  @Basic(optional = false)
  @Column(name = "department_name_search")
  private String departmentNameSearch;

  @Basic(optional = false)
  @Column(name = "position_name")
  private String positionName;

  @Basic(optional = false)
  @Column(name = "position_id")
  private String positionId;

  @Basic(optional = false)
  @Column(name = "position_name_search")
  private String positionNameSearch;

  @Column(name = "join_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date joinDate;

  @Basic(optional = false)
  @Column(name = "group_id")
  private int groupId;

  public ViewGetAccountOfGroup() {
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  /**
   * @return the fullnameSearch
   */
  public String getFullnameSearch() {
    return fullnameSearch;
  }

  /**
   * @param fullnameSearch
   *          the fullnameSearch to set
   */
  public void setFullnameSearch(String fullnameSearch) {
    this.fullnameSearch = fullnameSearch;
  }

  /**
   * @return the departmentNameSearch
   */
  public String getDepartmentNameSearch() {
    return departmentNameSearch;
  }

  /**
   * @param departmentNameSearch
   *          the departmentNameSearch to set
   */
  public void setDepartmentNameSearch(String departmentNameSearch) {
    this.departmentNameSearch = departmentNameSearch;
  }

  /**
   * @return the positionNameSearch
   */
  public String getPositionNameSearch() {
    return positionNameSearch;
  }

  /**
   * @param positionNameSearch
   *          the positionNameSearch to set
   */
  public void setPositionNameSearch(String positionNameSearch) {
    this.positionNameSearch = positionNameSearch;
  }

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  public String getPositionId() {
    return positionId;
  }

  public void setPositionId(String positionId) {
    this.positionId = positionId;
  }

}
