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
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "department_name"))
public class Department implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "department_id", unique = true, nullable = false)
  private Integer departmentId;

  @Basic(optional = false)
  @Column(name = "department_name", unique = true, nullable = false, length = 50)
  private String departmentName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
  @JsonIgnore
  private Collection<Account> accountCollection;

  @Basic(optional = false)
  @Column(name = "department_name_search", unique = true, nullable = false, length = 101)
  private String departmentNameSearch;

  public Department() {
  }

  public Department(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Collection<Account> getAccountCollection() {
    return accountCollection;
  }

  public void setAccountCollection(Collection<Account> accountCollection) {
    this.accountCollection = accountCollection;
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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (departmentId != null ? departmentId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Department)) {
      return false;
    }
    Department other = (Department) object;
    if ((this.departmentId == null && other.departmentId != null)
        || (this.departmentId != null && !this.departmentId.equals(other.departmentId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Department[ departmentId=" + departmentId + " ]";
  }

}
