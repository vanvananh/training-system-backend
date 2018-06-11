package com.cmc.training.util.filter;

import java.util.Date;

public class FilterQuestion {
  private Integer categoryId;
  private Integer typeId;
  private Integer levelId;
  private Integer creatorId;
  private Date createDateFrom;
  private Date createDateTo;

  public FilterQuestion() {
    super();
  }

  public FilterQuestion(Integer categoryId, Integer typeId, Integer levelId, Integer creatorId,
      Date createDateFrom, Date createDateTo) {
    super();
    this.categoryId = categoryId;
    this.typeId = typeId;
    this.levelId = levelId;
    this.creatorId = creatorId;
    this.createDateFrom = createDateFrom;
    this.createDateTo = createDateTo;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getLevelId() {
    return levelId;
  }

  public void setLevelId(Integer levelId) {
    this.levelId = levelId;
  }

  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  public Date getCreateDateFrom() {
    return createDateFrom;
  }

  public void setCreateDateFrom(Date createDateFrom) {
    this.createDateFrom = createDateFrom;
  }

  public Date getCreateDateTo() {
    return createDateTo;
  }

  public void setCreateDateTo(Date createDateTo) {
    this.createDateTo = createDateTo;
  }
}
