/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.cmc.training.util.MethodUtil;

/**
 *
 * @author User
 */
@Entity
@Table(name = "`group`", uniqueConstraints = @UniqueConstraint(columnNames = "group_name"))
public class Group implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "group_id", unique = true, nullable = false)
  private Integer groupId;

  @Basic(optional = false)
  @Column(name = "create_date", nullable = false, length = 10, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "group_name", nullable = false, length = 50)
  private String groupName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
  private Collection<GroupAccount> groupAccountCollection;

  @JoinColumn(name = "creator_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account creatorId;

  @Transient
  private long numberOfMembers;

  @Basic(optional = false)
  @Column(name = "group_name_search", unique = true, nullable = false, length = 101)
  private String groupNameSearch;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  public Group() {
  }

  public Group(Integer groupId) {
    this.groupId = groupId;
  }

  public Group(String groupName, int creatorId) {
    this.groupName = groupName;
    this.groupNameSearch = MethodUtil.convertContentSearch(this.groupName);
    this.creatorId = new Account(creatorId);

  }

  public Group(int groupId, String groupName) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.groupNameSearch = MethodUtil.convertContentSearch(this.groupName);
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Collection<GroupAccount> getGroupAccountCollection() {
    return groupAccountCollection;
  }

  public void setGroupAccountCollection(Collection<GroupAccount> groupAccountCollection) {
    this.groupAccountCollection = groupAccountCollection;
  }

  public Account getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Account creatorId) {
    this.creatorId = creatorId;
  }

  public long getNumberOfMembers() {
    if (null != groupAccountCollection) {
      return groupAccountCollection.size();
    }
    return 0;

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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (groupId != null ? groupId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Group)) {
      return false;
    }
    Group other = (Group) object;
    if ((this.groupId == null && other.groupId != null)
        || (this.groupId != null && !this.groupId.equals(other.groupId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Group[ groupId=" + groupId + " ]";
  }

  /**
   * set infor grop
   * 
   * @param groupUpdate
   *          void
   * @author: NTGiang1
   */
  public void setInforUpdateGroup(Group groupUpdate) {
    this.groupName = groupUpdate.getGroupName();
    this.groupNameSearch = groupUpdate.getGroupName() + " "
        + MethodUtil.removeAccent(groupUpdate.getGroupName());
  }

}
