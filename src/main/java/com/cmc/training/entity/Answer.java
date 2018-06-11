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

import com.cmc.training.util.MethodUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "answer_id", unique = true, nullable = false)
  private Integer answerId;

  @Basic(optional = false)
  @Column(name = "content", nullable = false, length = 500)
  private String content;

  @Basic(optional = false)
  @Column(name = "is_correct")
  private Boolean isCorrect;

  @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
  @ManyToOne(optional = false)
  @JsonIgnore
  private Question questionId;

  @Basic(optional = false)
  @Column(name = "content_search", nullable = false, length = 1001)
  private String contentSearch;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerId")
  @JsonIgnore
  private Collection<AnswerClone> answerCloneCollection;

  public Answer() {
  }

  public Answer(Integer answerId) {
    this.answerId = answerId;
  }

  public Answer(String content, Boolean isCorrect) {
    this.content = content;
    this.contentSearch = content + " " + MethodUtil.removeAccent(content);
    this.isCorrect = isCorrect;
  }

  public Integer getAnswerId() {
    return answerId;
  }

  public void setAnswerId(Integer answerId) {
    this.answerId = answerId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  public Question getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Question questionId) {
    this.questionId = questionId;
  }

  /**
   * @param isCorrect
   *          the isCorrect to set
   */
  public void setCorrect(boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  /**
   * @return the contentSearch
   */
  public String getContentSearch() {
    return contentSearch;
  }

  /**
   * @param contentSearch
   *          the contentSearch to set
   */
  public void setContentSearch(String contentSearch) {
    this.contentSearch = contentSearch;
  }

  public Collection<AnswerClone> getAnswerCloneCollection() {
    return answerCloneCollection;
  }

  public void setAnswerCloneCollection(Collection<AnswerClone> answerCloneCollection) {
    this.answerCloneCollection = answerCloneCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (answerId != null ? answerId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Answer)) {
      return false;
    }
    Answer other = (Answer) object;
    if ((this.answerId == null && other.answerId != null)
        || (this.answerId != null && !this.answerId.equals(other.answerId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Answer[ answerId=" + answerId + " ]";
  }

}
