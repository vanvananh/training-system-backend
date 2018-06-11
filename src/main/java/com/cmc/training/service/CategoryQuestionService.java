package com.cmc.training.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cmc.training.entity.CategoryQuestion;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterCategoryQuestion;

/**
 * This class is public to controller using business of CategoryQuestion.
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
public interface CategoryQuestionService {

  /**
   * This method is get list CategoryQuestion.
   * 
   * @param paging
   *          - object need paging.
   * @param sorting
   *          - object need sort.
   * @param keywordSearch
   *          - keyword need search
   * @param filter
   *          - form filter
   * @return Page CategoryQuestion .
   * @author: NNDuy
   */
  Page<CategoryQuestion> getAllCategoryQuestion(Paging paging, Sorting sorting,
      String keywordSearch, FilterCategoryQuestion filter);

  /**
   * This method is get CategoryQuestion by id.
   * 
   * @param id
   *          - id of CategoryQuestion need get.
   * @return CategoryQuestion
   * @author: VDHAO
   */
  CategoryQuestion getCategoryQuestionById(int id);

  /**
   * This method is create CategoryQuestion.
   * 
   * @param categoryQuestion
   *          - object CategoryQuestion need field CategoryQuestionName &&
   *          CategoryQuestionId must = null.
   * @author: NNDuy
   */
  CategoryQuestion createCategoryQuestion(CategoryQuestion categoryQuestion);

  /**
   * This method is update CategoryQuestion.
   * 
   * @param categoryQuestion
   *          - object CategoryQuestion need field CategoryQuestionName &
   *          CategoryQuestionId.
   * @author: VDHao
   */
  CategoryQuestion updateCategoryQuestion(CategoryQuestion categoryQuestion);

  /**
   * This method is delete CategoryQuestion.
   * 
   * @param id
   *          - id of CategoryQuestion need delete.
   * @author: NNDuy
   */
  void deleteCategoryQuestion(int id);

  /**
   * This method is check CategoryQuestion exist by id.
   * 
   * @param id
   *          - id of CategoryQuestion need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isCategoryQuestionExists(int id);

  /**
   * This method is check CategoryQuestion exist by name.
   * 
   * @param nameCategory
   *          - name of CategoryQuestion need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isCategoryQuestionExists(String nameCategory);

  /**
   * This method is check CategoryQuestion exist by code.
   * 
   * @param codeCategory
   *          - code of CategoryQuestion need check exist.
   * @return boolean
   * @author: NNDuy
   */
  boolean isCategoryQuestionCodeExists(String codeCategory);

  /**
   * This method is check List CategoryQuestion exist by id.
   * 
   * @param id
   *          - list id of CategoryQuestion need check exist.
   * @return boolean
   * @author: Vdhao
   */
  boolean isCategoryQuestionExists(List<Integer> id);

  /**
   * This method is delete list CategoryQuestion by id.
   * 
   * @param id
   *          - list id of CategoryQuestion need delete.
   * @author: Vdhao
   */
  void deleteListCategoryQuestionById(List<Integer> ids);

  /**
   * This method is to get list CategoryQuestions. No paging
   * 
   * @param
   * 
   * @author: NTVAnh1
   */
  List<CategoryQuestion> getAllCategoryQuestion();

}
