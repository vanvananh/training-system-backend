package com.cmc.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cmc.training.dto.AccountDTO;
import com.cmc.training.dto.UserProfileDTO;
import com.cmc.training.entity.Account;
import com.cmc.training.entity.Department;
import com.cmc.training.entity.GroupAccount;
import com.cmc.training.entity.Position;
import com.cmc.training.entity.view.ViewGetAccountOfGroup;
import com.cmc.training.repository.AccountRepository;
import com.cmc.training.repository.DepartmentRepository;
import com.cmc.training.repository.GroupAccountRepository;
import com.cmc.training.repository.PositionRepository;
import com.cmc.training.repository.ViewGetAccountOfGroupRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterAccount;
import com.cmc.training.util.specification.CriteriaCustom;
import com.cmc.training.util.specification.SpecificationBuilder;

/**
 * 
 * @author: NTGiang1
 * @Date: Mar 1, 2018
 */
@Service
public class AccountServiceImpl implements AccountService {

  // declare account Repository
  @Autowired
  private AccountRepository accountRepository;
  // declare account department
  @Autowired
  private DepartmentRepository allDepartmentsRepository;
  @Autowired
  private PositionRepository allPositionsRepository;

  // declare View Get Account Of Group Repository
  @Autowired
  private ViewGetAccountOfGroupRepository viewGetAccountOfGroupRepository;

  @Autowired
  private GroupAccountRepository groupAccountRepository;

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#isAccountExists(int)
   */
  @Override
  public boolean isAccountExists(int accountId) {
    return accountRepository.exists(accountId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.AccountService#getAllAccount(com.cmc.training.util.
   * Paging, com.cmc.training.util.Sorting, java.lang.String,
   * com.cmc.training.util.filter.FilterAccount)
   */
  @Override
  public Page<Account> getAllAccount(Paging paging, Sorting sorting, String keywordSearch,
      FilterAccount filter) {

    SpecificationBuilder<Account> specification = new SpecificationBuilder<Account>();

    // search
    if (!MethodUtil.isNull(keywordSearch)) {
      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.FULL_NAME_SEARCH,
          Constants.Operation.LIKE, keywordSearch));
    }

