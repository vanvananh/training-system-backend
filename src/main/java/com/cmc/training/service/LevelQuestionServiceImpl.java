package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.LevelQuestion;
import com.cmc.training.repository.LevelQuestionRepository;

/**
 * 
 * @author: lhdoan
 * @Date: 20 thg 3, 2018
 */
@Service
public class LevelQuestionServiceImpl implements LevelQuestionService {
  // declare categoryQuestion service
  @Autowired
  private LevelQuestionRepository levelQuestionRepository;

  @Override
  public List<LevelQuestion> getAllLevelQuestion() {
    return levelQuestionRepository.findAll();
  }

}
