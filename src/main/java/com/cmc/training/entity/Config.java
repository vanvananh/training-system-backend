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
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "config")
public class Config implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "config_id", unique = true, nullable = false)
  private Integer configId;

  @Basic(optional = false)
  @Column(name = "config_name", unique = true, nullable = false, length = 200)
  private String configName;

  @Basic(optional = false)
  @Column(name = "config_value", nullable = false, length = 200)
  private String configValue;

  public Config() {
  }

  public Config(Integer configId) {
    this.configId = configId;
  }

  public Config(Integer configId, String configName, String configValue) {
    this.configId = configId;
    this.configName = configName;
    this.configValue = configValue;
  }

  public Integer getConfigId() {
    return configId;
  }

  public void setConfigId(Integer configId) {
    this.configId = configId;
  }

  public String getConfigName() {
    return configName;
  }

  public void setConfigName(String configName) {
    this.configName = configName;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (configId != null ? configId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Config)) {
      return false;
    }
    Config other = (Config) object;
    if ((this.configId == null && other.configId != null)
        || (this.configId != null && !this.configId.equals(other.configId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Config[ configId=" + configId + " ]";
  }

}
