/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.training.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author User
 */
@Entity
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(columnNames = "tag_name"))
public class Tag implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "tag_id", unique = true, nullable = false)
  private Integer tagId;

  @Basic(optional = false)
  @Column(name = "tag_name", unique = true, nullable = false, length = 50)
  private String tagName;

  @JoinTable(name = "tag_question", joinColumns = {
      @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", nullable = false, updatable = false) }, inverseJoinColumns = {
          @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false, updatable = false) })
  @ManyToMany
  @JsonIgnore
  private Collection<Question> questionCollection;

  @Basic(optional = false)
  @Column(name = "tag_name_search", unique = true, nullable = false, length = 101)
  private String tagNameSearch;

  public Tag() {
  }

  public Tag(Integer tagId) {
    this.tagId = tagId;
  }

  public Integer getTagId() {
    return tagId;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  /**
   * @return the tagNameSearch
   */
  public String getTagNameSearch() {
    return tagNameSearch;
  }

  /**
   * @param tagNameSearch
   *          the tagNameSearch to set
   */
  public void setTagNameSearch(String tagNameSearch) {
    this.tagNameSearch = tagNameSearch;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (tagId != null ? tagId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tag)) {
      return false;
    }
    Tag other = (Tag) object;
    if ((this.tagId == null && other.tagId != null)
        || (this.tagId != null && !this.tagId.equals(other.tagId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Tag[ tagId=" + tagId + " ]";
  }

}
