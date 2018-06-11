/**
 * 
 */
package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.Answer;
import com.cmc.training.entity.LevelQuestion;
import com.cmc.training.entity.Question;
import com.cmc.training.entity.TypeQuestion;
import com.cmc.training.repository.AnswerRepository;
import com.cmc.training.repository.LevelQuestionRepository;
import com.cmc.training.repository.QuestionRepository;
import com.cmc.training.repository.TypeQuestionRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterQuestion;
import com.cmc.training.util.specification.CriteriaCustom;
import com.cmc.training.util.specification.SpecificationBuilder;

/**
 * This class is implement of QuestionService interface .
 * 
 * @author lhdoan
 * @Date Mar 14, 2018
 */
@Service
public class QuestionServiceImpl implements QuestionService {

  // declare question repository
  @Autowired
  private QuestionRepository questionRepository;

  // declare Answer repository
  @Autowired
  private AnswerRepository answerRepository;

  // declare level Question service
  @Autowired
  private LevelQuestionRepository levelQuestionRepository;

  // declare type Question service
  @Autowired
  private TypeQuestionRepository typeQuestionRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.QuestionService#getAllQuestion(com.cmc.training.
   * util .Paging, com.cmc.training.util.Sorting, java.lang.String)
   */
  @Override
  public Page<Question> getAllQuestion(Paging paging, Sorting sorting, String keywordSearch,
      FilterQuestion filterQuestion) {
    SpecificationBuilder<Question> specification = new SpecificationBuilder<Question>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // search
    if (!MethodUtil.isNull(keywordSearch)) {
      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_SEARCH,
          Constants.Operation.LIKE, keywordSearch));
    }

    // filter
    if (!MethodUtil.isNull(filterQuestion)) {
      // categoryId
      if (!MethodUtil.isNull(filterQuestion.getCategoryId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_CATEGORY_FILTER,
                Operation.EQUAL, filterQuestion.getCategoryId()));
      }
      // time from
      if (!MethodUtil.isNull(filterQuestion.getTypeId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_TYPE_FILTER,
                Operation.EQUAL, filterQuestion.getTypeId()));
      }
      // time to
      if (!MethodUtil.isNull(filterQuestion.getLevelId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_LEVEL_FILTER,
                Operation.EQUAL, filterQuestion.getLevelId()));
      }
      // questions from
      if (!MethodUtil.isNull(filterQuestion.getCreatorId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_CREATE_USER_FILTER,
                Operation.EQUAL, filterQuestion.getCreatorId()));
      }
      // create date from
      if (!MethodUtil.isNull(filterQuestion.getCreateDateFrom())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_CREATE_DATE_FILTER,
                Operation.GREATER_THAN_OR_EQUAL_DATE, filterQuestion.getCreateDateFrom()));
      }
      // create date to
      if (!MethodUtil.isNull(filterQuestion.getCreateDateTo())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.QUESTION_CREATE_DATE_FILTER,
                Operation.LESS_THAN_OR_EQUAL_DATE, filterQuestion.getCreateDateTo()));
      }

      // user
      if (!MethodUtil.isNull(filterQuestion.getCreatorId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.EXAM_CREATE_USER_FILTER,
                Operation.EQUAL, filterQuestion.getCreatorId()));
      }
    }

    // convert sorting field search
    sorting.convertSort(Constants.NameColume.CATEGORY_NAME,
        Constants.NameColume.CATEGORY_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
    // query extra sort field questionId
    sorting.and(Direction.ASC, Constants.NameColume.QUESTION_ID);

    // query
    return questionRepository.findAll(specification.build(),
        MethodUtil.Pagination(paging, sorting));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#getAllLevelQuestion()
   */
  @Override
  public List<LevelQuestion> getAllLevelQuestion() {
    return levelQuestionRepository.findAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#isLevelQuestionExists(int)
   */
  @Override
  public boolean isLevelQuestionExists(int id) {
    return levelQuestionRepository.exists(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#getAllTypeQuestion()
   */
  @Override
  public List<TypeQuestion> getAllTypeQuestion() {
    return typeQuestionRepository.findAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#isTypeQuestionExists(int)
   */
  @Override
  public boolean isTypeQuestionExists(int id) {
    return typeQuestionRepository.exists(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#isTypeMultiChoise(int)
   */
  @Override
  public boolean isTypeMultiChoise(int id) {
    return id == Constants.Database.TYPE_MULTI_CHOISE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.QuestionService#createQuestion(com.cmc.training.
   * entity.Question, java.util.List)
   */
  @Override
  public Question createQuestion(Question question, List<Answer> answers) {
    // create question
    question = questionRepository.save(question);

    if (!MethodUtil.checkListIsNull(answers)) {
      // create answer
      for (Answer answer : answers) {
        answer.setQuestionId(question);
        answer = answerRepository.save(answer);
      }
    }
    // return object question created
    return getQuestionById(question.getQuestionId());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#checkQuestionExists(java.util.
   * List)
   */
  @Override
  public boolean isQuestionExists(List<Integer> ids) {

    SpecificationBuilder<Question> specification = new SpecificationBuilder<Question>();

    // filter list id
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.QUESTION_ID, Constants.Operation.IN, ids));

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    return questionRepository.count(specification.build()) == (long) ids.size();
  }

  /**
   * delete list question from id array
   * 
   * @param ids
   * @author: nhanh3
   */
  private void deleteQuestion(int id) {
    Question questionCurrent = getQuestionById(id);
    questionCurrent.setDeleted(true);
    questionRepository.save(questionCurrent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.QuestionService#getQuestionById(int)
   */
  @Override
  public Question getQuestionById(int id) {
    return questionRepository.findOne(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.QuestionService#deleteListQuestionById(java.util.
   * List)
   */
  @Override
  public void deleteListQuestionById(List<Integer> ids) {
    for (Integer id : ids) {
      deleteQuestion(id);
    }
  }

}
