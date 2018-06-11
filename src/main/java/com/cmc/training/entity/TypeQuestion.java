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
@Table(name = "type_question", uniqueConstraints = @UniqueConstraint(columnNames = "type_name"))
public class TypeQuestion implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "type_id", unique = true, nullable = false)
  private Integer typeId;

  @Basic(optional = false)
  @Column(name = "type_name", unique = true, nullable = false, length = 50)
  private String typeName;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
  private Collection<Question> questionCollection;

  @Basic(optional = false)
  @Column(name = "type_name_search", unique = true, nullable = false, length = 101)
  private String typeNameSearch;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
  @JsonIgnore
  private Collection<QuestionClone> questionCloneCollection;

  public TypeQuestion() {
  }

  public TypeQuestion(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  /**
   * @return the typeNameSearch
   */
  public String getTypeNameSearch() {
    return typeNameSearch;
  }

  /**
   * @param typeNameSearch
   *          the typeNameSearch to set
   */
  public void setTypeNameSearch(String typeNameSearch) {
    this.typeNameSearch = typeNameSearch;
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
    hash += (typeId != null ? typeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TypeQuestion)) {
      return false;
    }
    TypeQuestion other = (TypeQuestion) object;
    if ((this.typeId == null && other.typeId != null)
        || (this.typeId != null && !this.typeId.equals(other.typeId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.TypeQuestion[ typeId=" + typeId + " ]";
  }

}
