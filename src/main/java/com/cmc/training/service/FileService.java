package com.cmc.training.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: NNDuy
 * @Date: Mar 22, 2018
 */
public interface FileService {

  /**
   * upload image
   * 
   * @param image
   *          void
   * @author: NNDuy
   */
  String uploadImage(MultipartFile image) throws IOException;

  /**
   * check file is exist
   * 
   * @param path
   *          - input path
   * @return boolean
   * @author: NNDuy
   */
  boolean isFileExist(String path);

  /**
   * if file is exist then delete file
   * 
   * @param filePath
   *          - input path file void
   * @author: NNDuy
   */
  void deleteFile(String filePath);

}
