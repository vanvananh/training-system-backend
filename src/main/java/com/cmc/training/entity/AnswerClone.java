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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "answer_clone")
public class AnswerClone implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "answer_clone_id", unique = true, nullable = false)
  private Integer answerCloneId;

  @Basic(optional = false)
  @Column(name = "content", nullable = false, length = 500)
  private String content;

  @Basic(optional = false)
  @Column(name = "is_correct", nullable = false)
  private boolean isCorrect;

  @Basic(optional = false)
  @Column(name = "content_search", nullable = false, length = 1001)
  private String contentSearch;

  @JoinColumn(name = "answer_id", referencedColumnName = "answer_id", nullable = false)
  @ManyToOne(optional = false)
  private Answer answerId;

  @JoinColumn(name = "question_clone_id", referencedColumnName = "question_clone_id", nullable = false)
  @ManyToOne(optional = false)
  @JsonIgnore
  private QuestionClone questionCloneId;

  public AnswerClone() {
  }

  public AnswerClone(Integer answerCloneId) {
    this.answerCloneId = answerCloneId;
  }

  public void setCorrect(boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  public Integer getAnswerCloneId() {
    return answerCloneId;
  }

  public void setAnswerCloneId(Integer answerCloneId) {
    this.answerCloneId = answerCloneId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  public String getContentSearch() {
    return contentSearch;
  }

  public void setContentSearch(String contentSearch) {
    this.contentSearch = contentSearch;
  }

  public Answer getAnswerId() {
    return answerId;
  }

  public void setAnswerId(Answer answerId) {
    this.answerId = answerId;
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
    hash += (answerCloneId != null ? answerCloneId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof AnswerClone)) {
      return false;
    }
    AnswerClone other = (AnswerClone) object;
    if ((this.answerCloneId == null && other.answerCloneId != null)
        || (this.answerCloneId != null && !this.answerCloneId.equals(other.answerCloneId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.AnswerClone[ answerCloneId=" + answerCloneId + " ]";
  }

}
