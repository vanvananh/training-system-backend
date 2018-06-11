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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "code_examination")
public class CodeExamination implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "code_examination_id", unique = true, nullable = false)
  private Integer codeExaminationId;

  @Column(name = "status")
  @ColumnDefault("true")
  private Boolean status;

  @JoinColumn(name = "examination_id", referencedColumnName = "examination_id", nullable = false)
  @ManyToOne(optional = false)
  private Examination examinationId;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeExaminationId")
  private Collection<ResultExam> resultExamCollection;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  public CodeExamination() {
  }

  public CodeExamination(Integer codeExaminationId) {
    this.codeExaminationId = codeExaminationId;
  }

  public Integer getCodeExaminationId() {
    return codeExaminationId;
  }

  public void setCodeExaminationId(Integer codeExaminationId) {
    this.codeExaminationId = codeExaminationId;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Examination getExaminationId() {
    return examinationId;
  }

  public void setExaminationId(Examination examinationId) {
    this.examinationId = examinationId;
  }

  public Collection<ResultExam> getResultExamCollection() {
    return resultExamCollection;
  }

  public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
    this.resultExamCollection = resultExamCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (codeExaminationId != null ? codeExaminationId.hashCode() : 0);
    return hash;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof CodeExamination)) {
      return false;
    }
    CodeExamination other = (CodeExamination) object;
    if ((this.codeExaminationId == null && other.codeExaminationId != null)
        || (this.codeExaminationId != null
            && !this.codeExaminationId.equals(other.codeExaminationId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.CodeExamination[ codeExaminationId=" + codeExaminationId + " ]";
  }

}
