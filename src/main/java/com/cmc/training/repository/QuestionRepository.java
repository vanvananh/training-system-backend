package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.Question;

/**
 * This class is Repository of Question.
 * 
 * @author lhdoan
 * @Date Mar 14, 2018
 */
public interface QuestionRepository  extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question>{

}
