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

/**
 *
 * @author User
 */
@Entity
@Table(name = "account_examination", uniqueConstraints = @UniqueConstraint(columnNames = {
    "examination_id", "account_id", "type_id" }))
public class AccountExamination implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "account_examination_id", unique = true, nullable = false)
  private Integer accountExaminationId;

  @JoinColumn(name = "examination_id", referencedColumnName = "examination_id", nullable = false)
  @ManyToOne(optional = false)
  private Examination examinationId;

  @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  @ManyToOne(optional = false)
  private Account accountId;

  @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
  @ManyToOne(optional = false)
  private Type typeId;

  public AccountExamination() {
  }

  public AccountExamination(Integer accountExaminationId) {
    this.accountExaminationId = accountExaminationId;
  }

  public Integer getAccountExaminationId() {
    return accountExaminationId;
  }

  public void setAccountExaminationId(Integer accountExaminationId) {
    this.accountExaminationId = accountExaminationId;
  }

  public Examination getExaminationId() {
    return examinationId;
  }

  public void setExaminationId(Examination examinationId) {
    this.examinationId = examinationId;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  public Type getTypeId() {
    return typeId;
  }

  public void setTypeId(Type typeId) {
    this.typeId = typeId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (accountExaminationId != null ? accountExaminationId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof AccountExamination)) {
      return false;
    }
    AccountExamination other = (AccountExamination) object;
    if ((this.accountExaminationId == null && other.accountExaminationId != null)
        || (this.accountExaminationId != null
            && !this.accountExaminationId.equals(other.accountExaminationId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.AccountExamination[ accountExaminationId=" + accountExaminationId + " ]";
  }

}
