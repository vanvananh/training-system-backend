/**
 * training-system-backend - com.cmc.training.dto
 */
package com.cmc.training.dto;

import com.cmc.training.entity.Answer;

/**
 * @author: NNDuy
 * @Date: Mar 19, 2018
 */
public class AnswerDTO {

  private String content;
  private Boolean isCorrect;

  /**
   * Constructure
   */
  public AnswerDTO() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  public Answer toEntity() {
    return new Answer(content, isCorrect);
  }

}
