/**
 * training-system-backend - com.cmc.training.util.filter
 */
package com.cmc.training.util.filter;

import java.util.Date;

/**
 * @author: NNDuy
 * @Date: Mar 14, 2018
 */
public class FilterCategoryQuestion {
  private Integer creatorId;
  private Date createDateFrom;
  private Date createDateTo;

  /**
   * Constructure
   */
  public FilterCategoryQuestion() {

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
