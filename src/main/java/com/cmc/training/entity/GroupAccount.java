/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "group_account", uniqueConstraints = @UniqueConstraint(columnNames = { "group_id",
    "account_id" }))
public class GroupAccount implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "group_account_id", unique = true, nullable = false)
  private Integer groupAccountId;

  @Basic(optional = false)
  @Column(name = "join_date", nullable = false, length = 10, insertable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date joinDate;

  @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  @JsonIgnore
  private Account accountId;

  @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
  @ManyToOne(optional = false)
  @JsonIgnore
  private Group groupId;

  public GroupAccount() {
  }

  public GroupAccount(Integer groupAccountId) {
    this.groupAccountId = groupAccountId;
  }

  public Integer getGroupAccountId() {
    return groupAccountId;
  }

  public void setGroupAccountId(Integer groupAccountId) {
    this.groupAccountId = groupAccountId;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  public Group getGroupId() {
    return groupId;
  }

  public void setGroupId(Group groupId) {
    this.groupId = groupId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (groupAccountId != null ? groupAccountId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof GroupAccount)) {
      return false;
    }
    GroupAccount other = (GroupAccount) object;
    if ((this.groupAccountId == null && other.groupAccountId != null)
        || (this.groupAccountId != null && !this.groupAccountId.equals(other.groupAccountId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.GroupAccount[ groupAccountId=" + groupAccountId + " ]";
  }

}
