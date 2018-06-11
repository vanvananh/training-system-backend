/**
 * 
 */
package com.cmc.training.dto;

import com.cmc.training.entity.Exam;

/**
 * @author CMC-GLOBAL
 *
 */
public class ExamDTO {
  private Integer examId;
  private int creatorId;
  private String note;
  private int durationId;
  private String title;
  private int categoryId;

  public Integer getExamId() {
    return examId;
  }

  public void setExamId(Integer examId) {
    this.examId = examId;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getDurationId() {
    return durationId;
  }

  public void setDurationId(int durationId) {
    this.durationId = durationId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * 
   * function convert ExamDTO to Exam entity
   * 
   * @return Exam
   * @author: NTVAnh1
   */
  public Exam toEntity() {
    if (examId == null) {
      return new Exam(note, durationId, title, creatorId, categoryId);
    }
    return new Exam(note, durationId, title, creatorId, categoryId);
  }
}
