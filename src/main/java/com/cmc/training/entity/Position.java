/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "position", uniqueConstraints = @UniqueConstraint(columnNames = "position_name"))
public class Position implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "position_id", unique = true, nullable = false)
  private Integer positionId;

  @Basic(optional = false)
  @Column(name = "position_name", unique = true, nullable = false, length = 50)
  private String positionName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "positionId")
  @JsonIgnore
  private Collection<Account> accountCollection;

  @Basic(optional = false)
  @Column(name = "position_name_search", unique = true, nullable = false, length = 101)
  private String positionNameSearch;

  public Position() {
  }

  public Position(Integer positionId) {
    this.positionId = positionId;
  }

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId(Integer positionId) {
    this.positionId = positionId;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Collection<Account> getAccountCollection() {
    return accountCollection;
  }

  public void setAccountCollection(Collection<Account> accountCollection) {
    this.accountCollection = accountCollection;
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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (positionId != null ? positionId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Position)) {
      return false;
    }
    Position other = (Position) object;
    if ((this.positionId == null && other.positionId != null)
        || (this.positionId != null && !this.positionId.equals(other.positionId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Position[ positionId=" + positionId + " ]";
  }

}
