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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.dto.CategoryQuestionDTO;
import com.cmc.training.entity.CategoryQuestion;
import com.cmc.training.service.AccountService;
import com.cmc.training.service.CategoryQuestionService;
import com.cmc.training.service.ConfigService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.Constants;
import com.cmc.training.util.DataRespone;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.RegularExpressions;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterCategoryQuestion;

/**
 * This class is category question controller.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.CATEGORY_QUESTION)
public class CategoryQuestionController {

  // declare category question service
  @Autowired
  private CategoryQuestionService categoryQuestionService;

  // declare account question service
  @Autowired
  private AccountService accountService;

  // declare config service
  @Autowired
  private ConfigService configService;

  /**
   * This method is get all Category Question.
   * 
   * @param pageNumber
   *          - page number must > 0
   * @param sortType
   *          - or ASC / DESC
   * @param sortField
   *          - field need sort
   * @param keywordSearch
   *          - keyword need search or filter
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllCategoryQuestion(
      @RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_DESC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_CATEGORY_QUESTION_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
      FilterCategoryQuestion filter) {

    // validate param pageNumber <= 0
    if (!MethodUtil.isNull(pageNumber) && pageNumber <= Constants.Common.ZERO_INT) {
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
        RegularExpressions.CATEGORY_QUESTION_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success & then get Respone include: list category question
    Page<CategoryQuestion> page = categoryQuestionService
        .getAllCategoryQuestion(!MethodUtil.isNull(pageNumber)
            ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
            : null, new Sorting(sortType, sortField), keywordSearch, filter);

    // validate param success then return list category question
    return new ResponseEntity<DataRespone>(new DataRespone(pageNumber, page.getTotalPages(),
        page.getContent().size(), page.getContent()), HttpStatus.OK);
  }

  /**
   * This method is create Category Question.
   * 
   * @param categoryQuestion
   *          - object CategoryQuestion to create.
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @PostMapping(value = Constants.Url.CRUD.CREATE)
  public ResponseEntity<?> createCategoryQuestion(
      @RequestBody CategoryQuestionDTO categoryQuestion) {

    // trim CategoryName
    if (null != categoryQuestion.getCategoryName()) {
      categoryQuestion.setCategoryName(categoryQuestion.getCategoryName().trim());
    }

    // valid param CategoryName
    if (MethodUtil.isNull(categoryQuestion.getCategoryName())
        || !MethodUtil.validateStringlength(categoryQuestion.getCategoryName(),
            Constants.Common.MAX_LENGTH_STRING)) {
      // return message error param CategoryName not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
              MessageUtil.CategoryQuestion.PARAMETER_CATEGORYNAME_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // check categoryQuestionName exists
    if (categoryQuestionService.isCategoryQuestionExists(categoryQuestion.getCategoryName())) {
      // return message error categoryQuestion exits
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.CONFLICT, MessageUtil.CategoryQuestion.CATEGORY_NAME_EXISTS),
          HttpStatus.CONFLICT);
    }

    // check CreatorId not exists
    if (!accountService.isAccountExists(categoryQuestion.getCreatorId())) {
      // return message error CreatorId not exits
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.CategoryQuestion.CREATOR_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // validate param success then return create Group
    // return success
    return new ResponseEntity<CategoryQuestion>(
        categoryQuestionService.createCategoryQuestion(categoryQuestion.toEntity()), HttpStatus.OK);
  }

  /**
   * 
   * This method in order to delete Category Question
   * 
   * @param ids
   * @return ResponseEntity<CategoryQuestion>
   * @author: VDHao
   */
  @DeleteMapping(value = Constants.Url.CRUD.DELETE)
  public ResponseEntity<?> deleteCategoryQuestion(
      @RequestParam(name = Constants.Param.ID) List<Integer> ids) {

    // check list ids is empty
    if (MethodUtil.checkListIsNull(ids)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param ids
    if (!categoryQuestionService.isCategoryQuestionExists(ids)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_EXIST),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // if validate param id success then delete group by id
    categoryQuestionService.deleteListCategoryQuestionById(ids);
    // return success

    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);
  }

  /**
   * 
   * This method to update Category Question by Id
   * 
   * @param categoryQuestionDTO
   * @return ResponseEntity<?>
   * @author: VDHao
   */
  @PutMapping(value = Constants.Url.CRUD.UPDATE)
  public ResponseEntity<?> update(@RequestBody CategoryQuestionDTO categoryQuestionDTO) {

    // trim CategoryName
    if (null != categoryQuestionDTO.getCategoryName()) {
      categoryQuestionDTO.setCategoryName(categoryQuestionDTO.getCategoryName().trim());
    }

    // validate param category question name
    if (MethodUtil.isNull(categoryQuestionDTO.getCategoryName())
        || !MethodUtil.validateStringlength(categoryQuestionDTO.getCategoryName(),
            Constants.Common.MAX_LENGTH_STRING)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
              MessageUtil.CategoryQuestion.CATEGORY_QUESTION_NAME_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // check CategoryQuestion not exists
    if (MethodUtil.isNull(categoryQuestionDTO.getCategoryId())
        || !categoryQuestionService.isCategoryQuestionExists(categoryQuestionDTO.getCategoryId())) {
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.NOT_FOUND,
          MessageUtil.CategoryQuestion.CATEGORY_QUESTION_ID_NOT_EXISTS), HttpStatus.NOT_FOUND);
    }

    // check CategoryQuestionName exists
    if (categoryQuestionService.isCategoryQuestionExists(categoryQuestionDTO.getCategoryName())) {
      // return message error param CategoryQuestionName exists
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.CONFLICT, MessageUtil.CategoryQuestion.CATEGORY_NAME_EXISTS),
          HttpStatus.CONFLICT);
    }

    // if validate param success then update category question
    // return success
    return new ResponseEntity<CategoryQuestion>(
        categoryQuestionService.updateCategoryQuestion(categoryQuestionDTO.toEntity()),
        HttpStatus.OK);
  }

  /**
   * 
   * This method to get Category Question by Id
   * 
   * @param id
   * @return ResponseEntity<?>
   * @author: VDHao
   */
  @GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
  public ResponseEntity<?> getGroupById(@RequestParam(name = Constants.Param.ID) int id) {

    // validate param id
    if (!categoryQuestionService.isCategoryQuestionExists(id)) {
      // return message error param id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success then return Category Question by id param
    return new ResponseEntity<CategoryQuestion>(categoryQuestionService.getCategoryQuestionById(id),
        HttpStatus.OK);
  }

  /**
   * 
   * This method is to list all Category Question
   * 
   * @param
   * @return ResponseEntity<List<CategoryQuestion>>
   * @author: NTVAnh1
   */
  @GetMapping(value = Constants.Url.QuestionUrl.GET_ALL_CATEGORY_QUESTION)
  public ResponseEntity<?> getAllCategoryQuestion() {
    return new ResponseEntity<List<CategoryQuestion>>(
        categoryQuestionService.getAllCategoryQuestion(), HttpStatus.OK);
  }
}
