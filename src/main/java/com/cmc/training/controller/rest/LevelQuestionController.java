package com.cmc.training.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.training.entity.LevelQuestion;
import com.cmc.training.service.LevelQuestionService;

/**
 * 
 * @author: lhdoan
 * @Date: 20 thg 3, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/levelquestion")
public class LevelQuestionController {
  // declare config service
  @Autowired
  private LevelQuestionService levelQuestionService;

  /**
   * 
   * This method is get all level Question.
   * 
   * @return ResponseEntity<?>
   * @author: lhdoan
   */
  @GetMapping(value = "/getAll")
  public ResponseEntity<?> getAllTypeQuestion() {
    return new ResponseEntity<List<LevelQuestion>>(levelQuestionService.getAllLevelQuestion(),
        HttpStatus.OK);
  }
}
