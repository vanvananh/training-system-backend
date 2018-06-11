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

/**
 *
 * @author User
 */
@Entity
@Table(name = "user_answer")
public class UserAnswer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "user_answer_id", unique = true, nullable = false)
  private Integer userAnswerId;

  @Basic(optional = false)
  @Column(name = "content", nullable = false, length = 500)
  private String content;

  @JoinColumn(name = "result_exam_id", referencedColumnName = "result_exam_id", nullable = false)
  @ManyToOne(optional = false)
  private ResultExam resultExamId;

  @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
  @ManyToOne(optional = false)
  private Question questionId;

  @Basic(optional = false)
  @Column(name = "content_search", nullable = false, length = 1001)
  private String contentSearch;

  public UserAnswer() {
  }

  public UserAnswer(Integer userAnswerId) {
    this.userAnswerId = userAnswerId;
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

  public Integer getUserAnswerId() {
    return userAnswerId;
  }

  public void setUserAnswerId(Integer userAnswerId) {
    this.userAnswerId = userAnswerId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ResultExam getResultExamId() {
    return resultExamId;
  }

  public void setResultExamId(ResultExam resultExamId) {
    this.resultExamId = resultExamId;
  }

  public Question getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Question questionId) {
    this.questionId = questionId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (userAnswerId != null ? userAnswerId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserAnswer)) {
      return false;
    }
    UserAnswer other = (UserAnswer) object;
    if ((this.userAnswerId == null && other.userAnswerId != null)
        || (this.userAnswerId != null && !this.userAnswerId.equals(other.userAnswerId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.UserAnswer[ userAnswerId=" + userAnswerId + " ]";
  }

}
