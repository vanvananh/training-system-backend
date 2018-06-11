package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.dto.AccountDTO;
import com.cmc.training.dto.UserProfileDTO;
import com.cmc.training.entity.Account;
import com.cmc.training.entity.view.ViewGetAccountOfGroup;
import com.cmc.training.service.AccountService;
import com.cmc.training.service.ConfigService;
import com.cmc.training.service.GroupService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.Constants;
import com.cmc.training.util.DataRespone;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.RegularExpressions;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterAccount;

/**
 * @description: .
 * @author Huy Anh
 * @create_date: Feb 26, 2018
 * @modifier: User
 * @modifier_date: Feb 26, 2018
 **/
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.ACCOUNT)
public class AccountController {

  // declare account service
  @Autowired
  private AccountService accountService;

  // declare config service
  @Autowired
  private ConfigService configService;

  // declare group service
  @Autowired
  private GroupService groupService;

  /**
   * 
   * TODO description this controller return list account of group by groupId
   * 
   * @param groupId
   * @return ResponseEntity<?>
   * @author: nvangoc
   */
  @GetMapping(value = Constants.Url.GroupAccountUrl.GET_LIST_ACC_OF_GROUP_NO_PAGING)
  public ResponseEntity<?> getListAccountOfGroup(
      @RequestParam(name = Constants.Param.ID, defaultValue = Constants.Common.EMPTY_STRING) int groupId) {

    // validate param not exists
    if (!groupService.checkGroupExists(groupId)) {
      // return message error param id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Group.GROUP_ID_NOT_EXISTS),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success then return list account of group
    return new ResponseEntity<List<AccountDTO>>(accountService.getListAccountOfGroup(groupId),
        HttpStatus.OK);
  }

  /**
   * 
   * TODO description this function return result after searching
   * 
   * @param keywordSearch
   * @return ResponseEntity<?>
   * @author: nvangoc
   */
  @GetMapping(value = Constants.Url.AccountUrl.GET_SEARCH)
  public ResponseEntity<?> getAccountBySearch(
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, defaultValue = Constants.Common.EMPTY_STRING) String keywordSearch) {

    // validate param keywordSearch
    if (MethodUtil.isNull(keywordSearch)
        || !MethodUtil.validateStringlength(keywordSearch, Constants.Common.FIFTY_INT)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
              MessageUtil.Account.PARAMETER_KEYWORD_SEARCH_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success then return list account by keyword search
    return new ResponseEntity<List<AccountDTO>>(
        accountService.getAccountBySearch(MethodUtil.formatKeySearch(keywordSearch.trim())),
        HttpStatus.OK);
  }

  /**
   * TODO description: this method return pageNumber, list account, total page,
   * size of current page.
   * 
   * @param pageNumber
   *          receive page number from front end
   * @param sortType
   *          receive sort type from front end
   * @param sortField
   *          receive sort filed from front end
   * @return ResponseEntity<?>
   * @author: nhanh3
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllAccount(
      @RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_ACCOUNT_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
      FilterAccount filter) {

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
        RegularExpressions.ACCOUNT_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Page<Account> page = accountService.getAllAccount(!MethodUtil.isNull(pageNumber)
        ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
        : null, new Sorting(sortType, sortField), keywordSearch, filter);
    // validate param success then return list category question
    return new ResponseEntity<DataRespone>(new DataRespone(pageNumber, page.getTotalPages(),
        page.getContent().size(), page.getContent()), HttpStatus.OK);
  }

  /**
   * Get all member in a group.
   * 
   * @param groupId
   *          group id
   * @param pageNumber
   *          - page number must > 0
   * @param sortType
   *          - or ASC / DESC
   * @param sortField
   *          - field need sort
   * @return ResponseEntity<?>
   * @author: NVDong
   */
  @GetMapping(value = Constants.Url.AccountUrl.GET_LIST_ACC_OF_GROUP)
  public ResponseEntity<?> getListAccOfGroup(@RequestParam(name = Constants.Param.ID) int groupId,
      @RequestParam(name = Constants.Param.PAGE_NUMBER, defaultValue = Constants.Param.PAGE_NUMBER_DEFAULT_STR) int pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_ACCOUNT_OF_GROUP_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch, FilterAccount filter) {

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
        RegularExpressions.ACCOUNT_OF_GROUP_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param groupId not exists
    if (!groupService.checkGroupExists(groupId)) {
      // return message error param groupId not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Group.GROUP_ID_NOT_EXISTS),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    Page<ViewGetAccountOfGroup> page = accountService.getListAccountOfGroup(groupId,
        new Paging(pageNumber, configService.getNumberRecordPerPageDefault()),
        new Sorting(sortType, sortField), keywordSearch, filter);
    // validate param success then return list category account
    return new ResponseEntity<DataRespone>(
        new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(),
            accountService.getNumberAccountOfGroup(groupId), page.getContent()),
        HttpStatus.OK);
  }

  /**
   * This method is get account by id.
   * 
   * @author: NTVAnh1
   * @create_date: Feb 25, 2018
   * @version: 1.0
   * @modifer: NTVAnh1
   * @modifer_date: Feb 25, 2018
   * @param id
   *          - id of account need get.
   * @return account.
   */
  @GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
  public ResponseEntity<?> getAccountById(@RequestParam(name = Constants.Param.ID) int id) {

    // validate param id
    if (!accountService.isAccountExists(id)) {
      // return message error param id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success then return account by id param
    return new ResponseEntity<UserProfileDTO>(accountService.getUserProfileById(id), HttpStatus.OK);
  }
  
  
}
