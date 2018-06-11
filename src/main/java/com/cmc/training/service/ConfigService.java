package com.cmc.training.service;

/**
 * This class is public to controller using business of Config.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface ConfigService {

  /**
   * This method is get Number Page Default.
   * 
   * @return number
   * @author: NNDuy
   */
  public int getNumberPageDefault();

  /**
   * This method is get Number Record Per Page Default.
   * 
   * @return number
   * @author: NNDuy
   */
  public int getNumberRecordPerPageDefault();

  /**
   * This method is get Folder Save Image.
   * 
   * @return number
   * @author: NNDuy
   */
  public String getFolderSaveImage();

}
