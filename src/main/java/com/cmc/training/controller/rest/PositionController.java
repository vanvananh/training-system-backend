/**
 * training-system-backend - com.cmc.training.controller.rest
 */
package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.entity.Position;
import com.cmc.training.service.PositionService;
import com.cmc.training.util.Constants;

/**
 * @author: nhanh3
 * @Date: Mar 20, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.POSITION)
public class PositionController {
  @Autowired
  private PositionService postitionService;

  /**
   * 
   * TODO get all postion
   * 
   * @param id
   * @return ResponseEntity<?>
   * @author: nhanh3
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllPosition() {
    return new ResponseEntity<List<Position>>(postitionService.getAllPosition(), HttpStatus.OK);
  }
}
