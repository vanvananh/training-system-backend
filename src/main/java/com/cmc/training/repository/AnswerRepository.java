package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmc.training.entity.Answer;

/**
 * This class is Repository of Answer.
 * 
 * @author NNDuy
 * @Date Mar 14, 2018
 */
public interface AnswerRepository
    extends JpaRepository<Answer, Integer>, JpaSpecificationExecutor<Answer> {

}
