package com.cmc.training.service;

import java.util.List;


import com.cmc.training.entity.LevelQuestion;
/**
 * 
 * @author: lhdoan
 * @Date: 20 thg 3, 2018
 */
public interface LevelQuestionService {
  /**
   * 
   * This method is get all Level Question.
   * @return List<LevelQuestion> 
   * @author: lhdoan
   */
  List<LevelQuestion> getAllLevelQuestion();
}
