package com.cmc.training.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmc.training.entity.Group;
import com.cmc.training.entity.view.ViewGroup;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterGroup;

/**
 * This class is public to controller using business of group.
 * 
 * @author: VDHao
 * @Date: Mar 1, 2018
 */
public interface GroupService {

  /**
   * 
   * This method is create group.
   * 
   * @param group
   *          void
   * @author: NTGiang1
   */
  Group createGroup(Group group);

  /**
   * 
   * TODO description
   * 
   * @param group
   *          void
   * @author: NTGiang1
   */
  Group updateGroup(Group group);

  /**
   * 
   * TODO description
   * 
   * @param id
   *          void
   * @author: NTGiang1
   */
  void deleteGroup(int id);

  /**
   * 
   * this method check group exists by name
   * 
   * @param nameGroup
   * @return boolean
   * @author: NTGiang1
   */
  boolean checkGroupExists(String nameGroup);

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
  Group getGroupById(int id);

  /**
   * This method is check group exist.
   * 
   * @author: NNDuy
   * @create_date: Feb 23, 2018
   * @version: 1.0
   * @modifer: NNDuy
   * @modifer_date: Feb 23, 2018
   * @param id
   *          - id of group need check exist.
   * @return is exist?
   */
  boolean checkGroupExists(int id);

  /**
   * 
   * TODO description
   * 
   * @param pageable
   * @param sorting
   * @param keywordSearch
   * @return Page<ViewGroup>
   * @author: VDHao
   */
  Page<ViewGroup> getAllGroup(Paging paging, Sorting sorting, String keywordSearch, FilterGroup filterGroup);

  /**
   * 
   * this method check list group exists by id
   * 
   * @return boolean
   * @author: NTGiang1
   */
  boolean checkGroupExists(List<Integer> ids);

  /**
   * 
   * this method delete list group by id
   * 
   * @param ids
   *          void
   * @author: NTGiang1
   */
  void deleteListGroupById(List<Integer> ids);
}
