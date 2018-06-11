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

import com.cmc.training.util.MethodUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "category_question")
public class CategoryQuestion implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "category_id", unique = true, nullable = false)
  private Integer categoryId;

  @Basic(optional = false)
  @Column(name = "category_name", nullable = false, length = 50)
  private String categoryName;

  @Basic(optional = false)
  @Column(name = "category_code", nullable = false, length = 10)
  private String categoryCode;

  @Basic(optional = false)
  @Column(name = "create_date", length = 10, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
  private Collection<Question> questionCollection;

  @JoinColumn(name = "creator_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account creatorId;

  @Basic(optional = false)
  @Column(name = "category_name_search", unique = true, nullable = false, length = 101)
  private String categoryNameSearch;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
  private Collection<Exam> examCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
  @JsonIgnore
  private Collection<QuestionClone> questionCloneCollection;

  public CategoryQuestion() {
  }

  public CategoryQuestion(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public CategoryQuestion(String categoryName, int creatorId) {
    this.categoryName = categoryName;
    this.categoryNameSearch = categoryName + " " + MethodUtil.removeAccent(categoryName);
    this.creatorId = new Account(creatorId);
    this.categoryCode = "ABC";

  }

  public CategoryQuestion(int categoryId, String categoryName, int creatorId) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.categoryNameSearch = MethodUtil.convertContentSearch(this.categoryName);
    this.creatorId = new Account(creatorId);
    this.categoryCode = "ABC";
  }

  /**
   * @param categoryId
   */
  public CategoryQuestion(int categoryId) {
    this.categoryId = categoryId;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  public Account getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Account creatorId) {
    this.creatorId = creatorId;
  }

  /**
   * @return the categoryNameSearch
   */
  public String getCategoryNameSearch() {
    return categoryNameSearch;
  }

  /**
   * @param categoryNameSearch
   *          the categoryNameSearch to set
   */
  public void setCategoryNameSearch(String categoryNameSearch) {
    this.categoryNameSearch = categoryNameSearch;
  }

  public Collection<Exam> getExamCollection() {
    return examCollection;
  }

  public void setExamCollection(Collection<Exam> examCollection) {
    this.examCollection = examCollection;
  }

  public Collection<QuestionClone> getQuestionCloneCollection() {
    return questionCloneCollection;
  }

  public void setQuestionCloneCollection(Collection<QuestionClone> questionCloneCollection) {
    this.questionCloneCollection = questionCloneCollection;
  }

  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (categoryId != null ? categoryId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof CategoryQuestion)) {
      return false;
    }
    CategoryQuestion other = (CategoryQuestion) object;
    if ((this.categoryId == null && other.categoryId != null)
        || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.CategoryQuestion[ categoryId=" + categoryId + " ]";
  }

  public void setInforUpdateCategoryQuestion(CategoryQuestion categoryQuestionUpdate) {
    this.categoryName = categoryQuestionUpdate.getCategoryName();
    this.categoryNameSearch = categoryQuestionUpdate.getCategoryName() + " "
        + MethodUtil.removeAccent(categoryQuestionUpdate.getCategoryName());
  }

}
