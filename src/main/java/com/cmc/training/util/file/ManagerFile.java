/**
 * training-system-backend - com.cmc.training.util
 */
package com.cmc.training.util.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.training.util.Constants;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;

/**
 * @author: NNDuy
 * @Date: Mar 21, 2018
 */
public class ManagerFile {

  /**
   * Constructure
   */
  public ManagerFile() {

  }

  /**
   * check file or folder exist
   * 
   * @param path
   *          - input path
   * @return
   * @throws IOException
   *           boolean
   * @author: NNDuy
   */
  public boolean checkFileOrFolderExist(String path) throws IOException {
    // validate
    if (MethodUtil.isNull(path)) {
      throw new IOException("Param filePath is null");
    }
    // check file exist
    return new File(path).exists();
  }

  /**
   * check type file is image
   * 
   * @param file
   *          - input file
   * @return boolean
   * @author: NNDuy
   */
  public boolean isTypeFileImage(MultipartFile file) {

    // validate file
    if (MethodUtil.isNull(file)) {
      return false;
    }

    // check
    return file.getContentType().toLowerCase().contains(Constants.Common.IMAGE);
  }

  /**
   * check path is folder
   * 
   * @param path
   *          - input path
   * @return boolean
   * @author: NNDuy
   */
  public boolean isFolder(String path) {
    return new File(path).isDirectory();
  }

  /**
   * create file by path
   * 
   * @param filePath
   *          - input path
   * @return
   * @throws IOException
   *           boolean
   * @author: NNDuy
   */
  public boolean createFile(String filePath) throws IOException {
    // if file Exist
    if (checkFileOrFolderExist(filePath)) {
      // Show message error
      System.out.println(MessageUtil.File.FILE_OR_FOLDER_EXIST);
      return false;
    }
    // if file not Exist
    // Create new File
    new File(filePath).createNewFile();
    // Show message
    System.out.println(MessageUtil.File.CREATE_FILE_SUCCESS);
    return true;
  }

  /**
   * create file by base64
   * 
   * @param filePath
   *          - input path
   * @param base64
   *          - content file
   * @throws IOException
   *           void
   * @author: NNDuy
   */
  public void createFileFromBase64(String filePath, String base64) throws IOException {

    // validate
    if (MethodUtil.isNull(base64)) {
      throw new IOException();
    }

    // if file Exist
    if (checkFileOrFolderExist(filePath)) {
      // Show message error
      throw new IOException(MessageUtil.File.FILE_OR_FOLDER_EXIST);
    }

    // write file
    File file = new File(filePath);
    byte[] bytes = Base64.decodeBase64(base64);
    Path path = Paths.get(file.getPath());
    Files.write(path, bytes);
  }

  /**
   * create file by MultipartFile
   * 
   * @param filePath
   *          - input path
   * @param multipartFile
   *          - input file
   * @throws IOException
   *           void
   * @author: NNDuy
   */
  public void createMultiPartFile(String filePath, MultipartFile multipartFile) throws IOException {

    // validate
    if (MethodUtil.isNull(multipartFile)) {
      throw new IOException();
    }

    // if file Exist
    if (checkFileOrFolderExist(filePath)) {
      // Show message error
      throw new IOException(MessageUtil.File.FILE_OR_FOLDER_EXIST);
    }

    // write file
    File file = new File(filePath);
    multipartFile.transferTo(file);
  }

  /**
   * create folder
   * 
   * @param folderPath
   *          - input path
   * @return
   * @throws IOException
   *           boolean
   * @author: NNDuy
   */
  public boolean createFolder(String folderPath) throws IOException {
    // if file Exist
    if (checkFileOrFolderExist(folderPath)) {
      // Show message error
      System.out.println(MessageUtil.File.FILE_OR_FOLDER_EXIST);
      return false;
    }

    // if folder not Exist
    // Create new folder
    new File(folderPath).mkdirs();
    // Show message
    System.out.println(MessageUtil.File.CREATE_FOLDER_SUCCESS);
    return true;
  }

  /**
   * delete file
   * 
   * @param filePath
   *          - input path
   * @return
   * @throws IOException
   *           boolean
   * @author: NNDuy
   */
  public boolean deleteFile(String filePath) throws IOException {
    // if file not exist
    if (!checkFileOrFolderExist(filePath)) {
      // Show message error
      System.out.println(MessageUtil.File.FILE_OR_FOLDER_NOT_EXIST);
      return false;
    }
    // if file Exist
    // Delete file
    new File(filePath).delete();
    // Show message
    System.out.println(MessageUtil.File.DELETE_FILE_SUCCESS);
    return true;
  }

  /**
   * rename file
   * 
   * @param filePath
   *          - path old
   * @param filePathNew
   *          - path new
   * @return
   * @throws IOException
   *           boolean
   * @author: NNDuy
   */
  public boolean renameFile(String filePath, String filePathNew) throws IOException {
    // if file source not exist
    if (!checkFileOrFolderExist(filePath)) {
      // Show message error
      System.out.println(MessageUtil.File.FILE_SOURCE_NOT_EXIST);
      return false;
    }
    // if file source Exist
    // create new file destination with path = filePath
    File dest = new File(filePathNew);
    // if file destination exist
    if (dest.exists()) {
      // Show message error
      System.out.println(MessageUtil.File.FILE_DESTINATION_EXIST);
      return false;
    }
    // if file destination not exist
    // copy data from file to destination and delete file
    new File(filePath).renameTo(dest);
    // Show message
    System.out.println(MessageUtil.File.RENAME_FILE_SUCCESS);
    return true;
  }

  /**
   * get format file
   * 
   * @param input
   *          - link file
   * @return String
   * @author: NNDuy
   */
  public static String getFormatFile(String input) {
    String[] results = input.split(Constants.Common.DOT_ENCODE);
    return results[results.length - 1];
  }

  /**
   * get name folder of path
   * 
   * @param path
   *          - input path
   * @return String
   * @author: NNDuy
   */
  public static String getNameFolderOfPath(String path) {
    String[] results = path.split("\\");
    return results[results.length - 2];
  }
}
