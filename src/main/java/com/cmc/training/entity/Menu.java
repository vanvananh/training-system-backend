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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "menu", uniqueConstraints = { @UniqueConstraint(columnNames = "menu_link"),
    @UniqueConstraint(columnNames = "menu_name") })
public class Menu implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "menu_id", unique = true, nullable = false)
  private Integer menuId;

  @Basic(optional = false)
  @Column(name = "icon_css", nullable = false, length = 200)
  private String iconCss;

  @Basic(optional = false)
  @Column(name = "menu_name", unique = true, nullable = false, length = 200)
  private String menuName;

  @Basic(optional = false)
  @Column(name = "menu_link", unique = true, nullable = false, length = 200)
  private String menuLink;

  @JoinTable(name = "menu_role", joinColumns = {
      @JoinColumn(name = "menu_id", referencedColumnName = "menu_id") }, inverseJoinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
  @ManyToMany
  @JsonIgnore
  private Collection<Role> roleCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuId")
  private Collection<SubMenu> subMenuCollection;

  public Menu() {
  }

  public Menu(Integer menuId) {
    this.menuId = menuId;
  }

  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }

  public String getIconCss() {
    return iconCss;
  }

  public void setIconCss(String iconCss) {
    this.iconCss = iconCss;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuLink() {
    return menuLink;
  }

  public void setMenuLink(String menuLink) {
    this.menuLink = menuLink;
  }

  public Collection<Role> getRoleCollection() {
    return roleCollection;
  }

  public void setRoleCollection(Collection<Role> roleCollection) {
    this.roleCollection = roleCollection;
  }

  public Collection<SubMenu> getSubMenuCollection() {
    return subMenuCollection;
  }

  public void setSubMenuCollection(Collection<SubMenu> subMenuCollection) {
    this.subMenuCollection = subMenuCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (menuId != null ? menuId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Menu)) {
      return false;
    }
    Menu other = (Menu) object;
    if ((this.menuId == null && other.menuId != null)
        || (this.menuId != null && !this.menuId.equals(other.menuId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "newpackage.Menu[ menuId=" + menuId + " ]";
  }

}
