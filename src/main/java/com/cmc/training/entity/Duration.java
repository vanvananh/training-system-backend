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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "duration")
public class Duration implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "duration_id", unique = true, nullable = false)
  private Integer durationId;

  @Basic(optional = false)
  @Column(name = "duration_value", unique = true, nullable = false)
  private int durationValue;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "durationId")
  @JsonIgnore
  private Collection<Exam> examCollection;

  public Duration() {
  }

  public Duration(Integer durationId) {
    this.durationId = durationId;
  }

  public Integer getDurationId() {
    return durationId;
  }

  public void setDurationId(Integer durationId) {
    this.durationId = durationId;
  }

  public int getDurationValue() {
    return durationValue;
  }

  public void setDurationValue(int durationValue) {
    this.durationValue = durationValue;
  }

  public Collection<Exam> getExamCollection() {
    return examCollection;
  }

  public void setExamCollection(Collection<Exam> examCollection) {
    this.examCollection = examCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (durationId != null ? durationId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Duration)) {
      return false;
    }
    Duration other = (Duration) object;
    if ((this.durationId == null && other.durationId != null)
        || (this.durationId != null && !this.durationId.equals(other.durationId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Duration[ durationId=" + durationId + " ]";
  }

}
