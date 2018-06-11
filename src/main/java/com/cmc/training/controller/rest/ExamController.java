package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

import com.cmc.training.dto.ExamDTO;
import com.cmc.training.entity.Duration;
import com.cmc.training.entity.Exam;
import com.cmc.training.entity.StatusExam;
import com.cmc.training.entity.view.ViewListExam;
import com.cmc.training.service.AccountService;
import com.cmc.training.service.CategoryQuestionService;
import com.cmc.training.service.ConfigService;
import com.cmc.training.service.ExamService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.Constants;
import com.cmc.training.util.DataRespone;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.RegularExpressions;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterExam;

/**
 * This class is exam controller.
 * 
 * @author: NVDong
 * @Date: Mar 14, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.EXAM)
public class ExamController {

  // declare exam service
  @Autowired
  private ExamService examService;

  // declare config service
  @Autowired
  private ConfigService configService;

  // declare account service
  @Autowired
  private AccountService accountService;

  // declare category question service
  @Autowired
  private CategoryQuestionService categoryQuestionService;

  /**
   * This method return all list Exam, page current, total page,
   * numberRecordPerPage
   * 
   * @param pageNumber
   *          - page number must > 0
   * @param sortType
   *          - or ASC / DESC
   * @param sortField
   *          - field need sort
   * @return ResponseEntity<?>
   * @author: NVDong
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getListExam(
      @RequestParam(name = Constants.Param.PAGE_NUMBER, defaultValue = Constants.Param.PAGE_NUMBER_DEFAULT_STR) int pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_EXAM_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
      FilterExam filter) {

    // validate param pageNumber <= 0
    if (pageNumber <= Constants.Common.ZERO_INT) {
      // return message error param pageNumber not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_PAGENUMBER_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param sortType
    if (!MethodUtil.compare(sortType.toLowerCase(), Sort.Direction.ASC.toString().toLowerCase())
        && !MethodUtil.compare(sortType.toLowerCase(),
            Sort.Direction.DESC.toString().toLowerCase())) {
      // return message error param sortType not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_SORTTYPE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param sortField
    if (!MethodUtil.isNull(sortField) && !MethodUtil.checkRegularExpression(sortField,
        RegularExpressions.EXAM_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    // validate filter
    // if (filter != null) {
    // // check categoryId exists
    // if
    // (!categoryQuestionService.isCategoryQuestionExists(filter.getCategoryId())) {
    // // return message error categoryId not exits
    // return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.NOT_FOUND,
    // MessageUtil.CategoryQuestion.PARAMETER_CATEGORY_ID_NOT_VALID),
    // HttpStatus.NOT_FOUND);
    // }
    // //check creator exists
    // }

    // validate param success & then get Respone include: list group
    Page<ViewListExam> page = examService.getAllExam(
        new Paging(pageNumber, configService.getNumberRecordPerPageDefault()),
        new Sorting(sortType, sortField), keywordSearch, filter);

    return new ResponseEntity<DataRespone>(new DataRespone(pageNumber, page.getTotalPages(),
        page.getContent().size(), page.getContent()), HttpStatus.OK);
  }

  /**
   * This method return all list status
   * 
   * @return ResponseEntity<?>
   * @author: NVDong
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL + Constants.Url.STATUS_EXAM)
  public ResponseEntity<?> getListStatus() {
    List<StatusExam> page = examService.getAllStatus();

    return new ResponseEntity<List<StatusExam>>(page, HttpStatus.OK);
  }

  /**
   * 
   * This method is to create exam.
   * 
   * @param examDTO
   *          - object ExamDTO to get RequestBody from frontend
   * @return ResponseEntity<Exam>
   * @author: NTVAnh1
   */
  @PostMapping(value = Constants.Url.CRUD.CREATE)
  public ResponseEntity<?> createExam(@RequestBody ExamDTO examDTO) {

    // trim exam title
    if (!MethodUtil.isNull(examDTO.getTitle())) {
      String examTitleTrim = examDTO.getTitle();
      examDTO.setTitle(examTitleTrim.trim());
    }

    // trim exam note
    if (!MethodUtil.isNull(examDTO.getNote())) {
      String examNoteTrim = examDTO.getNote();
      examDTO.setNote(examNoteTrim.trim());
    }

    // valid param exam title
    if (MethodUtil.isNull(examDTO.getTitle()) || !MethodUtil
        .validateStringlength(examDTO.getTitle(), Constants.Common.MAX_LENGTH_STRING_TITLE)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Exam.EXAM_TITLE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
    // valid param exam note
    if (!MethodUtil.isNull(examDTO.getNote())) {
      if (!MethodUtil.validateStringlength(examDTO.getNote(),
          Constants.Common.MAX_LENGTH_STRING_NOTE)) {
        return new ResponseEntity<ApiMessage>(
            new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Exam.EXAM_NOTE_NOT_VALID),
            HttpStatus.UNPROCESSABLE_ENTITY);
      }
    }

    // check creatorId not exists
    if (!accountService.isAccountExists(examDTO.getCreatorId())) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.Account.CREATOR_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // check categoryId exists
    if (!categoryQuestionService.isCategoryQuestionExists(examDTO.getCategoryId())) {
      // return message error categoryId not exits
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.NOT_FOUND,
          MessageUtil.CategoryQuestion.PARAMETER_CATEGORY_ID_NOT_VALID), HttpStatus.NOT_FOUND);
    }

    // check duration exists
    if (!examService.isDurationExist(examDTO.getDurationId())) {
      // return message error categoryId not exits
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.NOT_FOUND,
          MessageUtil.CategoryQuestion.PARAMETER_CATEGORY_ID_NOT_VALID), HttpStatus.NOT_FOUND);
    }
    // return success
    return new ResponseEntity<Exam>(examService.createExam(examDTO.toEntity()), HttpStatus.OK);
  }

  /**
   * 
   * This method for delete Exam
   * 
   * @return ResponseEntity<?>
   * @author: VDHao
   */
  @DeleteMapping(value = Constants.Url.CRUD.DELETE)
  public ResponseEntity<?> deleteExam(@RequestParam(name = Constants.Param.ID) List<Integer> ids) {

    // check list ids is empty
    if (MethodUtil.checkListIsNull(ids)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param ids
    if (!examService.isExamExists(ids)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_EXIST),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // check exam status is draft
    if (!examService.isStatusDraftOfExam(ids)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Exam.STATUS_EXAM_IS_PUBLIC),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
    examService.deleteExams(ids);
    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);
  }

  /**
   * This method return all list Duration
   * 
   * @return ResponseEntity<?>
   * @author: NTVAnh1
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL + Constants.Url.DURATION_EXAM)
  public ResponseEntity<?> getListDurations() {
    return new ResponseEntity<List<Duration>>(examService.getAllDurations(), HttpStatus.OK);
  }

}
