package com.cmc.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.CategoryQuestion;
import com.cmc.training.repository.CategoryQuestionRepository;
import com.cmc.training.util.Constants;
import com.cmc.training.util.Constants.Operation;
import com.cmc.training.util.MethodUtil;
import com.cmc.training.util.Paging;
import com.cmc.training.util.Sorting;
import com.cmc.training.util.filter.FilterCategoryQuestion;
import com.cmc.training.util.specification.CriteriaCustom;
import com.cmc.training.util.specification.SpecificationBuilder;

/**
 * This class is implement of CategoryQuestionService interface .
 * 
 * @author: NNDuy
 * @Date: Feb 28, 2018
 */
@Service
public class CategoryQuestionServiceImpl implements CategoryQuestionService {

  // declare categoryQuestion service
  @Autowired
  private CategoryQuestionRepository categoryQuestionRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#getAllCategoryQuestion(com.
   * cmc.training.util.Paging, com.cmc.training.util.Sorting, java.lang.String,
   * com.cmc.training.util.filter.FilterCategoryQuestion)
   */
  @Override
  public Page<CategoryQuestion> getAllCategoryQuestion(Paging paging, Sorting sorting,
      String keywordSearch, FilterCategoryQuestion filter) {

    SpecificationBuilder<CategoryQuestion> specification = new SpecificationBuilder<CategoryQuestion>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // search
    if (!MethodUtil.isNull(keywordSearch)) {
      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_NAME_SEARCH,
          Operation.LIKE, keywordSearch));
    }

    // filter
    if (!MethodUtil.isNull(filter)) {
      // creatorId
      if (!MethodUtil.isNull(filter.getCreatorId())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_FILTER_CREATOR_ID,
                Operation.EQUAL, filter.getCreatorId()));
      }
      // createDateFrom
      if (!MethodUtil.isNull(filter.getCreateDateFrom())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_FILTER_CREATE_DATE,
                Operation.GREATER_THAN_OR_EQUAL_DATE, filter.getCreateDateFrom()));
      }
      // createDateTo
      if (!MethodUtil.isNull(filter.getCreateDateTo())) {
        specification
            .addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_FILTER_CREATE_DATE,
                Operation.LESS_THAN_OR_EQUAL_DATE, filter.getCreateDateTo()));
      }
    }

    // convert sorting field search
    sorting.convertSort(Constants.NameColume.CATEGORY_NAME,
        Constants.NameColume.CATEGORY_NAME_SEARCH);
    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);

    // query extra sort field categoryId
    sorting.and(Direction.ASC, Constants.NameColume.CATEGORY_ID);

    // query with paging
    if (!MethodUtil.isNull(paging)) {
      return categoryQuestionRepository.findAll(specification.build(),
          MethodUtil.Pagination(paging, sorting));
    }
    // else query not paging
    return new PageImpl<CategoryQuestion>(
        categoryQuestionRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#getCategoryQuestionById(int)
   */
  @Override
  public CategoryQuestion getCategoryQuestionById(int id) {
    return categoryQuestionRepository.findOne(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#createCategoryQuestion(com.
   * cmc.training. entity.CategoryQuestion)
   */
  @Override
  public CategoryQuestion createCategoryQuestion(CategoryQuestion categoryQuestion) {
    return categoryQuestionRepository.save(categoryQuestion);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#updateCategoryQuestion(com.
   * cmc.training.entity.CategoryQuestion)
   */
  @Override
  public CategoryQuestion updateCategoryQuestion(CategoryQuestion categoryQuestion) {
    CategoryQuestion categoryQuestionCurrent = getCategoryQuestionById(
        categoryQuestion.getCategoryId());
    categoryQuestionCurrent.setInforUpdateCategoryQuestion(categoryQuestion);
    return categoryQuestionRepository.save(categoryQuestionCurrent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#deleteCategoryQuestion(int)
   */
  @Override
  public void deleteCategoryQuestion(int id) {
    CategoryQuestion categoryQuestionCurrent = getCategoryQuestionById(id);
    categoryQuestionCurrent.setDeleted(true);
    categoryQuestionRepository.save(categoryQuestionCurrent);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#checkCategoryQuestionExist(
   * int)
   */
  @Override
  public boolean isCategoryQuestionExists(int id) {
    return categoryQuestionRepository.exists(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#checkCategoryQuestionExists(
   * java.lang.String)
   */
  @Override
  public boolean isCategoryQuestionExists(String nameCategory) {

    SpecificationBuilder<CategoryQuestion> specification = new SpecificationBuilder<CategoryQuestion>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // filter name
    specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_NAME,
        Constants.Operation.EQUAL, nameCategory));

    return categoryQuestionRepository.count(specification.build()) >= Constants.Common.ONE_INT;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.CategoryQuestionService#
   * isCategoryQuestionCodeExists( java.lang.String)
   */
  @Override
  public boolean isCategoryQuestionCodeExists(String codeCategory) {

    SpecificationBuilder<CategoryQuestion> specification = new SpecificationBuilder<CategoryQuestion>();

    // get all record don't deleted
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.IS_DELETED, Constants.Operation.EQUAL, false));

    // filter code
    specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.CATEGORY_CODE,
        Constants.Operation.EQUAL, codeCategory));

    return categoryQuestionRepository.count(specification.build()) >= Constants.Common.ONE_INT;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#checkCategoryQuestionExists(
   * java.util.List)
   */
  @Override
  public boolean isCategoryQuestionExists(List<Integer> ids) {
    SpecificationBuilder<CategoryQuestion> specification = new SpecificationBuilder<CategoryQuestion>();
    specification.addCriteriaCustom(
        new CriteriaCustom(Constants.NameColume.CATEGORY_ID, Constants.Operation.IN, ids));
    return categoryQuestionRepository.count(specification.build()) == (long) ids.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cmc.training.service.CategoryQuestionService#
   * deleteListCategoryQuestionById(java.util.List)
   */
  @Override
  public void deleteListCategoryQuestionById(List<Integer> ids) {
    for (Integer id : ids) {
      deleteCategoryQuestion(id);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.cmc.training.service.CategoryQuestionService#getAllCategoryQuestions()
   */
  @Override
  public List<CategoryQuestion> getAllCategoryQuestion() {
    // TODO Auto-generated method stub
    return categoryQuestionRepository.findAll();
  }

}
