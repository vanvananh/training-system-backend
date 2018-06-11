package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.entity.Menu;
import com.cmc.training.service.MenuService;
import com.cmc.training.util.Constants;

/**
 * This class is menu controller.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.MENU)
public class MenuController {

  // declare menu service
  @Autowired
  private MenuService menuService;

  /**
   * This method is get all menu.
   * 
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
  public ResponseEntity<?> getAllCategoryQuestion() {

    // validate param success then return list category question
    return new ResponseEntity<List<Menu>>(menuService.getAllMenu(), HttpStatus.OK);
  }
}
