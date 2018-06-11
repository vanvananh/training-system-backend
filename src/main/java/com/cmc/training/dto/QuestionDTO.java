/**
 * training-system-backend - com.cmc.training.dto
 */
package com.cmc.training.dto;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.util.UriUtils;

import com.cmc.training.entity.Answer;
import com.cmc.training.entity.Question;
import com.cmc.training.util.MethodUtil;

/**
 * @author: NNDuy
 * @Date: Feb 27, 2018
 */
public class QuestionDTO {

  private String content;
  private String linkImage;
  private int levelId;
  private int categoryId;
  private int typeId;
  private int creatorId;
  private List<AnswerDTO> answers;

  /**
   * Constructure
   */
  public QuestionDTO() {
    answers = new ArrayList<>();
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getLevelId() {
    return levelId;
  }

  public void setLevelId(int levelId) {
    this.levelId = levelId;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public List<AnswerDTO> getAnswers() {
    return answers;
  }

  public void setAnswers(List<AnswerDTO> answers) {
    this.answers = answers;
  }

  public String getLinkImage() {
    try {
      return UriUtils.decode(linkImage, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setLinkImage(String linkImage) {
    this.linkImage = linkImage;
  }

  /**
   * convert QuestionDto to entity Question
   * 
   * @return Question
   * @author: NNDuy
   */
  public Question toEntityQuestion() {
    return new Question(content, levelId, categoryId, typeId, creatorId, linkImage);
  }

  /**
   * convert AnswerDto to entity Answer
   * 
   * @return List Answer
   * @author: NNDuy
   */
  public List<Answer> toEntityAnswer() {
    List<Answer> answerEntitys = null;
    if (!MethodUtil.checkListIsNull(answers)) {
      answerEntitys = new ArrayList<>();
      for (AnswerDTO answer : answers) {
        answerEntitys.add(answer.toEntity());
      }
    }
    return answerEntitys;
  }

}