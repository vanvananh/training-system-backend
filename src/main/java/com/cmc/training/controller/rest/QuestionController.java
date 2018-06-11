/**
 * 
 */
package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.dto.AnswerDTO;
import com.cmc.training.dto.QuestionDTO;
import com.cmc.training.entity.LevelQuestion;
import com.cmc.training.entity.Question;
import com.cmc.training.entity.TypeQuestion;
import com.cmc.training.service.AccountService;
import com.cmc.training.service.CategoryQuestionService;
import com.cmc.training.service.ConfigService;
import com.cmc.training.service.FileService;
import com.cmc.training.service.QuestionService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.Constants;
import com.cmc.training.util.DataRespone;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.RegularExpressions;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterQuestion;

/**
 * @author lhdoan
 * @Date Mar 14, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.QUESTION_ANSWER)
public class QuestionController {

  // declare question service
  @Autowired
  private QuestionService questionService;

  // declare categoryQuestion service
  @Autowired
  private CategoryQuestionService categoryQuestionService;

  // declare AccountService service
  @Autowired
  private AccountService accountService;

  // declare config service
  @Autowired
  private ConfigService configService;

  // declare file service
  @Autowired
  private FileService fileService;

  /**
   * This method is get all Question.
   * 
   * @param pageNumber
   *          - page number must > 0
   * @param sortType
   *          - or ASC / DESC
   * @param sortField
   *          - field need sort
   * @param keywordSearch
   *          - keyword need search
   * @return ResponseEntity<?>
   * @author: lhdoan
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllQuestion(
      @RequestParam(name = Constants.Param.PAGE_NUMBER, defaultValue = Constants.Param.PAGE_NUMBER_DEFAULT_STR) int pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_DESC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_QUESTION_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
      FilterQuestion filter) {

    // validate param pageNumber <= 0
    if (pageNumber <= Constants.Common.ZERO_INT) {
      // return message error param pageNumber not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_PAGENUMBER_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param sortType
    if (!MethodUtil.isNull(sortType) && !MethodUtil.checkRegularExpression(sortType.toLowerCase(),
        RegularExpressions.SORT_TYPE_PATTERN)) {
      // return message error param sortType not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_SORTTYPE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param sortField
    if (!MethodUtil.isNull(sortField) && !MethodUtil.checkRegularExpression(sortField,
        RegularExpressions.QUESTION_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success & then get Respone include: list question
    Page<Question> page = questionService.getAllQuestion(
        new Paging(pageNumber, configService.getNumberRecordPerPageDefault()),
        new Sorting(sortType, sortField), keywordSearch, filter);

    // validate param success then return list question
    return new ResponseEntity<DataRespone>(new DataRespone(pageNumber, page.getTotalPages(),
        page.getContent().size(), page.getContent()), HttpStatus.OK);
  }

  /**
   * This method is get all Level Question.
   * 
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @GetMapping(value = Constants.Url.QuestionUrl.GET_ALL_LEVEL_QUESTION)
  public ResponseEntity<?> getAllLevelQuestion() {

    // return list level question
    return new ResponseEntity<List<LevelQuestion>>(questionService.getAllLevelQuestion(),
        HttpStatus.OK);
  }

  /**
   * This method is get all Type Question.
   * 
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @GetMapping(value = Constants.Url.QuestionUrl.GET_ALL_TYPE_QUESTION)
  public ResponseEntity<?> getAllTypeQuestion() {

    // return list type question
    return new ResponseEntity<List<TypeQuestion>>(questionService.getAllTypeQuestion(),
        HttpStatus.OK);
  }

  /**
   * This method is create Question.
   * 
   * @param question
   *          - object question to create.
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @PostMapping(value = Constants.Url.CRUD.CREATE)
  public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO question) {

    // check levelId exists
    if (!questionService.isLevelQuestionExists(question.getLevelId())) {
      // return message error levelId not exits
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.Question.LEVEL_QUESTION_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // check typeId exists
    if (!questionService.isTypeQuestionExists(question.getTypeId())) {
      // return message error typeId not exits
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.Question.TYPE_QUESTION_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // check categoryId exists
    if (!categoryQuestionService.isCategoryQuestionExists(question.getCategoryId())) {
      // return message error categoryId not exits
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.NOT_FOUND,
          MessageUtil.CategoryQuestion.PARAMETER_CATEGORY_ID_NOT_VALID), HttpStatus.NOT_FOUND);
    }

    // check CreatorId not exists
    if (!accountService.isAccountExists(question.getCreatorId())) {
      // return message error CreatorId not exits
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.CategoryQuestion.CREATOR_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // trim field content of question and answer
    trimContent(question);

    // valid param content of question
    if (!validateContentOfQuestion(question.getContent())) {
      // return message error param content not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.Question.PARAMETER_CONTENT_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // if answer for multi-choise
    if (questionService.isTypeMultiChoise(question.getTypeId())) {

      // check list answer < 2
      if (MethodUtil.checkListIsNull(question.getAnswers()) || question.getAnswers().size() < 2
          || question.getAnswers().size() > 5) {
        // return message error list answer < 2 or answer > 5
        return new ResponseEntity<ApiMessage>(
            new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
                MessageUtil.Question.QUESTION_HAVE_LEAST_TWO_ANSWER_AND_MAX_FIVE_ANSWER),
            HttpStatus.UNPROCESSABLE_ENTITY);
      }

      // validate content of answer
      if (!validateContentInAnswers(question.getAnswers())) {
        return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
            MessageUtil.Question.PARAMETER_CONTENT_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
      }

      // validate field isCorrect != null
      if (!validateIsCorrectInAnswers(question.getAnswers())) {
        return new ResponseEntity<ApiMessage>(
            new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
                MessageUtil.Question.PARAMETER_IS_CORRECT_NOT_VALID),
            HttpStatus.UNPROCESSABLE_ENTITY);
      }

      // validate isCorrect of answer has least 1 true
      if (!isCorrectTrueInAnswers(question.getAnswers())) {
        return new ResponseEntity<ApiMessage>(
            new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
                MessageUtil.Question.PARAMETER_IS_CORRECT_HAVE_LEAST_ONE),
            HttpStatus.UNPROCESSABLE_ENTITY);
      }
    }

    // validate linkImage
    if (!MethodUtil.isNull(question.getLinkImage())
        && !fileService.isFileExist(question.getLinkImage())) {
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.Question.LINK_IMAGE_NOT_EXIST), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success then return create Question
    return new ResponseEntity<Question>(
        questionService.createQuestion(question.toEntityQuestion(), question.toEntityAnswer()),
        HttpStatus.OK);
  }

  /**
   * trim field content of question and answer
   * 
   * @param question
   *          void
   * @author: NNDuy
   */
  private void trimContent(QuestionDTO question) {
    // trim content of question
    if (!MethodUtil.isNull(question.getContent())) {
      question.setContent(question.getContent().trim());
    }

    // trim answers
    if (!MethodUtil.checkListIsNull(question.getAnswers())) {
      for (AnswerDTO answer : question.getAnswers()) {
        // trim content of answer
        if (!MethodUtil.isNull(answer.getContent())) {
          answer.setContent(answer.getContent().trim());
        }
      }
    }
  }

  /**
   * validate content max 500 character in answers
   * 
   * @param answers
   * @return boolean
   * @author: NNDuy
   */
  private boolean validateContentInAnswers(List<AnswerDTO> answers) {
    for (AnswerDTO answer : answers) {
      // valid param content of answer
      if (MethodUtil.isNull(answer.getContent()) || !MethodUtil
          .validateStringlength(answer.getContent(), Constants.Common.ANSWER_MAX_LENGTH_CONTENT)) {
        return false;
      }
    }
    return true;
  }

  /**
   * validate content max 1500 character in question
   * 
   * @param content
   * @return boolean
   * @author: NNDuy
   */
  private boolean validateContentOfQuestion(String content) {
    // valid param content of question
    if (MethodUtil.isNull(content) || !MethodUtil.validateStringlength(content,
        Constants.Common.QUESTION_MAX_LENGTH_CONTENT)) {
      return false;
    }
    return true;
  }

  /**
   * validate is correct not null
   * 
   * @param answers
   * @return boolean
   * @author: NNDuy
   */
  private boolean validateIsCorrectInAnswers(List<AnswerDTO> answers) {
    for (AnswerDTO answer : answers) {
      // check isCorrect = null
      if (MethodUtil.isNull(answer.getIsCorrect())) {
        // return message error param isCorrect not valid
        return false;
      }
    }
    return true;
  }

  /**
   * validate isCorrect of answer has least 1 true
   * 
   * @param answers
   * @return boolean
   * @author: NNDuy
   */
  private boolean isCorrectTrueInAnswers(List<AnswerDTO> answers) {
    for (AnswerDTO answer : answers) {
      if (answer.getIsCorrect()) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * delete all question have id in array and validate
   * 
   * @param ids
   * @return ResponseEntity<?>
   * @author: nhanh3
   */
  @DeleteMapping(value = Constants.Url.CRUD.DELETE)
  public ResponseEntity<?> deleteListQuestionById(
      @RequestParam(name = Constants.Param.ID) List<Integer> ids) {
    // check list ids is empty
    if (MethodUtil.checkListIsNull(ids)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
    // validate param ids
    if (!questionService.isQuestionExists(ids)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_EXIST),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // if validate param id success then delete group by id
    questionService.deleteListQuestionById(ids);
    // return success
    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);

  }
}
