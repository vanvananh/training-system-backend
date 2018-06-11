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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "level_question", uniqueConstraints = @UniqueConstraint(columnNames = "level_name"))
public class LevelQuestion implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "level_id", unique = true, nullable = false)
  private Integer levelId;

  @Basic(optional = false)
  @Column(name = "level_name", unique = true, nullable = false, length = 50)
  private String levelName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "levelId")
  @JsonIgnore
  private Collection<Question> questionCollection;

  @Basic(optional = false)
  @Column(name = "level_name_search", unique = true, nullable = false, length = 101)
  private String levelNameSearch;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "levelId")
  @JsonIgnore
  private Collection<QuestionClone> questionCloneCollection;

  public LevelQuestion() {
  }

  public LevelQuestion(Integer levelId) {
    this.levelId = levelId;
  }

  public Integer getLevelId() {
    return levelId;
  }

  public void setLevelId(Integer levelId) {
    this.levelId = levelId;
  }

  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  /**
   * @return the levelNameSearch
   */
  public String getLevelNameSearch() {
    return levelNameSearch;
  }

  /**
   * @param levelNameSearch
   *          the levelNameSearch to set
   */
  public void setLevelNameSearch(String levelNameSearch) {
    this.levelNameSearch = levelNameSearch;
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
    hash += (levelId != null ? levelId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof LevelQuestion)) {
      return false;
    }
    LevelQuestion other = (LevelQuestion) object;
    if ((this.levelId == null && other.levelId != null)
        || (this.levelId != null && !this.levelId.equals(other.levelId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.LevelQuestion[ levelId=" + levelId + " ]";
  }

}
