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

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "examination")
public class Examination implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "examination_id", unique = true, nullable = false)
  private Integer examinationId;

  @Basic(optional = false)
  @Column(name = "end_date", nullable = false, length = 10)
  @Temporal(TemporalType.TIMESTAMP)
  private Date endDate;

  @Basic(optional = false)
  @Column(name = "start_date", nullable = false, length = 10)
  @Temporal(TemporalType.TIMESTAMP)
  private Date startDate;

  @Basic(optional = false)
  @Column(name = "title", nullable = false, length = 200)
  private String title;

  @Basic(optional = false)
  @Column(name = "title_search", nullable = false, length = 401)
  private String titleSearch;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId")
  private Collection<CodeExamination> codeExaminationCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId")
  @JsonIgnore
  private Collection<AccountExamination> accountExaminationCollection;

  @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
  @ManyToOne(optional = false)
  private StatusExamination statusId;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  public Examination() {
  }

  public Examination(Integer examinationId) {
    this.examinationId = examinationId;
  }

  public Integer getExaminationId() {
    return examinationId;
  }

  public void setExaminationId(Integer examinationId) {
    this.examinationId = examinationId;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Collection<CodeExamination> getCodeExaminationCollection() {
    return codeExaminationCollection;
  }

  public void setCodeExaminationCollection(Collection<CodeExamination> codeExaminationCollection) {
    this.codeExaminationCollection = codeExaminationCollection;
  }

  public Collection<AccountExamination> getAccountExaminationCollection() {
    return accountExaminationCollection;
  }

  public void setAccountExaminationCollection(
      Collection<AccountExamination> accountExaminationCollection) {
    this.accountExaminationCollection = accountExaminationCollection;
  }

  public StatusExamination getStatusId() {
    return statusId;
  }

  public void setStatusId(StatusExamination statusId) {
    this.statusId = statusId;
  }

  /**
   * @return the titleSearch
   */
  public String getTitleSearch() {
    return titleSearch;
  }

  /**
   * @param titleSearch
   *          the titleSearch to set
   */
  public void setTitleSearch(String titleSearch) {
    this.titleSearch = titleSearch;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (examinationId != null ? examinationId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Examination)) {
      return false;
    }
    Examination other = (Examination) object;
    if ((this.examinationId == null && other.examinationId != null)
        || (this.examinationId != null && !this.examinationId.equals(other.examinationId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Examination[ examinationId=" + examinationId + " ]";
  }

}
