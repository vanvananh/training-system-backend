package com.cmc.training.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmc.training.entity.CategoryQuestion;
import com.cmc.training.util.Constants;

/**
 * This class is Repository of Category Question.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface CategoryQuestionRepository
    extends JpaRepository<CategoryQuestion, Integer>, JpaSpecificationExecutor<CategoryQuestion> {
  /**
   * This method is delete list CategoryQuestion by id.
   * 
   * @param id
   *          void
   * @author: NNDuy
   */
  @Transactional
  @Modifying
  @Query(value = " DELETE " + " FROM    CategoryQuestion " + " WHERE   categoryId IN :"
      + Constants.Param.ID)
  public void deleteListCategoryById(@Param(Constants.Param.ID) List<Integer> ids);

}
