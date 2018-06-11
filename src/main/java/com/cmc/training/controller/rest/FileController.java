/**
 * 
 */
package com.cmc.training.controller.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.training.service.FileService;
import com.cmc.training.util.ApiMessage;
import com.cmc.training.util.Constants;
import com.cmc.training.util.MessageUtil;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.file.ManagerFile;

/**
 * @author NNDuy
 * @Date Mar 14, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.FILE)
public class FileController {

  // declare file service
  @Autowired
  private FileService fileService;

  /**
   * upload file image
   * 
   * @param image
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @PostMapping(value = Constants.Url.FileUrl.UPLOAD_IMAGE)
  public ResponseEntity<?> upLoadImage(
      @RequestParam(name = Constants.Param.IMAGE) MultipartFile image) {

    // check image is empty
    if (MethodUtil.isNull(image)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.IMAGE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate type file is Image
    if (!new ManagerFile().isTypeFileImage(image)) {
      // return message error Image not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.FILE_NOT_IMAGE),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate size image < 3MB
    if (image.getSize() > Constants.Common.THREE_MB) {
      // return message error Image > 3MB
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.IMAGE_LARGE_THREE_MB),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // if validate success then upload image
    try {
      // return success
      return new ResponseEntity<String>(fileService.uploadImage(image), HttpStatus.OK);
    } catch (IOException e) {
      // return error
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.Question.UPLOAD_IMAGE_NOT_SUCCESS), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  /**
   * delete file
   * 
   * @param filePath
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @DeleteMapping(value = Constants.Url.CRUD.DELETE)
  public ResponseEntity<?> deleteFile(@RequestParam(name = Constants.Param.PATH) String filePath) {

    // check path is empty
    if (MethodUtil.isNull(filePath)) {
      // return message error id not valid
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.FILE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // decode path
    try {
      filePath = MethodUtil.decodeUTF8(filePath);
    } catch (UnsupportedEncodingException e) {
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.PATH_NOT_DECODE),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate path exist
    if (!fileService.isFileExist(filePath)) {
      // return message error path not exist
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.NOT_FOUND, MessageUtil.File.FILE_NOT_EXIST),
          HttpStatus.NOT_FOUND);
    }

    // if validate success then delete file
    fileService.deleteFile(filePath);
    // return success
    return new ResponseEntity<ApiMessage>(
        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);

  }
}
