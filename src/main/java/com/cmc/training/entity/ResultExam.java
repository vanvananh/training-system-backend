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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "result_exam")
public class ResultExam implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "result_exam_id", unique = true, nullable = false)
  private Integer resultExamId;

  @Basic(optional = false)
  @Column(name = "total_mark_essay", nullable = false, precision = 12, scale = 0)
  private float totalMarkEssay;

  @Basic(optional = false)
  @Column(name = "total_mark_multiple_choice", nullable = false, precision = 12, scale = 0)
  private float totalMarkMultipleChoice;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultExamId")
  @JsonIgnore
  private Collection<UserAnswer> userAnswerCollection;

  @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", nullable = false)
  @ManyToOne(optional = false)
  private Exam examId;

  @JoinColumn(name = "code_examination_id", referencedColumnName = "code_examination_id", nullable = false)
  @ManyToOne(optional = false)
  private CodeExamination codeExaminationId;

  @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account accountId;

  public ResultExam() {
  }

  public ResultExam(Integer resultExamId) {
    this.resultExamId = resultExamId;
  }

  public Integer getResultExamId() {
    return resultExamId;
  }

  public void setResultExamId(Integer resultExamId) {
    this.resultExamId = resultExamId;
  }

  public float getTotalMarkEssay() {
    return totalMarkEssay;
  }

  public void setTotalMarkEssay(float totalMarkEssay) {
    this.totalMarkEssay = totalMarkEssay;
  }

  public float getTotalMarkMultipleChoice() {
    return totalMarkMultipleChoice;
  }

  public void setTotalMarkMultipleChoice(float totalMarkMultipleChoice) {
    this.totalMarkMultipleChoice = totalMarkMultipleChoice;
  }

  public Collection<UserAnswer> getUserAnswerCollection() {
    return userAnswerCollection;
  }

  public void setUserAnswerCollection(Collection<UserAnswer> userAnswerCollection) {
    this.userAnswerCollection = userAnswerCollection;
  }

  public Exam getExamId() {
    return examId;
  }

  public void setExamId(Exam examId) {
    this.examId = examId;
  }

  public CodeExamination getCodeExaminationId() {
    return codeExaminationId;
  }

  public void setCodeExaminationId(CodeExamination codeExaminationId) {
    this.codeExaminationId = codeExaminationId;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (resultExamId != null ? resultExamId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ResultExam)) {
      return false;
    }
    ResultExam other = (ResultExam) object;
    if ((this.resultExamId == null && other.resultExamId != null)
        || (this.resultExamId != null && !this.resultExamId.equals(other.resultExamId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.ResultExam[ resultExamId=" + resultExamId + " ]";
  }

}
