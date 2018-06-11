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
@Table(name = "exam_question_clone", uniqueConstraints = @UniqueConstraint(columnNames = {
    "exam_id", "question_clone_id" }))
public class ExamQuestionClone implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "exam_question_id", unique = true, nullable = false)
  private Integer examQuestionId;

  @Basic(optional = false)
  @Column(name = "score", nullable = false, precision = 12, scale = 0)
  private float score;

  @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", nullable = false)
  @ManyToOne(optional = false)
  @JsonIgnore
  private Exam examId;

  @JoinColumn(name = "question_clone_id", referencedColumnName = "question_clone_id", nullable = false)
  @ManyToOne(optional = false)
  private QuestionClone questionCloneId;

  public ExamQuestionClone() {
  }

  public ExamQuestionClone(Integer examQuestionId) {
    this.examQuestionId = examQuestionId;
  }

  public Integer getExamQuestionId() {
    return examQuestionId;
  }

  public void setExamQuestionId(Integer examQuestionId) {
    this.examQuestionId = examQuestionId;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  public Exam getExamId() {
    return examId;
  }

  public void setExamId(Exam examId) {
    this.examId = examId;
  }

  public QuestionClone getQuestionCloneId() {
    return questionCloneId;
  }

  public void setQuestionCloneId(QuestionClone questionCloneId) {
    this.questionCloneId = questionCloneId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (examQuestionId != null ? examQuestionId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ExamQuestionClone)) {
      return false;
    }
    ExamQuestionClone other = (ExamQuestionClone) object;
    if ((this.examQuestionId == null && other.examQuestionId != null)
        || (this.examQuestionId != null && !this.examQuestionId.equals(other.examQuestionId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.ExamQuestionClone[ examQuestionId=" + examQuestionId + " ]";
  }

}
