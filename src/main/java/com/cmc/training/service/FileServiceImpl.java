package com.cmc.training.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.training.util.Constants;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.file.ManagerFile;

/**
 * 
 * @author: NNDuy
 * @Date: Mar 14, 2018
 */
@Service
public class FileServiceImpl implements FileService {

  // declare config service
  @Autowired
  private ConfigService configService;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.FileService#uploadImage(org.springframework.web.
   * multipart.MultipartFile)
   */
  @Override
  public String uploadImage(MultipartFile image) throws IOException {

    // get folder save image
    String linkFolderSave = configService.getFolderSaveImage();
    String nameImage = new Date().getTime() + Constants.Common.DOT
        + ManagerFile.getFormatFile(image.getOriginalFilename());

    // save file
    return createFile(linkFolderSave, nameImage, image);
  }

  /**
   * create file
   * 
   * @param linkFolderSave
   *          - link folder to save file
   * @param nameFile
   *          - name file
   * @param file
   *          - multipartFile
   * @return link created file
   * @throws IOException
   *           String
   * @author: NNDuy
   */
  private String createFile(String linkFolderSave, String nameFile, MultipartFile file)
      throws IOException {

    String linkProject = MethodUtil.getLinkProject();

    // write file
    ManagerFile managerFile = new ManagerFile();

    // check folder is exist
    if (!managerFile.checkFileOrFolderExist(linkProject + linkFolderSave)) {
      managerFile.createFolder(linkProject + linkFolderSave);
    }
    managerFile.createMultiPartFile(linkProject + linkFolderSave + "\\" + nameFile, file);
    // return link uploaded file
    return linkFolderSave + "\\" + nameFile;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.FileService#isFileExist(java.lang.String)
   */
  @Override
  public boolean isFileExist(String path) {

    // get link file in disk
    String linkFile = MethodUtil.getLinkProject() + path;

    boolean result;
    try {
      result = new ManagerFile().checkFileOrFolderExist(linkFile);
    } catch (IOException e) {
      result = false;
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.FileService#deleteFile(java.lang.String)
   */
  @Override
  public void deleteFile(String filePath) {
    // get link file in disk
    String linkFile = MethodUtil.getLinkProject() + filePath;
    try {
      new ManagerFile().deleteFile(linkFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
