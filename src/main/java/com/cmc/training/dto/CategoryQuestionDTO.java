/**
 * training-system-backend - com.cmc.training.dto
 */
package com.cmc.training.dto;

import com.cmc.training.entity.CategoryQuestion;

/**
 * @author: User
 * @Date: Feb 27, 2018
 */
public class CategoryQuestionDTO {
  private Integer categoryId;
  private String categoryName;
  private Integer creatorId;

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  public CategoryQuestionDTO() {

  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public Integer getCreatorId() {
    return creatorId;
  }

  public CategoryQuestion toEntity() {
    // create dto
    if (categoryId == null) {
      return new CategoryQuestion(categoryName, creatorId);
    }
    // update dto
    return new CategoryQuestion(categoryId, categoryName, creatorId);
  }
}
