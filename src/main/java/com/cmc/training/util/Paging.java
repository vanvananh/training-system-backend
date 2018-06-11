package com.cmc.training.util;

/**
 * This class is paging.
 * 
 * @author: NNDuy
 * @create_date: Feb 23, 2018
 * @version: 1.0
 * @modifer: NNDuy
 * @modifer_date: Feb 23, 2018
 */
public class Paging {

  private int pageNumber;
  private int numberRecordPerPage;

  /**
   * Constructure
   */
  public Paging(int pageNumber, int numberRecordPerPage) {
    this.pageNumber = pageNumber;
    this.numberRecordPerPage = numberRecordPerPage;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getNumberRecordPerPage() {
    return numberRecordPerPage;
  }

}
