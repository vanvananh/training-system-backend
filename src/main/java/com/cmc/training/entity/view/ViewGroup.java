/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Table(name = "view_group")
@Access(value = AccessType.FIELD)
public class ViewGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Basic(optional = false)
  @Column(name = "group_id")
  private int groupId;

  @Basic(optional = false)
  @Column(name = "group_name")
  private String groupName;

  @Basic(optional = false)
  @Column(name = "group_name_search")
  private String groupNameSearch;

  @Basic(optional = false)
  @Column(name = "create_date")
  @Temporal(TemporalType.DATE)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "fullname")
  private String fullname;

  @Basic(optional = false)
  @Column(name = "fullname_search")
  private String fullnameSearch;

  @Basic(optional = false)
  @Column(name = "number_of_members")
  private long numberOfMembers;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  private boolean isDeleted;

  public ViewGroup() {
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

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public long getNumberOfMembers() {
    return numberOfMembers;
  }

  public void setNumberOfMembers(long numberOfMembers) {
    this.numberOfMembers = numberOfMembers;
  }

  /**
   * @return the groupNameSearch
   */
  public String getGroupNameSearch() {
    return groupNameSearch;
  }

  /**
   * @param groupNameSearch
   *          the groupNameSearch to set
   */
  public void setGroupNameSearch(String groupNameSearch) {
    this.groupNameSearch = groupNameSearch;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

}