    // filter
    if (!MethodUtil.isNull(filter)) {
      // groupId
      if (!MethodUtil.isNull(filter.getGroupId())) {
        specification.addCriteriaCustom(new CriteriaCustom(
            Constants.NameColume.ACCOUNT_FILTER_GROUP_ID, Operation.EQUAL, filter.getGroupId()));
      }
      // departmentId
      if (!MethodUtil.isNull(filter.getDepartmentId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.ACCOUNT_FILTER_DEPARTMENT_ID,
                Operation.EQUAL, filter.getDepartmentId()));
      }
      // positionId
      if (!MethodUtil.isNull(filter.getPositionId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.ACCOUNT_FILTER_POSITION_ID,
                Operation.EQUAL, filter.getPositionId()));
      }
    }

    // convert sorting field search
    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.DEPARTMENT_NAME,
        Constants.NameColume.DEPARTMENT_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.POSITION_NAME,
        Constants.NameColume.POSITION_NAME_SEARCH);
    // query extra sort field accountId
    sorting.and(Direction.ASC, Constants.NameColume.ACCOUNT_ID);

    // query with paging
    if (!MethodUtil.isNull(paging)) {
      return accountRepository.findAll(specification.build(),
          MethodUtil.Pagination(paging, sorting));
    }
    // else query not paging
    return new PageImpl<Account>(
        accountRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.AccountService#getAccountBySearch(java.lang.String)
   */
  @Override
  public List<AccountDTO> getAccountBySearch(String keyword) {
    List<AccountDTO> accountDTOs = new ArrayList<>();
    List<Account> accountsBySearch = accountRepository.getAccountBySearch(keyword);
    for (Account accountBySearch : accountsBySearch) {
      AccountDTO accountDTO = new AccountDTO(accountBySearch);
      accountDTOs.add(accountDTO);
    }
    return accountDTOs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getListAccountOfGroup(int,
   * com.cmc.training.util.Paging, com.cmc.training.util.Sorting,
   * java.lang.String)
   */
  @Override
  public Page<ViewGetAccountOfGroup> getListAccountOfGroup(int groupId, Paging paging,
      Sorting sorting, String keywordSearch, FilterAccount filter) {

    SpecificationBuilder<ViewGetAccountOfGroup> specification = new SpecificationBuilder<ViewGetAccountOfGroup>();

    // filter groupId
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.GROUP_ID, Constants.Operation.EQUAL, groupId));

    // search
    if (!MethodUtil.isNull(keywordSearch)) {
      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.FULL_NAME_SEARCH,
          Constants.Operation.LIKE, keywordSearch));
    }
    // filter
    if (!MethodUtil.isNull(filter)) {
      // department
      if (!MethodUtil.isNull(filter.getDepartmentId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_ACCOUNT_DEPARTMENT,
                Operation.EQUAL, filter.getDepartmentId()));
      }
      // position
      if (!MethodUtil.isNull(filter.getPositionId())) {
        specification.addCriteriaCustom(new CriteriaCustom(
            Constants.NameColume.GROUP_ACCOUNT_POSITION, Operation.EQUAL, filter.getPositionId()));
      }
      // ======================
      // departmentName
      if (!MethodUtil.isNull(filter.getDepartmentName())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_ACCOUNT_DEPARTMENT,
                Operation.EQUAL, filter.getDepartmentName()));
      }
      // positionName
      if (!MethodUtil.isNull(filter.getPositionName())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_ACCOUNT_POSITION,
                Operation.EQUAL, filter.getPositionName()));
      }
      // joindateFrom
      if (!MethodUtil.isNull(filter.getJoinDateFrom())) {
        specification.addCriteriaCustom(
            new CriteriaCustom(Constants.NameColume.GROUP_ACCOUNT_DATE_JOINED_FROM,
                Operation.GREATER_THAN_OR_EQUAL_DATE, filter.getJoinDateFrom()));
      }
      // joindateTo
      if (!MethodUtil.isNull(filter.getJoinDateTo())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.GROUP_ACCOUNT_DATE_JOINED_TO,
                Operation.LESS_THAN_OR_EQUAL_DATE, filter.getJoinDateTo()));
      }
    }

    // convert sorting field search
    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.DEPARTMENT_NAME,
        Constants.NameColume.DEPARTMENT_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.POSITION_NAME,
        Constants.NameColume.POSITION_NAME_SEARCH);

    // query extra sort field accountId
    sorting.and(Direction.ASC, Constants.NameColume.ACCOUNT_ID);

    // query
    return viewGetAccountOfGroupRepository.findAll(specification.build(),
        MethodUtil.Pagination(paging, sorting));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getNumberAccountOfGroup(int)
   */
  @Override
  public int getNumberAccountOfGroup(int groupId) {

    SpecificationBuilder<Account> specification = new SpecificationBuilder<Account>();

    // filter groupId
    specification.addCriteriaCustom(new CriteriaCustom("groupAccountCollection.groupId.groupId",
        Constants.Operation.EQUAL, groupId));

    return (int) accountRepository.count(specification.build());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getUserProfileById(int)
   */
  @Override
  public UserProfileDTO getUserProfileById(int id) {
    return new UserProfileDTO(accountRepository.findOne(id));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getListAccountOfGroup(int)
   */
  @Override
  public List<AccountDTO> getListAccountOfGroup(int groupId) {
    List<AccountDTO> accountDTOs = new ArrayList<>();
    List<GroupAccount> groupAccounts = groupAccountRepository.getAccountsOfGroup(groupId);
    for (GroupAccount groupAccount : groupAccounts) {
      AccountDTO accountDTO = new AccountDTO(groupAccount);
      accountDTOs.add(accountDTO);
    }
    return accountDTOs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getAllDepartment()
   */
  @Override
  public List<Department> getAllDepartment() {
    return allDepartmentsRepository.findAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.AccountService#getAllPosition()
   */
  @Override
  public List<Position> getAllPosition() {
    return allPositionsRepository.findAll();
  }
}
