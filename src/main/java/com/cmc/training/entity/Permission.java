package com.cmc.training.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name = "permissions")
public class Permission implements Serializable {

  private static final long serialVersionUID = -2196096290553983312L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permission_id", unique = true, nullable = false)
  private int permissionId;

  @Column(name = "permission_name")
  private String permissionName;

  @Column(name = "permission_desc")
  private String permissionDesc;

  // bi-directional many-to-one association to RolePermission
  @OneToMany(mappedBy = "permission")
  private List<RolePermission> rolePermissions;

  public Permission() {
  }

  public Permission(int permissionId, String permissionName, String permissionDesc,
      List<RolePermission> rolePermissions) {
    super();
    this.permissionId = permissionId;
    this.permissionName = permissionName;
    this.permissionDesc = permissionDesc;
    this.rolePermissions = rolePermissions;
  }

  public int getPermissionId() {
    return this.permissionId;
  }

  public void setPermissionId(int permissionId) {
    this.permissionId = permissionId;
  }

  public String getPermissionName() {
    return this.permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

  public String getPermissionDesc() {
    return permissionDesc;
  }

  public void setPermissionDesc(String permissionDesc) {
    this.permissionDesc = permissionDesc;
  }

  @JsonIgnore
  public List<RolePermission> getRolePermissions() {
    return this.rolePermissions;
  }

  public void setRolePermissions(List<RolePermission> rolePermissions) {
    this.rolePermissions = rolePermissions;
  }

}