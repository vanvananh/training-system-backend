/**
 * training-system-backend - com.cmc.training.util.filter
 */
package com.cmc.training.util.filter;

import java.util.Date;

/**
 * @author: nvDong
 * @Date: Mar 19, 2018
 */
public class FilterExam {
  private Integer categoryName;
  private Integer durationValueFrom;
  private Integer durationValueTo;
  private Integer numberOfQuestionFrom;
  private Integer numberOfQuestionTo;
  private Date createDateFrom;
  private Date createDateTo;
  private Integer fullname;
  private Integer statusName;

  /**
   * Constructure
   */
  public FilterExam() {

  }

  public Integer getFullname() {
    return fullname;
  }

  public void setFullname(Integer fullname) {
    this.fullname = fullname;
  }

  public Date getCreateDateFrom() {
    return createDateFrom;
  }

  public Integer getDurationValueFrom() {
    return durationValueFrom;
  }

  public void setDurationValueFrom(Integer durationValueFrom) {
    this.durationValueFrom = durationValueFrom;
  }

  public Integer getDurationValueTo() {
    return durationValueTo;
  }

  public void setDurationValueTo(Integer durationValueTo) {
    this.durationValueTo = durationValueTo;
  }

  public Integer getNumberOfQuestionFrom() {
    return numberOfQuestionFrom;
  }

  public void setNumberOfQuestionFrom(Integer numberOfQuestionFrom) {
    this.numberOfQuestionFrom = numberOfQuestionFrom;
  }

  public Integer getNumberOfQuestionTo() {
    return numberOfQuestionTo;
  }

  public void setNumberOfQuestionTo(Integer numberOfQuestionTo) {
    this.numberOfQuestionTo = numberOfQuestionTo;
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

  public Integer getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(Integer categoryName) {
    this.categoryName = categoryName;
  }

  public Integer getStatusName() {
    return statusName;
  }

  public void setStatusName(Integer statusName) {
    this.statusName = statusName;
  }

}
