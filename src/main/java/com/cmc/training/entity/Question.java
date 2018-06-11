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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.cmc.training.util.MethodUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "question_id", unique = true, nullable = false)
  private Integer questionId;

  @Basic(optional = false)
  @Column(name = "content", nullable = false, length = 1500)
  private String content;

  @Basic(optional = false)
  @Column(name = "file_image", length = 255)
  private String fileImage;

  @Basic(optional = false)
  @Column(name = "create_date", nullable = false, length = 10, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @ManyToMany(mappedBy = "questionCollection")
  @JsonIgnore
  private Collection<Tag> tagCollection;

  @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false)
  @ManyToOne(optional = false)
  private LevelQuestion levelId;

  @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
  @ManyToOne(optional = false)
  private CategoryQuestion categoryId;

  @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
  @ManyToOne(optional = false)
  private TypeQuestion typeId;

  @JoinColumn(name = "creator_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account creatorId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
  @JsonIgnore
  private Collection<UserAnswer> userAnswerCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
  private Collection<Answer> answerCollection;

  @Basic(optional = false)
  @Column(name = "content_search", nullable = false, length = 1001)
  private String contentSearch;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
  @JsonIgnore
  private Collection<QuestionClone> questionCloneCollection;

  public Question() {
  }

  public Question(Integer questionId) {
    this.questionId = questionId;
  }

  public Question(String content, int levelId, int categoryId, int typeId, int creatorId,
      String fileImage) {
    this.content = content;
    this.contentSearch = content + " " + MethodUtil.removeAccent(content);
    this.levelId = new LevelQuestion(levelId);
    this.typeId = new TypeQuestion(typeId);
    this.categoryId = new CategoryQuestion(categoryId);
    this.creatorId = new Account(creatorId);
    this.fileImage = fileImage;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Collection<Tag> getTagCollection() {
    return tagCollection;
  }

  public void setTagCollection(Collection<Tag> tagCollection) {
    this.tagCollection = tagCollection;
  }

  public LevelQuestion getLevelId() {
    return levelId;
  }

  public void setLevelId(LevelQuestion levelId) {
    this.levelId = levelId;
  }

  public CategoryQuestion getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(CategoryQuestion categoryId) {
    this.categoryId = categoryId;
  }

  public TypeQuestion getTypeId() {
    return typeId;
  }

  public void setTypeId(TypeQuestion typeId) {
    this.typeId = typeId;
  }

  public Account getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Account creatorId) {
    this.creatorId = creatorId;
  }

  public Collection<UserAnswer> getUserAnswerCollection() {
    return userAnswerCollection;
  }

  public void setUserAnswerCollection(Collection<UserAnswer> userAnswerCollection) {
    this.userAnswerCollection = userAnswerCollection;
  }

  public Collection<Answer> getAnswerCollection() {
    return answerCollection;
  }

  public void setAnswerCollection(Collection<Answer> answerCollection) {
    this.answerCollection = answerCollection;
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

  public String getFileImage() {
    return fileImage;
  }

  public void setFileImage(String fileImage) {
    this.fileImage = fileImage;
  }

  public Collection<QuestionClone> getQuestionCloneCollection() {
    return questionCloneCollection;
  }

  public void setQuestionCloneCollection(Collection<QuestionClone> questionCloneCollection) {
    this.questionCloneCollection = questionCloneCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (questionId != null ? questionId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Question)) {
      return false;
    }
    Question other = (Question) object;
    if ((this.questionId == null && other.questionId != null)
        || (this.questionId != null && !this.questionId.equals(other.questionId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Question[ questionId=" + questionId + " ]";
  }

}
