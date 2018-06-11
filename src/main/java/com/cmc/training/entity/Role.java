/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "role_name"))
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "role_id", unique = true, nullable = false)
  private Integer roleId;

  @Basic(optional = false)
  @Column(name = "role_name", unique = true, nullable = false, length = 50)
  private String roleName;

  @OneToMany(mappedBy = "roleId")
  @JsonIgnore
  private Collection<Account> accountCollection;

  @ManyToMany(mappedBy = "roleCollection")
  @JsonIgnore
  private Collection<Menu> menuCollection;

  public Role() {
  }

  public Role(Integer roleId) {
    this.roleId = roleId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Collection<Account> getAccountCollection() {
    return accountCollection;
  }

  public void setAccountCollection(Collection<Account> accountCollection) {
    this.accountCollection = accountCollection;
  }

  public Collection<Menu> getMenuCollection() {
    return menuCollection;
  }

  public void setMenuCollection(Collection<Menu> menuCollection) {
    this.menuCollection = menuCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (roleId != null ? roleId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Role)) {
      return false;
    }
    Role other = (Role) object;
    if ((this.roleId == null && other.roleId != null)
        || (this.roleId != null && !this.roleId.equals(other.roleId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Role[ roleId=" + roleId + " ]";
  }

}
