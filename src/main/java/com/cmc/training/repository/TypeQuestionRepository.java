package com.cmc.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cmc.training.entity.TypeQuestion;

/**
 * This class is Repository of Level Question.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface TypeQuestionRepository extends JpaRepository<TypeQuestion, Integer> {

}
