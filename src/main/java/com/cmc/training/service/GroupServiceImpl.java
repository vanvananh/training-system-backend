package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.Group;
import com.cmc.training.entity.view.ViewGroup;
import com.cmc.training.repository.GroupRepository;
import com.cmc.training.repository.GroupViewRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterGroup;
import com.cmc.training.util.specification.CriteriaCustom;
import com.cmc.training.util.specification.SpecificationBuilder;

/**
 * This is GroupServiceImpl implement GroupService
 * 
 * @author: VDHao
 * @Date: Mar 1, 2018
 */

@Service
public class GroupServiceImpl implements GroupService {

  // declare group service
  @Autowired
  private GroupRepository groupRepository;

  // declare groupViewRepository
  @Autowired
  private GroupViewRepository groupViewRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.GroupService#getAllGroup(org.springframework.data.
   * domain.Pageable, com.cmc.training.util.Sorting, java.lang.String)
   */
  @Override
  public Page<ViewGroup> getAllGroup(Paging paging, Sorting sorting, String keywordSearch,
      FilterGroup filterGroup) {

    SpecificationBuilder<ViewGroup> specification = new SpecificationBuilder<ViewGroup>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // search
    if (!MethodUtil.isNull(keywordSearch)) {
      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_NAME_SEARCH,
          Constants.Operation.LIKE, keywordSearch));
    }

    // filter
    if (!MethodUtil.isNull(filterGroup)) {
      // maxMembers
      if (!MethodUtil.isNull(filterGroup.getNumberOfMembersTo())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_MEMBERS_FILTER,
                Operation.LESS_THAN_OR_EQUAL_INT, filterGroup.getNumberOfMembersTo()));
      }

      // minMembers
      if (!MethodUtil.isNull(filterGroup.getNumberOfMembersFrom())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_MEMBERS_FILTER,
                Operation.GREATER_THAN_OR_EQUAL_INT, filterGroup.getNumberOfMembersFrom()));
      }

      // create date from
      if (!MethodUtil.isNull(filterGroup.getCreateDateFrom())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_CREATE_DATE_FILTER,
                Operation.GREATER_THAN_OR_EQUAL_DATE, filterGroup.getCreateDateFrom()));
      }

      // create date to
      if (!MethodUtil.isNull(filterGroup.getCreateDateTo())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_CREATE_DATE_FILTER,
                Operation.LESS_THAN_OR_EQUAL_DATE, filterGroup.getCreateDateTo()));
      }
    }

    // convert sorting field search
    sorting.convertSort(Constants.NameColume.GROUP_NAME, Constants.NameColume.GROUP_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
    // query extra sort field groupId
    sorting.and(Direction.ASC, Constants.NameColume.GROUP_ID);

    // query
    return groupViewRepository.findAll(specification.build(),
        MethodUtil.Pagination(paging, sorting));
  }

  /*
   * @see
   * com.cmc.training.service.GroupService#createGroup(com.cmc.training.entity.
   * Group)
   */
  @Override
  public Group createGroup(Group group) {
    return groupRepository.save(group);
  }

  /*
   * @see
   * com.cmc.training.service.GroupService#updateGroup(com.cmc.training.entity.
   * Group)
   */
  @Override
  public Group updateGroup(Group group) {
    // get Group by id
    Group groupCurrent = getGroupById(group.getGroupId());
    groupCurrent.setInforUpdateGroup(group);
    return groupRepository.save(groupCurrent);
  }

  /*
   * @see com.cmc.training.service.GroupService#deleteGroup(int)
   */
  @Override
  public void deleteGroup(int id) {
    Group groupCurrent = getGroupById(id);
    groupCurrent.setDeleted(true);
    groupRepository.save(groupCurrent);
  }

  /*
   * @see com.cmc.training.service.GroupService#getGroupById(int)
   */
  @Override
  public Group getGroupById(int id) {
    return groupRepository.findOne(id);
  }

  /*
   * @see com.cmc.training.service.GroupService#checkGroupExists(int)
   */
  @Override
  public boolean checkGroupExists(int id) {
    return groupRepository.exists(id);
  }

  /*
   * @see com.cmc.training.service.GroupService#checkGroupExists(java.lang.String)
   */
  @Override
  public boolean checkGroupExists(String nameGroup) {

    SpecificationBuilder<Group> specification = new SpecificationBuilder<Group>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // filter name
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.GROUP_NAME, Constants.Operation.EQUAL, nameGroup));

    return groupRepository.count(specification.build()) >= Constants.Common.ONE_INT;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.GroupService#checkGroupExists(java.util.List)
   */
  @Override
  public boolean checkGroupExists(List<Integer> ids) {

    SpecificationBuilder<Group> specification = new SpecificationBuilder<Group>();

    // filter list id
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.GROUP_ID, Constants.Operation.IN, ids));

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    return groupRepository.count(specification.build()) == (long) ids.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.GroupService#deleteListGroupById(java.util.List)
   */
  @Override
  public void deleteListGroupById(List<Integer> ids) {
    for (Integer id : ids) {
      deleteGroup(id);
    }
  }
}
