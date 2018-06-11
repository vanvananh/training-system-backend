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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "account_id", unique = true, nullable = false)
  private Integer accountId;

  @Basic(optional = false)
  @Column(name = "create_date", nullable = false, length = 10, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Basic(optional = false)
  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Basic(optional = false)
  @Column(name = "fullname", nullable = false, length = 50)
  private String fullname;

  @Basic(optional = false)
  @Column(name = "username", unique = true, nullable = false, length = 50)
  private String username;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
  private Collection<Question> questionCollection;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
  private Collection<GroupAccount> groupAccountCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
  private Collection<AccountExamination> accountExaminationCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
  private Collection<Exam> examCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
  private Collection<CategoryQuestion> categoryQuestionCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
  private Collection<ResultExam> resultExamCollection;

  @JoinColumn(name = "department_id", referencedColumnName = "department_id", nullable = false)
  @ManyToOne(optional = false)
  private Department departmentId;

  @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
  @ManyToOne(optional = false)
  private Position positionId;

  @JoinColumn(name = "role_id", referencedColumnName = "role_id")
  @ManyToOne
  private Role roleId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
  @JsonIgnore
  private Collection<Group> groupCollection;

  @Basic(optional = false)
  @Column(name = "fullname_search", nullable = false, length = 101)
  private String fullnameSearch;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
  @JsonIgnore
  private Collection<QuestionClone> questionCloneCollection;

  public Account() {
  }

  public Account(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  public Collection<GroupAccount> getGroupAccountCollection() {
    return groupAccountCollection;
  }

  public void setGroupAccountCollection(Collection<GroupAccount> groupAccountCollection) {
    this.groupAccountCollection = groupAccountCollection;
  }

  public Collection<AccountExamination> getAccountExaminationCollection() {
    return accountExaminationCollection;
  }

  public void setAccountExaminationCollection(
      Collection<AccountExamination> accountExaminationCollection) {
    this.accountExaminationCollection = accountExaminationCollection;
  }

  public Collection<Exam> getExamCollection() {
    return examCollection;
  }

  public void setExamCollection(Collection<Exam> examCollection) {
    this.examCollection = examCollection;
  }

  public Collection<CategoryQuestion> getCategoryQuestionCollection() {
    return categoryQuestionCollection;
  }

  public void setCategoryQuestionCollection(
      Collection<CategoryQuestion> categoryQuestionCollection) {
    this.categoryQuestionCollection = categoryQuestionCollection;
  }

  public Collection<ResultExam> getResultExamCollection() {
    return resultExamCollection;
  }

  public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
    this.resultExamCollection = resultExamCollection;
  }

  public Department getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Department departmentId) {
    this.departmentId = departmentId;
  }

  public Position getPositionId() {
    return positionId;
  }

  public void setPositionId(Position positionId) {
    this.positionId = positionId;
  }

  public Role getRoleId() {
    return roleId;
  }

  public void setRoleId(Role roleId) {
    this.roleId = roleId;
  }

  public Collection<Group> getGroupCollection() {
    return groupCollection;
  }

  public void setGroupCollection(Collection<Group> groupCollection) {
    this.groupCollection = groupCollection;
  }

  /**
   * @return the fullnameSearch
   */
  public String getFullnameSearch() {
    return fullnameSearch;
  }

  /**
   * @param fullnameSearch
   *          the fullnameSearch to set
   */
  public void setFullnameSearch(String fullnameSearch) {
    this.fullnameSearch = fullnameSearch;
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
    hash += (accountId != null ? accountId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Account)) {
      return false;
    }
    Account other = (Account) object;
    if ((this.accountId == null && other.accountId != null)
        || (this.accountId != null && !this.accountId.equals(other.accountId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Account[ accountId=" + accountId + " ]";
  }

}
