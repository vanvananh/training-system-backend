/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "view_list_exam")
public class ViewListExam implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "exam_id")
  private int examId;

  @Basic(optional = false)
  @Column(name = "code")
  private String code;

  @Basic(optional = false)
  @Column(name = "title")
  private String title;

  @Basic(optional = false)
  @Column(name = "category_name")
  private String categoryName;

  @Basic(optional = false)
  @Column(name = "duration_value")
  private int durationValue;

  @Basic(optional = false)
  @Column(name = "number_of_question")
  private int numberOfQuestion;

  @Basic(optional = false)
  @Column(name = "is_assign")
  private boolean isAssign;

  @Column(name = "create_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "title_search")
  private String titleSearch;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  @Basic(optional = false)
  @Column(name = "fullname")
  private String fullname;

  @Basic(optional = false)
  @Column(name = "fullname_search")
  private String fullnameSearch;

  @Basic(optional = false)
  @Column(name = "category_name_search")
  private String categoryNameSearch;

  @Basic(optional = false)
  @Column(name = "note")
  private String note;

  @Basic(optional = false)
  @Column(name = "categoryId")
  private String categoryId;

  @Basic(optional = false)
  @Column(name = "creatorId")
  private String creatorId;

  @Basic(optional = false)
  @Column(name = "statusId")
  private String statusId;

  @Basic(optional = false)
  @Column(name = "statusName")
  private String statusName;

  public ViewListExam() {
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public int getExamId() {
    return examId;
  }

  public void setExamId(int examId) {
    this.examId = examId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public int getDurationValue() {
    return durationValue;
  }

  public void setDurationValue(int durationValue) {
    this.durationValue = durationValue;
  }

  public int getNumberOfQuestion() {
    return numberOfQuestion;
  }

  public void setNumberOfQuestion(int numberOfQuestion) {
    this.numberOfQuestion = numberOfQuestion;
  }

  public boolean getIsAssign() {
    return isAssign;
  }

  public void setIsAssign(boolean isAssign) {
    this.isAssign = isAssign;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getTitleSearch() {
    return titleSearch;
  }

  public void setTitleSearch(String titleSearch) {
    this.titleSearch = titleSearch;
  }

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getFullnameSearch() {
    return fullnameSearch;
  }

  public void setFullnameSearch(String fullnameSearch) {
    this.fullnameSearch = fullnameSearch;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }

  public String getStatusId() {
    return statusId;
  }

  public void setStatusId(String statusId) {
    this.statusId = statusId;
  }

}
