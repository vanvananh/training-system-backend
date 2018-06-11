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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.cmc.training.util.Constants;
import com.cmc.training.util.MethodUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "exam")
public class Exam implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "exam_id", unique = true, nullable = false)
  private Integer examId;

  @Basic(optional = false)
  @Column(name = "code", length = 50)
  private String code;

  @Basic(optional = false)
  @Column(name = "create_date", length = 10, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "is_assign")
  private boolean isAssign;

  @Basic(optional = false)
  @Column(name = "note", length = 500)
  private String note;

  @Basic(optional = false)
  @Column(name = "number_of_question")
  @ColumnDefault("0")
  private int numberOfQuestion;

  @JoinColumn(name = "duration_id", referencedColumnName = "duration_id", nullable = false)
  @ManyToOne(optional = false)
  private Duration durationId;

  @Basic(optional = false)
  @Column(name = "title", nullable = false, length = 200)
  private String title;

  @Basic(optional = false)
  @Column(name = "total_score", precision = 12, scale = 0)
  @ColumnDefault("0")
  private float totalScore;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
  private Collection<ExamQuestionClone> examQuestionCloneCollection;

  @JoinColumn(name = "status_id", referencedColumnName = "status_id", insertable = false)
  @ManyToOne(optional = false)
  private StatusExam statusId;

  @JoinColumn(name = "creator_id", referencedColumnName = "account_id", nullable = false)
  @ManyToOne(optional = false)
  private Account creatorId;

  @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
  @ManyToOne(optional = false)
  private CategoryQuestion categoryId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
  @JsonIgnore
  private Collection<ResultExam> resultExamCollection;

  @Basic(optional = false)
  @Column(name = "title_search", nullable = false, length = 401)
  private String titleSearch;

  @Basic(optional = false)
  @Column(name = "is_deleted")
  @ColumnDefault("0")
  private boolean isDeleted;

  @PrePersist
  public void prePersist() {
    if (statusId == null)
      statusId = new StatusExam(Constants.Common.DRAFT_STATUS_IN_EXAM);
  }

  public Exam() {
  }

  public Exam(Integer examId) {
    this.examId = examId;
  }

  public Exam(String code, String note, int durationId, String title, int creatorId,
      int categoryId) {
    this.code = code;
    this.title = title;
    this.categoryId = new CategoryQuestion(categoryId);
    this.durationId = new Duration(durationId);
    this.creatorId = new Account(creatorId);
    this.note = note;
    this.titleSearch = MethodUtil.convertContentSearch(this.title);
  }

  public Exam(Integer examId, String code, String note, int durationId, String title, int creatorId,
      int categoryId) {
    this.examId = examId;
    this.code = code;
    this.title = title;
    this.categoryId = new CategoryQuestion(categoryId);
    this.durationId = new Duration(durationId);
    this.creatorId = new Account(creatorId);
    this.note = note;
    this.titleSearch = MethodUtil.convertContentSearch(this.title);
  }

  public Exam(String note, int durationId, String title, int creatorId, int categoryId) {
    this.title = title;
    this.categoryId = new CategoryQuestion(categoryId);
    this.durationId = new Duration(durationId);
    this.creatorId = new Account(creatorId);
    this.note = note;
    this.titleSearch = MethodUtil.convertContentSearch(this.title);
    createDate = new Date();
  }

  public String getTitleSearch() {
    return titleSearch;
  }

  public void setTitleSearch(String titleSearch) {
    this.titleSearch = titleSearch;
  }

  public void setAssign(boolean isAssign) {
    this.isAssign = isAssign;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getExamId() {
    return examId;
  }

  public void setExamId(Integer examId) {
    this.examId = examId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public boolean getIsAssign() {
    return isAssign;
  }

  public void setIsAssign(boolean isAssign) {
    this.isAssign = isAssign;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getNumberOfQuestion() {
    return numberOfQuestion;
  }

  public void setNumberOfQuestion(int numberOfQuestion) {
    this.numberOfQuestion = numberOfQuestion;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(float totalScore) {
    this.totalScore = totalScore;
  }

  public Collection<ExamQuestionClone> getExamQuestionCloneCollection() {
    return examQuestionCloneCollection;
  }

  public void setExamQuestionCloneCollection(
      Collection<ExamQuestionClone> examQuestionCloneCollection) {
    this.examQuestionCloneCollection = examQuestionCloneCollection;
  }

  public StatusExam getStatusId() {
    return statusId;
  }

  public void setStatusId(StatusExam statusId) {
    this.statusId = statusId;
  }

  public Account getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Account creatorId) {
    this.creatorId = creatorId;
  }

  public CategoryQuestion getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(CategoryQuestion categoryId) {
    this.categoryId = categoryId;
  }

  public Collection<ResultExam> getResultExamCollection() {
    return resultExamCollection;
  }

  public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
    this.resultExamCollection = resultExamCollection;
  }

  public Duration getDurationId() {
    return durationId;
  }

  public void setDurationId(Duration durationId) {
    this.durationId = durationId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (examId != null ? examId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Exam)) {
      return false;
    }
    Exam other = (Exam) object;
    if ((this.examId == null && other.examId != null)
        || (this.examId != null && !this.examId.equals(other.examId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Exam[ examId=" + examId + " ]";
  }

}
