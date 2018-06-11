/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "sub_menu", uniqueConstraints = { @UniqueConstraint(columnNames = "sub_menu_link"),
    @UniqueConstraint(columnNames = { "sub_menu_link", "sub_menu_name" }) })
public class SubMenu implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "sub_menu_id", unique = true, nullable = false)
  private Integer subMenuId;

  @Basic(optional = false)
  @Column(name = "sub_menu_link", unique = true, nullable = false, length = 200)
  private String subMenuLink;

  @Basic(optional = false)
  @Column(name = "sub_menu_name", nullable = false, length = 200)
  private String subMenuName;

  @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
  @ManyToOne(optional = false)
  @JsonIgnore
  private Menu menuId;

  public SubMenu() {
  }

  public SubMenu(Integer subMenuId) {
    this.subMenuId = subMenuId;
  }

  public Integer getSubMenuId() {
    return subMenuId;
  }

  public void setSubMenuId(Integer subMenuId) {
    this.subMenuId = subMenuId;
  }

  public String getSubMenuLink() {
    return subMenuLink;
  }

  public void setSubMenuLink(String subMenuLink) {
    this.subMenuLink = subMenuLink;
  }

  public String getSubMenuName() {
    return subMenuName;
  }

  public void setSubMenuName(String subMenuName) {
    this.subMenuName = subMenuName;
  }

  public Menu getMenuId() {
    return menuId;
  }

  public void setMenuId(Menu menuId) {
    this.menuId = menuId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (subMenuId != null ? subMenuId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof SubMenu)) {
      return false;
    }
    SubMenu other = (SubMenu) object;
    if ((this.subMenuId == null && other.subMenuId != null)
        || (this.subMenuId != null && !this.subMenuId.equals(other.subMenuId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "newpackage.SubMenu[ subMenuId=" + subMenuId + " ]";
  }

}
