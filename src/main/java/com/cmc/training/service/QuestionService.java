/**
 * 
 */
package com.cmc.training.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmc.training.entity.Answer;
import com.cmc.training.entity.LevelQuestion;
import com.cmc.training.entity.Question;
import com.cmc.training.entity.TypeQuestion;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterQuestion;

/**
 * This class is public to controller using business of Question.
 * 
 * @author lhdoan
 * @Date Mar 14, 2018
 */
public interface QuestionService {
  /**
   * This method is get list Question.
   * 
   * @param paging
   *          - object need paging.
   * @param sorting
   *          - object need sort.
   * @param keywordSearch
   *          - keyword need search
   * @return Page Question .
   * @author: lhdoan
   */
  Page<Question> getAllQuestion(Paging paging, Sorting sorting, String keywordSearch,
      FilterQuestion filterQuestion);

  /**
   * This method is get list LevelQuestion.
   * 
   * @return List<LevelQuestion> .
   * @author: NNDuy
   */
  List<LevelQuestion> getAllLevelQuestion();

  /**
   * This method is check LevelQuestion exist by id.
   * 
   * @param id
   *          - id of LevelQuestion need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isLevelQuestionExists(int id);

  /**
   * This method is get list typeQuestion.
   * 
   * @return List<TypeQuestion> .
   * @author: NNDuy
   */
  List<TypeQuestion> getAllTypeQuestion();

  /**
   * This method is check TypeQuestion exist by id.
   * 
   * @param id
   *          - id of TypeQuestion need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isTypeQuestionExists(int id);

  /**
   * This method is check type multi-choise.
   * 
   * @param id
   *          - id of Question need check.
   * @return boolean
   * @author: NNDuy
   */
  boolean isTypeMultiChoise(int id);

  /**
   * This method is create Question.
   * 
   * @param question
   *          - object question
   * @param answers
   *          - list object answer
   * @author: NNDuy
   * @param list
   */
  Question createQuestion(Question question, List<Answer> answers);

  /**
   * this method check question exists by id
   * 
   * @param ids
   * @return boolean
   * @author: nhanh3
   */
  boolean isQuestionExists(List<Integer> ids);

  /**
   * 
   * this method delete list question by id
   * 
   * @param ids
   * @author: nhanh3
   */
  void deleteListQuestionById(List<Integer> ids);

  /**
   * this get question by id
   * 
   * @param ids
   * @return Question
   * @author: nhanh3
   */
  Question getQuestionById(int id);

}
