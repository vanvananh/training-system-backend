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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "question_clone")
public class QuestionClone implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "question_clone_id", unique = true, nullable = false)
  private Integer questionCloneId;

  @Basic(optional = false)
  @Column(name = "content", nullable = false, length = 500)
  private String content;

  @Column(name = "file_image", length = 255)
  private String fileImage;

  @Basic(optional = false)
  @Column(name = "create_date", nullable = false, length = 10)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "content_search", nullable = false, length = 1001)
  private String contentSearch;

  @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
  @ManyToOne(optional = false)
  private Question questionId;

  @JoinColumn(name = "creator_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account creatorId;

  @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
  @ManyToOne(optional = false)
  private TypeQuestion typeId;

  @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
  @ManyToOne(optional = false)
  private CategoryQuestion categoryId;

  @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false)
  @ManyToOne(optional = false)
  private LevelQuestion levelId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionCloneId")
  private Collection<AnswerClone> answerCloneCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionCloneId")
  @JsonIgnore
  private Collection<ExamQuestionClone> examQuestionCloneCollection;

  public QuestionClone() {
  }

  public QuestionClone(Integer questionCloneId) {
    this.questionCloneId = questionCloneId;
  }

  public Integer getQuestionCloneId() {
    return questionCloneId;
  }

  public void setQuestionCloneId(Integer questionCloneId) {
    this.questionCloneId = questionCloneId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFileImage() {
    return fileImage;
  }

  public void setFileImage(String fileImage) {
    this.fileImage = fileImage;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getContentSearch() {
    return contentSearch;
  }

  public void setContentSearch(String contentSearch) {
    this.contentSearch = contentSearch;
  }

  public Question getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Question questionId) {
    this.questionId = questionId;
  }

  public Account getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Account creatorId) {
    this.creatorId = creatorId;
  }

  public TypeQuestion getTypeId() {
    return typeId;
  }

  public void setTypeId(TypeQuestion typeId) {
    this.typeId = typeId;
  }

  public CategoryQuestion getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(CategoryQuestion categoryId) {
    this.categoryId = categoryId;
  }

  public LevelQuestion getLevelId() {
    return levelId;
  }

  public void setLevelId(LevelQuestion levelId) {
    this.levelId = levelId;
  }

  public Collection<AnswerClone> getAnswerCloneCollection() {
    return answerCloneCollection;
  }

  public void setAnswerCloneCollection(Collection<AnswerClone> answerCloneCollection) {
    this.answerCloneCollection = answerCloneCollection;
  }

  public Collection<ExamQuestionClone> getExamQuestionCloneCollection() {
    return examQuestionCloneCollection;
  }

  public void setExamQuestionCloneCollection(
      Collection<ExamQuestionClone> examQuestionCloneCollection) {
    this.examQuestionCloneCollection = examQuestionCloneCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (questionCloneId != null ? questionCloneId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof QuestionClone)) {
      return false;
    }
    QuestionClone other = (QuestionClone) object;
    if ((this.questionCloneId == null && other.questionCloneId != null)
        || (this.questionCloneId != null && !this.questionCloneId.equals(other.questionCloneId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "newpackage.QuestionClone[ questionCloneId=" + questionCloneId + " ]";
  }

}
