package com.cmc.training.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmc.training.dto.AccountDTO;
import com.cmc.training.dto.UserProfileDTO;
import com.cmc.training.entity.Account;
import com.cmc.training.entity.Department;
import com.cmc.training.entity.Position;
import com.cmc.training.entity.view.ViewGetAccountOfGroup;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterAccount;

public interface AccountService {

  /**
   * This method is check account exist.
   * 
   * @param id
   *          - id of account need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isAccountExists(int accountId);

  /**
   * @description: return list account is content of a page object .
   * @author Huy Anh.
   * @create_date: Feb 26, 2018
   * @modifier: User
   * @modifier_date: Feb 26, 2018
   * @param paging
   *          include page number and number per page.
   * @param sorting
   *          include sort field and sort type.
   * @param filter
   *          - form filter
   * @return
   */
  Page<Account> getAllAccount(Paging paging, Sorting sorting, String keywordSearch,
      FilterAccount filter);

  /**
   * Get all member in a group
   * 
   * @param paging
   *          - object need paging.
   * @param sorting
   *          - object need sort.
   * @param groupId
   *          - id of group.
   * @return List<ViewGetAccountOfGroup>
   * @author: Admin
   */
  Page<ViewGetAccountOfGroup> getListAccountOfGroup(int groupId, Paging paging, Sorting sorting,
      String keywordSearch, FilterAccount filter);

  /**
   * Get number member in a group
   * 
   * @param groupId
   *          - id of group.
   * @return int - Number account of group.
   * @author: NVDong
   */
  int getNumberAccountOfGroup(int groupId);

  /**
   * get account by search keyword
   * 
   * @param keyword
   * @return UpdateGroupMemberDTO
   * @author: NVANgoc
   */
  public List<AccountDTO> getAccountBySearch(String keyword);

  /**
   * This method is get account by id.
   * 
   * @author: NTVAnh
   * @create_date: 05-03-2018 09:59:33
   * @version: 1.0
   * @modifer: NTVAnh1
   * @modifer_date:
   * @param id
   *          - id of account need get.
   * @return account.
   */
  UserProfileDTO getUserProfileById(int id);

  /**
   * 
   * TODO description Get all member in a group
   * 
   * @param groupId
   * @return List<Account>
   * @author: nvangoc
   */
  public List<AccountDTO> getListAccountOfGroup(int groupId);

  /**
   * TODO get all department
   * @return List<Department> 
   * @author: nhanh3
   */
  List<Department> getAllDepartment();
  /**
   * TODO get all position
   * @return List<Position> 
   * @author: nhanh3
   */
  List<Position> getAllPosition();
}
