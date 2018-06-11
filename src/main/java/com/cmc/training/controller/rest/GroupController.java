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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.dto.GroupDTO;
import com.cmc.training.entity.Group;
import com.cmc.training.entity.view.ViewGroup;
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
import com.cmc.training.util.filter.FilterExam;
import com.cmc.training.util.filter.FilterGroup;

/**
 * This is group Controller
 * 
 * @author: VDHao
 * @Date: Mar 1, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.GROUP)
public class GroupController {

  // declare group service
  @Autowired
  private GroupService groupService;

  // declare account service
  @Autowired
  private AccountService accountService;

  // declare config service
  @Autowired
  private ConfigService configService;

  /**
   * This method return all list group, page current, total page,
   * numberRecordPerPage
   * 
   * @param pageNumber
   *          - page number must > 0
   * @param sortType
   *          - or ASC / DESC
   * @param sortField
   *          - field need sort
   * @return ResponseEntity<?>
   * @author: VDHao
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllGroup(
      @RequestParam(name = Constants.Param.PAGE_NUMBER, defaultValue = Constants.Param.PAGE_NUMBER_DEFAULT_STR) int pageNumber,
      @RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
      @RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_GROUP_DEFAULT) String sortField,
      @RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
      FilterGroup filter) {

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
        RegularExpressions.GROUP_SORT_FIELD_PATTERN)) {
      // return message error param sortField not valid
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.PARAMETER_SORTFIELD_NOT_VALID), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate param success & then get Respone include: list group
    Page<ViewGroup> page = groupService.getAllGroup(
        new Paging(pageNumber, configService.getNumberRecordPerPageDefault()),
        new Sorting(sortType, sortField), keywordSearch, filter);

    return new ResponseEntity<DataRespone>(new DataRespone(pageNumber, page.getTotalPages(),
        page.getContent().size(), page.getContent()), HttpStatus.OK);
  }

  /**
   * 
   * This method is create group.
   * 
   * @param groupDTO
   *          - object GroupDTO to get RequestBody from frontend
   * @return ResponseEntity<Group>
   * @author: NTGiang1
   */
  @PostMapping(value = Constants.Url.CRUD.CREATE)
  public ResponseEntity<?> createGroup(@RequestBody GroupDTO groupDTO) {

    // trim groupname
    String groupNameTrim = groupDTO.getGroupName();
    groupDTO.setGroupName(groupNameTrim.trim());

    // valid param groupname
    if (MethodUtil.isNull(groupDTO.getGroupName()) || !MethodUtil
        .validateStringlength(groupDTO.getGroupName(), Constants.Common.MAX_LENGTH_STRING)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Group.GROUP_NAME_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // check group exists
    if (groupService.checkGroupExists(groupDTO.getGroupName())) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.CONFLICT, MessageUtil.Group.GROUP_NAME_EXISTS),
          HttpStatus.CONFLICT);
    }

    // check creatorId not exists
    if (!accountService.isAccountExists(groupDTO.getCreatorId())) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.Account.CREATOR_ID_NOT_EXISTS),
          HttpStatus.NOT_FOUND);
    }

    // return success
    return new ResponseEntity<Group>(groupService.createGroup(groupDTO.toEntity()), HttpStatus.OK);
  }

  /**
   * 
   * This method is update group
   * 
   * @param groupDTO
   * @return ResponseEntity<Group>
   * @author: NTGiang1
   */
  @PutMapping(value = Constants.Url.CRUD.UPDATE)
  public ResponseEntity<?> update(@RequestBody GroupDTO groupDTO) {

    // trim groupname
    String groupNameTrim = groupDTO.getGroupName();
    groupDTO.setGroupName(groupNameTrim.trim());

    // valid param groupname
    if (MethodUtil.isNull(groupDTO.getGroupName()) || !MethodUtil
        .validateStringlength(groupDTO.getGroupName(), Constants.Common.MAX_LENGTH_STRING)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.Group.GROUP_NAME_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // check groupId not exists
    if (MethodUtil.isNull(groupDTO.getGroupId())
        || !groupService.checkGroupExists(groupDTO.getGroupId())) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.Group.GROUP_ID_NOT_VALID),
          HttpStatus.NOT_FOUND);
    }

    // check groupname exists
    if (groupService.checkGroupExists(groupDTO.getGroupName())) {
      // return message error param groupName exists
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.CONFLICT, MessageUtil.Group.GROUP_NAME_EXISTS),
          HttpStatus.CONFLICT);
    }

    // if validate param success then update group
    // return success
    return new ResponseEntity<Group>(groupService.updateGroup(groupDTO.toEntity()), HttpStatus.OK);
  }

  /**
   * This method is get group by id.
   * 
   * @author: NNDuy
   * @create_date: Feb 25, 2018
   * @version: 1.0
   * @modifer: NNDuy
   * @modifer_date: Feb 25, 2018
   * @param id
   *          - id of group need get.
   * @return group.
   */
  @GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
  public ResponseEntity<?> getGroupById(@RequestParam(name = Constants.Param.ID) int id) {
    // validate param id
    if (!groupService.checkGroupExists(id)) {
      // return message error param id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
    // validate param success then return Group by id param
    return new ResponseEntity<Group>(groupService.getGroupById(id), HttpStatus.OK);
  }

  /**
   * 
   * This method is delete listGroup
   * 
   * @param ids
   * @return ResponseEntity<Group>
   * @author: NTGiang1
   */
  @DeleteMapping(value = Constants.Url.CRUD.DELETE)
  public ResponseEntity<?> deleteListGroupById(
      @RequestParam(name = Constants.Param.ID) List<Integer> ids) {
    // check list ids is empty
    if (MethodUtil.checkListIsNull(ids)) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
    // validate param ids
    if (!groupService.checkGroupExists(ids)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_EXIST),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // if validate param id success then delete group by id
    groupService.deleteListGroupById(ids);
    // return success
    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);

  }
}
