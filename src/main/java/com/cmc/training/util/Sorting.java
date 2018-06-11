package com.cmc.training.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * This class is paging.
 * 
 * @author: NNDuy
 * @create_date: Feb 23, 2018
 * @version: 1. 0
 * @modifer: NNDuy
 * @modifer_date: Feb 23, 2018
 */
public class Sorting {

  private List<Sort.Direction> sortTypes;
  private List<String> sortFields;

  /**
   * Constructure
   */
  public Sorting(String sortType, String sortField) {
    this.sortTypes = new ArrayList<>();
    this.sortFields = new ArrayList<>();
    if (sortType.toLowerCase().equals(Sort.Direction.ASC.toString().toLowerCase())) {
      this.sortTypes.add(Sort.Direction.ASC);
    } else {
      this.sortTypes.add(Sort.Direction.DESC);
    }
    this.sortFields.add(sortField);
  }

  /**
   * Constructure
   */
  public Sorting(Direction sortType, String sortField) {
    this.sortTypes = new ArrayList<>();
    this.sortFields = new ArrayList<>();
    this.sortTypes.add(sortType);
    this.sortFields.add(sortField);
  }

  /**
   * add extra sorting
   * 
   * @param sortType
   *          - sort type.
   * @param sortField
   *          - sort field
   * @return Sorting - object current
   * @author: NNDuy
   */
  public Sorting and(String sortType, String sortField) {
    if (sortType.toLowerCase().equals(Sort.Direction.ASC.toString().toLowerCase())) {
      this.sortTypes.add(Sort.Direction.ASC);
    } else {
      this.sortTypes.add(Sort.Direction.DESC);
    }
    return this;
  }

  /**
   * add extra sorting
   * 
   * @param sortType
   *          - sort type.
   * @param sortField
   *          - sort field
   * @return Sorting - object current
   * @author: NNDuy
   */
  public Sorting and(Direction sortType, String sortField) {
    this.sortTypes.add(sortType);
    this.sortFields.add(sortField);
    return this;
  }

  /**
   * convert sort void
   * 
   * @author: NNDuy
   */
  public void convertSort(String sortFieldOld, String sortFieldNew) {
    for (int i = 0; i < sortFields.size(); i++) {
      if (sortFields.get(i).contains(sortFieldOld)) {
        sortFields.set(i, sortFields.get(i).replace(sortFieldOld, sortFieldNew));
      }
    }
  }

  public List<Sort.Direction> getSortTypes() {
    return sortTypes;
  }

  public List<String> getSortFields() {
    return sortFields;
  }

}
